package qianlei.view.detail;

import qianlei.view.detail.tabledetail.ShowVipTableWithSearchBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 展示vip信息界面
 *
 * @author qianlei
 */
public class ShowVipPanel extends ShowVipTableWithSearchBar implements CanInitPanel {
    public ShowVipPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //双击
                int needClickNumber = 2;
                if (e.getClickCount() >= needClickNumber) {
                    String id = getSelectedVipId();
                    removeAll();
                    //变为修改界面
                    add(new UpdateVipPanel(id));
                    repaint();
                    setVisible(true);
                }
            }
        });
    }
}
