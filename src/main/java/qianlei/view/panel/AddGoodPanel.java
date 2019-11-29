package qianlei.view.panel;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.panel.detail.InputGoodPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 添加商品界面
 *
 * @author qianlei
 */
public class AddGoodPanel extends AbstractCanInitPanel {
    private final InputGoodPanel inputGoodPanel = new InputGoodPanel();
    private final GoodService goodService = new GoodService();
    private final JPanel panel = new JPanel(new FlowLayout());

    AddGoodPanel() {
        JButton check = new JButton("确认");
        panel.add(check);
        initView();
        check.addActionListener((e) -> addGood());
    }

    /**
     * 添加商品
     */
    private void addGood() {
        int a = JOptionPane.showConfirmDialog(AddGoodPanel.this, "是否添加该商品");
        if (a == JOptionPane.YES_OPTION) {
            Result result;
            try {
                Good good = inputGoodPanel.getGood();
                goodService.addGood(good);
                result = new Result(true, "添加成功");
            } catch (WrongDataException e) {
                result = new Result(false, e.getMessage());
            }
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(AddGoodPanel.this, result.getMessage(), "添加成功", JOptionPane.INFORMATION_MESSAGE);
                initView();
            } else {
                JOptionPane.showMessageDialog(AddGoodPanel.this, result.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            inputGoodPanel.init();
            removeAll();
            setLayout(new BorderLayout());
            add(inputGoodPanel);
            add(panel, BorderLayout.SOUTH);
            repaint();
            setVisible(true);
        });
    }
}
