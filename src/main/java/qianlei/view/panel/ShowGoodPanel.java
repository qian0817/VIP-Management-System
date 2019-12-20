package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.service.GoodService;
import qianlei.view.frame.MainFrame;
import qianlei.view.panel.detail.ShowGoodTableWithSearchBar;

import javax.swing.*;
import java.awt.*;

/**
 * 展示商品界面
 *
 * @author qianlei
 */
class ShowGoodPanel extends AbstractCanInitPanel {
    private final ShowGoodTableWithSearchBar showGoodTable = new ShowGoodTableWithSearchBar();
    private final GoodService goodService = new GoodService();
    private final MainFrame parent;

    ShowGoodPanel(MainFrame parent) {
        final JPanel bottomPanel = new JPanel();
        final JButton updateButton = new JButton("修改");
        final JButton deleteButton = new JButton("删除");

        this.parent = parent;
        setLayout(new BorderLayout());
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);
        add(showGoodTable);
        updateButton.addActionListener((e) -> updateGood());
        deleteButton.addActionListener((e) -> deleteGood());
    }

    /**
     * 进入选中商品的修改界面
     */
    private void updateGood() {
        Good good = showGoodTable.getSelectedGood();
        //判断有无商品选中
        if (good != null) {
            removeAll();
            parent.setTitle("商品信息修改");
            //切换修改商品界面
            add(new UpdateGoodPanel(good.getGoodNo(), parent));
            repaint();
            setVisible(true);
        } else {
            NotificationManager.showInnerNotification("请选择需要修改的商品");
        }
    }

    /**
     * 删除选中的商品
     */
    private void deleteGood() {
        Good good = showGoodTable.getSelectedGood();
        //判断有无商品选中
        if (good != null) {
            Result result = goodService.deleteGood(good);
            NotificationManager.showInnerNotification(result.getMessage());
            if (result.isSuccess()) {
                showGoodTable.initView();
            }
        } else {
            NotificationManager.showInnerNotification("请选择需要删除的商品");
        }
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(showGoodTable::initView);
    }
}
