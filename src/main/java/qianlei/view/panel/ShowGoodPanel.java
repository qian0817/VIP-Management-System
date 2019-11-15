package qianlei.view.panel;

import qianlei.view.MainFrame;
import qianlei.view.panel.tabledetail.ShowGoodTableWithSearchBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 展示商品界面
 *
 * @author qianlei
 */
class ShowGoodPanel extends ShowGoodTableWithSearchBar {

    ShowGoodPanel(MainFrame parent) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //是否双击
                int needClickNumber = 2;
                if (e.getClickCount() >= needClickNumber) {
                    String id = getSelectedGoodId();
                    removeAll();
                    parent.setTitle("商品信息修改");
                    //切换修改商品界面
                    add(new UpdateGoodPanel(id, parent));
                    repaint();
                    setVisible(true);
                }
            }
        });
    }

    /**
     * 添加事件
     */
    private void addAction() {

    }
}
