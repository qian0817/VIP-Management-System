package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.RecordService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 添加记录界面
 *
 * @author qianlei
 */
public class AddRecordPanel extends JPanel {
    private ShowGoodPanel showGoodPanel = new ShowGoodPanel();
    private ShowVipPanel showVipPanel = new ShowVipPanel();
    private JButton check = new JButton("确认");
    private JLabel showChooseGoodLabel = new JLabel("请选择商品", JLabel.CENTER);
    private JLabel showChooseVipLabel = new JLabel("请选择VIP用户", JLabel.CENTER);

    public AddRecordPanel() {
        setLayout(new BorderLayout());
        check.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(AddRecordPanel.this, "是否添加该记录");
            if (a == JOptionPane.YES_OPTION) {
                RecordService recordService = new RecordService();
                String goodId = showGoodPanel.getSelectedRow();
                String vipId = showVipPanel.getSelectedRow();
                try {
                    recordService.addRecord(goodId, vipId);
                    JOptionPane.showMessageDialog(AddRecordPanel.this, "添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
                    init();
                } catch (WrongDataException ex) {
                    JOptionPane.showMessageDialog(AddRecordPanel.this, ex.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        init();
    }

    /**
     * 初始化界面
     */
    public void init() {
        removeAll();
        JPanel center = new JPanel(new GridLayout(2, 1));
        showGoodPanel.init("", "");
        showVipPanel.init("", "", "");

        showChooseGoodLabel.setText("请选择商品");
        JPanel chooseGoodPanel = new JPanel(new BorderLayout());
        chooseGoodPanel.add(showChooseGoodLabel, BorderLayout.NORTH);
        chooseGoodPanel.add(showGoodPanel);
        center.add(chooseGoodPanel);

        showChooseVipLabel.setText("请选择VIP用户");
        JPanel chooseVipPanel = new JPanel(new BorderLayout());
        chooseVipPanel.add(showChooseVipLabel, BorderLayout.NORTH);
        chooseVipPanel.add(showVipPanel);
        center.add(chooseVipPanel);

        add(center);
        add(check, BorderLayout.SOUTH);
        showVipPanel.changeMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChooseVipLabel.setText("当前选择VIP编号：" + showVipPanel.getSelectedRow());
            }
        });
        showGoodPanel.changeMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChooseGoodLabel.setText("当前选择商品证件号：" + showGoodPanel.getSelectedRow());
            }
        });
        repaint();
        setVisible(true);
    }
}
