package qianlei.view.panel.detail;

import qianlei.entity.Vip;
import qianlei.service.VipService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;
import qianlei.view.panel.AbstractCanInitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 展示带有搜索框的VIP列表
 *
 * @author qianlei
 */
public class ShowVipTableWithSearchBar extends AbstractCanInitPanel {
    private final VipService vipService = new VipService();
    private final SearchBar searchBar = new SearchBar(Arrays.asList("姓名", "卡号", "手机号"));
    private static final String[] COLUMN_NAMES = new String[]{"卡号", "姓名", "性别", "手机号码", "联系地址", "邮箱", "创建时间"};
    private final TablePanel tablePanel = new TablePanel(new Object[][]{}, COLUMN_NAMES);
    private List<Vip> vipList;

    public ShowVipTableWithSearchBar() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("卡号"), input.get("姓名"), input.get("手机号"));
        });
        initView();
    }

    public void init(String id, String name, String phone) {
        SwingUtilities.invokeLater(() -> {
            List<Vip> vipList = vipService.getAllNormalVipByIdAndNameAndPhone(id, name, phone);
            this.vipList = vipList;
            Object[][] data = new Object[vipList.size()][COLUMN_NAMES.length];
            for (int i = 0; i < vipList.size(); i++) {
                Vip good = vipList.get(i);
                data[i][0] = good.getVipNo();
                data[i][1] = good.getName();
                data[i][2] = good.getSex();
                data[i][3] = good.getPhone();
                data[i][4] = good.getAddress();
                data[i][5] = good.getEmail();
                data[i][6] = good.getCreateTime();
            }
            tablePanel.changeData(data, COLUMN_NAMES);
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
        init("", "", "");
    }

    @Override
    public void addMouseListener(MouseListener l) {
        tablePanel.addMouseListener(l);
    }

    /**
     * 获取选中的会员
     *
     * @return 选中的会员
     */
    public Vip getSelectedVip() {
        int row = tablePanel.getSelectedRow();
        if (row == -1) {
            return null;
        }
        return vipList.get(row);
    }
}
