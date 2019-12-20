package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.frame.MainFrame;
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
        Result result;
        Vip vip;
        try {
            vip = inputVipPanel.getVip();
            result = vipService.updateVip(vip);
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        NotificationManager.showInnerNotification(result.getMessage());
        if (result.isSuccess()) {
            changeToShowVipPanel();
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
