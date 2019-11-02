package qianlei.view.panel.tabledetail;

import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.panel.AbstractCanInitPanel;
import qianlei.view.panel.tabledetail.component.SearchBar;
import qianlei.view.panel.tabledetail.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 展示带有搜索框的VIP列表
 *
 * @author qianlei
 */
public class ShowVipTableWithSearchBar extends AbstractCanInitPanel {
    private final VipService vipService = new VipService();
    private final SearchBar searchBar = new SearchBar(Arrays.asList("姓名", "证件号", "手机号"));
    private TablePanel tablePanel;
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
        removeAll();
        add(searchBar, BorderLayout.NORTH);
        List<Vip> vipList;
        try {
            vipList = vipService.getAllNormalVipByIdAndNameAndPhone(id, name, phone);
        } catch (WrongDataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            vipList = new LinkedList<>();
        }
        Object[][] data = new Object[vipList.size()][7];
        for (int i = 0; i < vipList.size(); i++) {
            Vip good = vipList.get(i);
            data[i][0] = good.getId();
            data[i][1] = good.getName();
            data[i][2] = good.getSex();
            data[i][3] = good.getPhone();
            data[i][4] = good.getAddress();
            data[i][5] = good.getPostcode();
            data[i][6] = good.getCreateTime();
        }
        tablePanel = new TablePanel(data, new String[]{"证件号", "姓名", "性别", "手机号码", "联系地址", "邮编", "创建时间"});
        add(tablePanel);
        tablePanel.addMouseListener(mouseListener);
        repaint();
        setVisible(true);
    }

    @Override
    public void initView() {
        init("", "", "");
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        tablePanel.addMouseListener(mouseListener);
        this.mouseListener = mouseListener;
    }

    /**
     * 获取选中的id
     *
     * @return 选中的id
     */
    public String getSelectedVipId() {
        return (String) tablePanel.getSelectedId();
    }
}
