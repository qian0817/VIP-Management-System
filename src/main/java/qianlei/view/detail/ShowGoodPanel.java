package qianlei.view.detail;

import qianlei.entity.Good;
import qianlei.service.GoodService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 展示商品界面
 *
 * @author qianlei
 */
public class ShowGoodPanel extends JPanel {
    private SearchBar searchBar = new SearchBar(Arrays.asList("编号", "名称"));
    private TablePanel tablePanel;
    private GoodService goodService = new GoodService();

    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //是否双击
            int needClickNumber = 2;
            if (e.getClickCount() >= needClickNumber) {
                String id = (String) tablePanel.getRowByIndex(e.getPoint(), 0);
                removeAll();
                add(new UpdateGoodPanel(id));
                repaint();
                setVisible(true);
            }
        }
    };

    public ShowGoodPanel() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("编号"), input.get("名称"));
        });
        init("", "");
    }

    public void init(String id, String name) {
        removeAll();
        add(searchBar, BorderLayout.NORTH);
        List<Good> goodList = goodService.getAllNormalGoodByIdAndName(id, name);
        Object[][] data = new Object[goodList.size()][8];
        for (int i = 0; i < goodList.size(); i++) {
            Good good = goodList.get(i);
            data[i][0] = good.getId();
            data[i][1] = good.getName();
            data[i][2] = good.getMaker();
            data[i][3] = good.getCreateTime();
            data[i][4] = good.getPrice();
            data[i][5] = good.getDiscount();
            data[i][6] = good.getRemain();
            data[i][7] = good.getIntroduction();
        }
        tablePanel = new TablePanel(data, new String[]{"编号", "名称", "制造商", "生产日期", "价格", "折扣率", "库存", "商品简介"});
        add(tablePanel);
        tablePanel.addMouseListener(mouseListener);
        repaint();
        setVisible(true);
    }

    void changeMouseListener(MouseListener listener) {
        tablePanel.removeMouseListener(mouseListener);
        mouseListener = listener;
        tablePanel.addMouseListener(listener);
    }

    String getSelectedRow() {
        return (String) tablePanel.getSelectedId();
    }
}
