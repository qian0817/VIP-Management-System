package qianlei.view.panel;

import qianlei.entity.Record;
import qianlei.service.RecordService;
import qianlei.view.panel.component.SearchBar;
import qianlei.view.panel.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qianlei
 */
public class ShowRecordPanel extends AbstractCanInitPanel {
    private static final String[] COLUMN_NAMES = new String[]{"订单号", "会员编号", "会员姓名", "会员手机号", "会员状态", "总价格", "购买日期"};

    private final SearchBar searchBar = new SearchBar(Arrays.asList("会员姓名", "会员编号", "手机号"));
    private final RecordService recordService = new RecordService();
    private final TablePanel tablePanel = new TablePanel(new Object[][]{}, COLUMN_NAMES);
    private final JButton showDetailButton = new JButton("查看购买的商品");

    ShowRecordPanel() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("证件号"), input.get("姓名"), input.get("手机号"));
        });
        showDetailButton.addActionListener((e) -> {
            Object[] objects = tablePanel.getSelectedObject();
            if (objects == null) {
                JOptionPane.showMessageDialog(ShowRecordPanel.this, "请选择需要查看的记录");
            } else {
                JFrame frame = new JFrame();
                frame.add(new ShowRecordDetailPanel((String) objects[0]));
                frame.pack();
                frame.setResizable(false);
                frame.setLocation(800, 450);
                frame.setVisible(true);
            }
        });
        initView();
    }

    @Override
    public void initView() {
        init("", "", "");
    }

    /**
     * 根据
     *
     * @param id    用户id
     * @param name  用户名称
     * @param phone 用户手机号
     */
    public void init(String id, String name, String phone) {
        SwingUtilities.invokeLater(() -> {
            List<Record> recordList = recordService.getAllRecordByIdAndName(id, name, phone);
            Object[][] data = new Object[recordList.size()][10];
            for (int i = 0; i < recordList.size(); i++) {
                Record record = recordList.get(i);
                data[i][0] = record.getId();
                data[i][1] = record.getVipId();
                data[i][2] = record.getVip().getName();
                data[i][3] = record.getVip().getPhone();
                data[i][4] = record.getVip().getStatus().getMessage();
                data[i][5] = record.getPrice();
                data[i][6] = record.getCreateTime();
            }
            tablePanel.changeData(data, COLUMN_NAMES);
            removeAll();
            add(searchBar, BorderLayout.NORTH);
            add(tablePanel);
            add(showDetailButton, BorderLayout.SOUTH);
            repaint();
            setVisible(true);
        });
    }
}
