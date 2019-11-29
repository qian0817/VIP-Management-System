package qianlei.view.panel;

import qianlei.entity.Vip;
import qianlei.service.VipService;
import qianlei.view.MainFrame;
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
            int a = JOptionPane.showConfirmDialog(ShowVipPanel.this, "是否删除该会员");
            if (a == JOptionPane.YES_OPTION) {
                vipService.deleteVipById(vip.getId());
                initView();
            }
        } else {
            JOptionPane.showMessageDialog(ShowVipPanel.this, "请选择需要删除的会员");
        }
    }

    private void updateVip() {
        Vip vip = showVipTable.getSelectedVip();
        if (vip != null) {
            removeAll();
            parent.setTitle("会员信息修改");
            //变为修改界面
            add(new UpdateVipPanel(vip.getId(), parent));
            repaint();
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(ShowVipPanel.this, "请选择需要修改的会员");
        }
    }

    @Override
    public void initView() {
        showVipTable.initView();
    }
}
