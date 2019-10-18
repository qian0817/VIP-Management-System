package qianlei.view.detail;

import com.alee.laf.panel.WebPanel;
import qianlei.entity.Vip;
import qianlei.service.VipService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ShowVipPanel extends WebPanel {
    private SearchBar searchBar = new SearchBar(Arrays.asList("姓名", "证件号", "手机号"));
    private TablePanel tablePanel;
    private VipService vipService = new VipService();
    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() >= 2) {
                //TODO 修改界面
                System.out.println(tablePanel.getRowFirst(e.getPoint()));
            }
        }
    };

    public ShowVipPanel() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("证件号"), input.get("姓名"), input.get("手机号"));
        });
        init("", "", "");
    }

    public void init(String id, String name, String phone) {
        removeAll();
        add(searchBar, BorderLayout.NORTH);
        List<Vip> vipList = vipService.getAllNormalVipByIdAndNameAndPhone(id, name, phone);
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
        tablePanel.addMouseListener(mouseAdapter);
        repaint();
        setVisible(true);
    }

    public void removeMouseListener() {
        tablePanel.removeMouseListener(mouseAdapter);
    }

    public String getSelectedRow() {
        return (String) tablePanel.getSelectedId();
    }
}
