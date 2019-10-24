package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.RecordService;

import javax.swing.*;
import java.awt.*;

/**
 * 添加记录界面
 *
 * @author qianlei
 */
public class AddRecordPanel extends JPanel {
    private ShowGoodPanel showGoodPanel = new ShowGoodPanel();
    private ShowVipPanel showVipPanel = new ShowVipPanel();
    private JButton check = new JButton("确认");

    public AddRecordPanel() {
        setLayout(new BorderLayout());


        check.addActionListener((e) -> {
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

        JPanel chooseGoodPanel = new JPanel(new BorderLayout());
        chooseGoodPanel.add(new JLabel("请选择商品", JLabel.CENTER), BorderLayout.NORTH);
        chooseGoodPanel.add(showGoodPanel);
        center.add(chooseGoodPanel);

        JPanel chooseVipPanel = new JPanel(new BorderLayout());
        chooseVipPanel.add(new JLabel("请选择VIP用户", JLabel.CENTER), BorderLayout.NORTH);
        chooseVipPanel.add(showVipPanel);
        center.add(chooseVipPanel);

        add(center);
        add(check, BorderLayout.SOUTH);
        showGoodPanel.removeMouseListener();
        showVipPanel.removeMouseListener();
        repaint();
        setVisible(true);
    }
}
