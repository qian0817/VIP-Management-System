package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
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
        Result result;
        try {
            Good good = inputGoodPanel.getGood();
            result = goodService.addGood(good);
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        NotificationManager.showInnerNotification(result.getMessage());
        if (result.isSuccess()) {
            initView();
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
