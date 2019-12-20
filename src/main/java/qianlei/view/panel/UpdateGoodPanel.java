package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.frame.MainFrame;
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

    UpdateGoodPanel(String goodNo, MainFrame parent) {
        this.parent = parent;
        SwingUtilities.invokeLater(() -> {
            setLayout(new BorderLayout());
            initInputGoodPanel(goodNo);
            addComponent();
            checkButton.addActionListener((e) -> updateGood());
        });
    }

    /**
     * 修改商品
     */
    private void updateGood() {
        Result result;
        try {
            Good good = inputGoodPanel.getGood();
            result = goodService.updateGood(good);
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        NotificationManager.showInnerNotification(result.getMessage());
        if (result.isSuccess()) {
            changeToShowGoodPanel();
        }
    }

    /**
     * 初始化数据
     *
     * @param goodNo 商品编号
     */
    private void initInputGoodPanel(String goodNo) {
        Good good = goodService.getGoodByGoodNo(goodNo);
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
