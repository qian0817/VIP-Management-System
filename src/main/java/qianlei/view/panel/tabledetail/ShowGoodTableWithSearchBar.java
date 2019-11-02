package qianlei.view.panel.tabledetail;

import qianlei.entity.Good;
import qianlei.service.GoodService;
import qianlei.view.panel.AbstractCanInitPanel;
import qianlei.view.panel.tabledetail.component.SearchBar;
import qianlei.view.panel.tabledetail.component.TablePanel;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 展示拥有搜索框的商品列表
 *
 * @author qianlei
 */
public class ShowGoodTableWithSearchBar extends AbstractCanInitPanel {
    private final SearchBar searchBar = new SearchBar(Arrays.asList("编号", "名称"));
    private final GoodService goodService = new GoodService();
    private TablePanel tablePanel;
    private MouseListener mouseListener;

    public ShowGoodTableWithSearchBar() {
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("编号"), input.get("名称"));
        });
        initView();
    }

    public void init(String id, String name) {
        setLayout(new BorderLayout());
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

    @Override
    public void initView() {
        init("", "");
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        tablePanel.addMouseListener(mouseListener);
        this.mouseListener = mouseListener;
    }

    public String getSelectedGoodId() {
        return (String) tablePanel.getSelectedId();
    }
}
