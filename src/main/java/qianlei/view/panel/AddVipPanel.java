package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.panel.detail.InputVipPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 添加Vip界面
 *
 * @author qianlei
 */
public class AddVipPanel extends AbstractCanInitPanel {
    private final VipService vipService = new VipService();
    private final JButton button = new JButton("确认");
    private final InputVipPanel inputVipPanel = new InputVipPanel();

    AddVipPanel() {
        initView();
        button.addActionListener((e) -> addVip());
    }

    private void addVip() {
        Result result;
        try {
            Vip vip = inputVipPanel.getVip();
            result = vipService.addVip(vip);
        } catch (WrongDataException ex) {
            result = new Result(false, ex.getMessage());
        }
        NotificationManager.showInnerNotification(result.getMessage());
        if (result.isSuccess()) {
            initView();
        }
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            inputVipPanel.init();
            removeAll();
            //重置界面
            setLayout(new BorderLayout());
            JPanel panel = new JPanel(new FlowLayout());
            panel.add(button);
            //添加组件
            add(inputVipPanel);
            add(panel, BorderLayout.SOUTH);
            repaint();
            setVisible(true);
        });
    }
}
