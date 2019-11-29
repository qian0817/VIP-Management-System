package qianlei.view.panel.detail;

import qianlei.entity.Good;
import qianlei.service.GoodService;
import qianlei.view.panel.AbstractCanInitPanel;
import qianlei.view.panel.component.SearchBar;
import qianlei.view.panel.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
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
    private final String[] columnName = new String[]{"编号", "名称", "原价", "折扣率", "库存", "制造商", "生产日期", "商品简介"};
    private final TablePanel tablePanel = new TablePanel(new Object[][]{}, columnName);

    public ShowGoodTableWithSearchBar() {
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("编号"), input.get("名称"));
        });
        initView();
    }

    public void init(String id, String name) {
        SwingUtilities.invokeLater(() -> {
            List<Good> goodList;
            goodList = goodService.getAllNormalGoodByIdAndName(id, name);
            Object[][] data = new Object[goodList.size()][8];
            for (int i = 0; i < goodList.size(); i++) {
                Good good = goodList.get(i);
                data[i][0] = good.getId();
                data[i][1] = good.getName();
                data[i][2] = good.getPrice();
                data[i][3] = good.getDiscount();
                data[i][4] = good.getRemain();
                data[i][5] = good.getMaker();
                data[i][6] = good.getCreateTime();
                data[i][7] = good.getIntroduction();
            }
            tablePanel.changeData(data, columnName);
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
        Good good = new Good();
        Object[] objects = tablePanel.getSelectedObject();
        if (objects != null) {
            good.setId((String) objects[0]);
            good.setName((String) objects[1]);
            good.setPrice((BigDecimal) objects[2]);
            good.setDiscount((BigDecimal) objects[3]);
            good.setRemain((long) objects[4]);
            good.setMaker((String) objects[5]);
            good.setCreateTime((Date) objects[6]);
            good.setIntroduction((String) objects[7]);
            return good;
        } else {
            return null;
        }
    }
}
