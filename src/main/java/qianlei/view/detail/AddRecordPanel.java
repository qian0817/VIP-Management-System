package qianlei.view.detail;

import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.RecordService;
import qianlei.view.detail.tabledetail.ShowGoodTableWithSearchBar;
import qianlei.view.detail.tabledetail.ShowVipTableWithSearchBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 添加记录界面
 *
 * @author qianlei
 */
public class AddRecordPanel extends JPanel implements CanInitPanel, CanSubmitPanel {
    private final ShowGoodTableWithSearchBar showGoodTableWithSearchBar = new ShowGoodTableWithSearchBar();
    private final ShowVipTableWithSearchBar showVipTableWithSearchBar = new ShowVipTableWithSearchBar();
    private final RecordService recordService = new RecordService();
    private final JButton check = new JButton("确认");
    private final JLabel showChooseGoodLabel = new JLabel("请选择商品", JLabel.CENTER);
    private final JLabel showChooseVipLabel = new JLabel("请选择VIP用户", JLabel.CENTER);
    private final JPanel chooseVipPanel = new JPanel(new BorderLayout());
    private final JPanel chooseGoodPanel = new JPanel(new BorderLayout());
    private final JPanel center = new JPanel(new GridLayout(2, 1));
    public AddRecordPanel() {
        init();
        check.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(AddRecordPanel.this, "是否添加该记录");
            if (a == JOptionPane.YES_OPTION) {
                Result result = submit();
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(AddRecordPanel.this, result.getMessage(), "添加成功", JOptionPane.INFORMATION_MESSAGE);
                    init();
                } else {
                    JOptionPane.showMessageDialog(AddRecordPanel.this, result.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    /**
     * 提交
     *
     * @return 结果
     */
    @Override
    public Result submit() {
        String goodId = showGoodTableWithSearchBar.getSelectedGoodId();
        String vipId = showVipTableWithSearchBar.getSelectedVipId();
        try {
            recordService.addRecord(goodId, vipId);
            return new Result(true, "添加成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }
    }

    @Override
    public void init() {
        removeAll();
        initComponentData();
        addComponent();
        addAction();
        repaint();
        setVisible(true);
    }

    /**
     * 初始化组件信息
     */
    private void initComponentData() {
        showVipTableWithSearchBar.init();
        showGoodTableWithSearchBar.init();
        showChooseGoodLabel.setText("请选择商品");
        showChooseVipLabel.setText("请选择VIP用户");
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        setLayout(new BorderLayout());
        chooseGoodPanel.add(showChooseGoodLabel, BorderLayout.NORTH);
        chooseGoodPanel.add(showGoodTableWithSearchBar);
        center.add(chooseGoodPanel);
        chooseVipPanel.add(showChooseVipLabel, BorderLayout.NORTH);
        chooseVipPanel.add(showVipTableWithSearchBar);
        center.add(chooseVipPanel);
        add(center);
        add(check, BorderLayout.SOUTH);
    }

    /**
     * 添加事件
     */
    private void addAction() {
        showVipTableWithSearchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChooseVipLabel.setText("当前选择VIP编号：" + showVipTableWithSearchBar.getSelectedVipId());
            }
        });
        showGoodTableWithSearchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChooseGoodLabel.setText("当前选择商品证件号：" + showGoodTableWithSearchBar.getSelectedGoodId());
            }
        });
    }
}
