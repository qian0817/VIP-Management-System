package qianlei.view.panel.detail;

import qianlei.entity.Vip;
import qianlei.service.VipService;
import qianlei.view.panel.AbstractCanInitPanel;
import qianlei.view.panel.component.SearchBar;
import qianlei.view.panel.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Date;
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
    private String[] columnNames = new String[]{"证件号", "姓名", "性别", "手机号码", "联系地址", "邮箱", "创建时间"};
    private TablePanel tablePanel = new TablePanel(new Object[][]{}, columnNames);
    private MouseListener mouseListener;

    public ShowVipTableWithSearchBar() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("证件号"), input.get("姓名"), input.get("手机号"));
        });
        initView();
    }

    public void init(String id, String name, String phone) {
        SwingUtilities.invokeLater(() -> {
            List<Vip> vipList = vipService.getAllNormalVipByIdAndNameAndPhone(id, name, phone);
            Object[][] data = new Object[vipList.size()][7];
            for (int i = 0; i < vipList.size(); i++) {
                Vip good = vipList.get(i);
                data[i][0] = good.getId();
                data[i][1] = good.getName();
                data[i][2] = good.getSex();
                data[i][3] = good.getPhone();
                data[i][4] = good.getAddress();
                data[i][5] = good.getEmail();
                data[i][6] = good.getCreateTime();
            }
            tablePanel.changeData(data, columnNames);
            removeAll();
            add(searchBar, BorderLayout.NORTH);
            add(tablePanel);
            tablePanel.addMouseListener(mouseListener);
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
        mouseListener = l;
        tablePanel.addMouseListener(mouseListener);
    }

    /**
     * 获取选中的会员
     *
     * @return 选中的会员
     */
    public Vip getSelectedVip() {
        Object[] objects = tablePanel.getSelectedObject();
        if (objects == null) {
            return null;
        }
        Vip vip = new Vip();
        vip.setId((String) objects[0]);
        vip.setName((String) objects[1]);
        vip.setSex((String) objects[2]);
        vip.setPhone((String) objects[3]);
        vip.setAddress((String) objects[4]);
        vip.setEmail((String) objects[5]);
        vip.setCreateTime((Date) objects[6]);
        return vip;
    }
}
