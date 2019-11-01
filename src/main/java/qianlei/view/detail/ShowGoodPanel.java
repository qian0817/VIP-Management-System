package qianlei.view.detail;

import qianlei.view.detail.tabledetail.ShowGoodTableWithSearchBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 展示商品界面
 *
 * @author qianlei
 */
public class ShowGoodPanel extends ShowGoodTableWithSearchBar implements CanInitPanel {
    public ShowGoodPanel() {
        addAction();
    }

    /**
     * 添加事件
     */
    private void addAction() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //是否双击
                int needClickNumber = 2;
                if (e.getClickCount() >= needClickNumber) {
                    String id = getSelectedGoodId();
                    removeAll();
                    //切换修改商品界面
                    add(new UpdateGoodPanel(id));
                    repaint();
                    setVisible(true);
                }
            }
        });
    }
}
