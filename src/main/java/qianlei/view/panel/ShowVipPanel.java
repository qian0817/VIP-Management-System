package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Vip;
import qianlei.service.VipService;
import qianlei.view.frame.MainFrame;
import qianlei.view.panel.detail.ShowVipTableWithSearchBar;

import javax.swing.*;
import java.awt.*;

/**
 * 展示vip信息界面
 *
 * @author qianlei
 */
class ShowVipPanel extends AbstractCanInitPanel {
    private final ShowVipTableWithSearchBar showVipTable = new ShowVipTableWithSearchBar();
    private final VipService vipService = new VipService();
    private final MainFrame parent;

    ShowVipPanel(MainFrame parent) {
        this.parent = parent;
        //组件
        JPanel bottomPanel = new JPanel();
        JButton updateButton = new JButton("修改");
        JButton deleteButton = new JButton("删除");
        //设置布局
        setLayout(new BorderLayout());
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);
        add(bottomPanel, BorderLayout.SOUTH);
        add(showVipTable);
        //添加事件
        updateButton.addActionListener((e) -> updateVip());
        deleteButton.addActionListener((e) -> deleteGood());
    }

    private void deleteGood() {
        Vip vip = showVipTable.getSelectedVip();
        if (vip != null) {
            vipService.deleteVipById(vip);
            initView();
        } else {
            NotificationManager.showInnerNotification("请选择需要删除的会员");
        }
    }

    private void updateVip() {
        Vip vip = showVipTable.getSelectedVip();
        if (vip != null) {
            removeAll();
            parent.setTitle("会员信息修改");
            //变为修改界面
            add(new UpdateVipPanel(vip.getVipNo(), parent));
            repaint();
            setVisible(true);
        } else {
            NotificationManager.showInnerNotification("请选择需要修改的会员");
        }
    }

    @Override
    public void initView() {
        showVipTable.initView();
    }
}
