package qianlei.view.panel;

import qianlei.view.MainFrame;
import qianlei.view.panel.tabledetail.ShowVipTableWithSearchBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 展示vip信息界面
 *
 * @author qianlei
 */
class ShowVipPanel extends ShowVipTableWithSearchBar {

    ShowVipPanel(MainFrame parent) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击
                int needClickNumber = 2;
                if (e.getClickCount() >= needClickNumber) {
                    String id = getSelectedVipId();
                    removeAll();
                    parent.setTitle("VIP信息修改");
                    //变为修改界面
                    add(new UpdateVipPanel(id, parent));
                    repaint();
                    setVisible(true);
                }
            }
        });
    }
}
