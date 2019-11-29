package qianlei.view.panel;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.MainFrame;
import qianlei.view.panel.detail.InputGoodPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author qianlei
 */
class UpdateGoodPanel extends JPanel {
    private final InputGoodPanel inputGoodPanel = new InputGoodPanel();
    private final JPanel checkPanel = new JPanel(new FlowLayout());
    private final JButton checkButton = new JButton("修改");
    private final GoodService goodService = new GoodService();
    private final MainFrame parent;

    UpdateGoodPanel(String id, MainFrame parent) {
        this.parent = parent;
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());
            initInputGoodPanel(id);
            addComponent();
            checkButton.addActionListener((e) -> updateGood());
        });
    }

    /**
     * 修改商品
     */
    private void updateGood() {
        int a = JOptionPane.showConfirmDialog(UpdateGoodPanel.this, "是否修改该商品");
        if (a == JOptionPane.YES_OPTION) {
            Result result;
            try {
                Good good = inputGoodPanel.getGood();
                goodService.updateGood(good);
                result = new Result(true, "修改成功");
            } catch (WrongDataException e) {
                result = new Result(false, e.getMessage());
            }
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(UpdateGoodPanel.this, result.getMessage(), "修改成功", JOptionPane.INFORMATION_MESSAGE);
                changeToShowGoodPanel();
            } else {
                JOptionPane.showMessageDialog(UpdateGoodPanel.this, result.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 初始化数据
     *
     * @param id 商品id
     */
    private void initInputGoodPanel(String id) {
        Good good = goodService.getGoodById(id);
        inputGoodPanel.init(good);
        inputGoodPanel.setEditable(0, false);
    }

    /**
     * 回到showGoodPanel
     */
    private void changeToShowGoodPanel() {
        removeAll();
        setLayout(new BorderLayout());
        add(new ShowGoodPanel(parent));
        repaint();
        setVisible(true);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        checkPanel.add(checkButton);
        add(inputGoodPanel);
        add(checkPanel, BorderLayout.SOUTH);
    }
}
