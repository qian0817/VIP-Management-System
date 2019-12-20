package qianlei.view.panel.detail;

import qianlei.entity.Good;
import qianlei.service.GoodService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;
import qianlei.view.panel.AbstractCanInitPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 展示拥有搜索框的商品列表
 *
 * @author qianlei
 */
public class ShowGoodTableWithSearchBar extends AbstractCanInitPanel {
    private static final String[] COLUMN_NAME = new String[]{"商品编号", "商品名称", "商品原价", "商品折扣", "商品库存", "制造厂商", "生产日期", "商品简介", "商品备注"};
    private final GoodService goodService = new GoodService();
    private final SearchBar searchBar = new SearchBar(Arrays.asList("商品编号", "商品名称"));
    private final TablePanel tablePanel = new TablePanel(new Object[][]{}, COLUMN_NAME);
    private List<Good> goodList;

    public ShowGoodTableWithSearchBar() {
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("商品编号"), input.get("商品名称"));
        });
        initView();
    }

    public void init(String id, String name) {
        SwingUtilities.invokeLater(() -> {
            List<Good> goodList;
            goodList = goodService.selectAllNormalGoodByNoAndName(id, name);
            this.goodList = goodList;
            Object[][] data = new Object[goodList.size()][COLUMN_NAME.length];
            for (int i = 0; i < goodList.size(); i++) {
                Good good = goodList.get(i);
                data[i][0] = good.getGoodNo();
                data[i][1] = good.getName();
                data[i][2] = good.getPrice();
                data[i][3] = good.getDiscount();
                data[i][4] = good.getRemain();
                data[i][5] = good.getMaker();
                data[i][6] = good.getCreateTime();
                data[i][7] = good.getIntroduction();
                data[i][8] = good.getRemarks();
            }
            tablePanel.changeData(data, COLUMN_NAME);
            setLayout(new BorderLayout());
            removeAll();
            add(searchBar, BorderLayout.NORTH);
            add(tablePanel);
            repaint();
            validate();
            setVisible(true);
        });
    }

    @Override
    public void initView() {
        init("", "");
    }

    public Good getSelectedGood() {
        int row = tablePanel.getSelectedRow();
        if (row != -1) {
            return goodList.get(row);
        } else {
            return null;
        }
    }
}
