package qianlei.view.panel;

import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.MainFrame;
import qianlei.view.panel.detail.InputVipPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 修改VIP信息界面
 *
 * @author qianlei
 */
class UpdateVipPanel extends JPanel {
    private final VipService vipService = new VipService();
    private final InputVipPanel inputVipPanel = new InputVipPanel();
    private final JButton checkButton = new JButton("修改");
    private final JPanel panel = new JPanel(new FlowLayout());
    private final MainFrame parent;

    UpdateVipPanel(String id, MainFrame parent) {
        this.parent = parent;
        //组件初始化
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());
            initInputVipPanel(id);
            addComponent();
            checkButton.addActionListener((e) -> updateVip());
        });
    }

    /**
     * 修改VIP
     */
    private void updateVip() {
        int a = JOptionPane.showConfirmDialog(UpdateVipPanel.this, "是否修改该VIP");
        if (a == JOptionPane.YES_OPTION) {
            Result result;
            Vip vip;
            try {
                vip = inputVipPanel.getVip();
                vipService.updateVip(vip);
                result = new Result(true, "修改成功");
            } catch (WrongDataException e) {
                result = new Result(true, e.getMessage());
            }
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(UpdateVipPanel.this, result.getMessage(), "修改成功", JOptionPane.INFORMATION_MESSAGE);
                changeToShowVipPanel();
            } else {
                JOptionPane.showMessageDialog(UpdateVipPanel.this, result.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        panel.add(checkButton);
        add(inputVipPanel);
        add(panel, BorderLayout.SOUTH);
    }

    /**
     * 初始化主界面
     *
     * @param id vipId
     */
    private void initInputVipPanel(String id) {
        Vip vip = vipService.getVipById(id);
        inputVipPanel.init(vip);
        inputVipPanel.setEditable(0, false);
    }

    /**
     * 回到showVipPanel
     */
    private void changeToShowVipPanel() {
        removeAll();
        setLayout(new BorderLayout());
        add(new ShowVipPanel(parent));
        repaint();
        setVisible(true);
    }
}
