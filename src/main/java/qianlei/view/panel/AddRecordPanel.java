package qianlei.view.panel;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.service.RecordService;
import qianlei.view.panel.component.InputPanelBase;
import qianlei.view.panel.component.TablePanel;
import qianlei.view.panel.detail.ShowGoodTableWithSearchBar;
import qianlei.view.panel.detail.ShowVipTableWithSearchBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 添加记录界面
 *
 * @author qianlei
 */
public class AddRecordPanel extends AbstractCanInitPanel {
    private final ShowGoodTableWithSearchBar showGoodTableWithSearchBar = new ShowGoodTableWithSearchBar();
    private final ShowVipTableWithSearchBar showVipTableWithSearchBar = new ShowVipTableWithSearchBar();
    private final RecordService recordService = new RecordService();
    private final JLabel showChooseVipLabel = new JLabel("请选择会员用户", JLabel.CENTER);
    private final JPanel chooseVipPanel = new JPanel(new BorderLayout());
    private final JPanel chooseGoodPanel = new JPanel(new BorderLayout());
    private final JPanel main = new JPanel(new GridLayout(1, 2));
    private final JPanel left = new JPanel(new BorderLayout());
    private final String[] columnNames = new String[]{"商品编号", "商品名称", "数量", "总价"};
    private final JButton check = new JButton("提交");
    private final JButton clear = new JButton("清空");
    private final JButton delete = new JButton("删除");
    private final Map<Good, Integer> shopMap = new HashMap<>();
    private final JButton addShop = new JButton("添加到购物车");
    private final TablePanel shopListTable = new TablePanel(new Object[][]{}, columnNames);
    private final JLabel shopListLabel = new JLabel("购物车", JLabel.CENTER);
    private final InputPanelBase inputRecordIdPanel = new InputPanelBase("订单号", "请输入订单号");
    private final JPanel bottomPanel = new JPanel(new BorderLayout());

    AddRecordPanel() {
        initView();
        check.addActionListener((e) -> addRecord());
        showVipTableWithSearchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChooseVipLabel.setText("当前选择的会员卡号:" + showVipTableWithSearchBar.getSelectedVip().getId());
            }
        });
        addShop.addActionListener((e) -> addGoodList());
        clear.addActionListener((e) -> clearShopList());
        delete.addActionListener((e) -> deleteSelectGood());
    }

    private void deleteSelectGood() {
        Object[] objects = shopListTable.getSelectedObject();
        for (Map.Entry<Good, Integer> entry : shopMap.entrySet()) {
            if (entry.getKey().getId().equals(objects[0])) {
                shopMap.remove(entry.getKey());
                break;
            }
        }
        changeShopList();
    }

    /**
     * 清空购物车
     */
    private void clearShopList() {
        shopMap.clear();
        changeShopList();
    }

    /**
     * 添加消费记录
     */
    private void addRecord() {
        Vip vip = showVipTableWithSearchBar.getSelectedVip();
        if (vip == null) {
            JOptionPane.showMessageDialog(AddRecordPanel.this, "没有选择会员用户", "添加失败", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String recordId = inputRecordIdPanel.getItem();

        int a = JOptionPane.showConfirmDialog(AddRecordPanel.this, "是否添加该记录");
        if (a == JOptionPane.YES_OPTION) {
            Result result = recordService.addRecord(shopMap, vip.getId(), recordId);
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(AddRecordPanel.this, result.getMessage(), "添加成功", JOptionPane.INFORMATION_MESSAGE);
                initView();
            } else {
                JOptionPane.showMessageDialog(AddRecordPanel.this, result.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 添加商品到购物车
     */
    private void addGoodList() {
        Good good = showGoodTableWithSearchBar.getSelectedGood();
        if (good == null) {
            return;
        }
        shopMap.put(good, shopMap.getOrDefault(good, 0) + 1);
        changeShopList();
    }

    /**
     * 修改购物车
     */
    private void changeShopList() {
        Object[][] shopList = new Object[shopMap.size()][columnNames.length];
        int i = 0;
        //总价计算
        BigDecimal price = BigDecimal.ZERO;
        for (Map.Entry<Good, Integer> entry : shopMap.entrySet()) {
            BigDecimal curPrice = entry.getKey().getRealPrice().multiply(new BigDecimal(entry.getValue()));
            shopList[i][0] = entry.getKey().getId();
            shopList[i][1] = entry.getKey().getName();
            shopList[i][2] = entry.getValue();
            shopList[i][3] = curPrice;
            price = price.add(curPrice);
            i++;
        }
        shopListLabel.setText(price.equals(BigDecimal.ZERO) ? "购物车" : "当前总价:" + price);
        shopListTable.changeData(shopList, columnNames);
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            clearShopList();
            removeAll();
            initComponentData();
            addComponent();
            repaint();
            setVisible(true);
        });
    }

    /**
     * 初始化组件信息
     */
    private void initComponentData() {
        showVipTableWithSearchBar.initView();
        showGoodTableWithSearchBar.initView();
        showChooseVipLabel.setText("请选择会员用户");
        shopListTable.init(new Object[][]{}, columnNames);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        setLayout(new BorderLayout());
        bottomPanel.add(check);
        bottomPanel.add(clear, BorderLayout.WEST);
        bottomPanel.add(delete, BorderLayout.EAST);

        left.setLayout(new BorderLayout());
        left.add(chooseVipPanel, BorderLayout.NORTH);
        left.add(shopListTable);
        left.add(bottomPanel, BorderLayout.SOUTH);

        chooseGoodPanel.add(showGoodTableWithSearchBar);
        chooseGoodPanel.add(addShop, BorderLayout.SOUTH);

        chooseVipPanel.add(showChooseVipLabel, BorderLayout.NORTH);
        chooseVipPanel.add(showVipTableWithSearchBar);
        chooseVipPanel.add(shopListLabel, BorderLayout.SOUTH);

        main.add(left);
        main.add(chooseGoodPanel);
        add(inputRecordIdPanel, BorderLayout.NORTH);
        add(main);
    }
}
