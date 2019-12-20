package qianlei.view.panel;

import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Record;
import qianlei.service.RecordService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qianlei
 */
public class ShowRecordPanel extends AbstractCanInitPanel {
    private static final String[] COLUMN_NAMES = new String[]{"订单号", "会员编号", "会员姓名", "会员手机号", "总价格", "购买日期"};

    private final SearchBar searchBar = new SearchBar(Arrays.asList("会员卡号", "会员姓名", "手机号"));
    private final RecordService recordService = new RecordService();
    private final TablePanel tablePanel = new TablePanel(new Object[][]{}, COLUMN_NAMES);
    private final JButton showDetailButton = new JButton("查看购买的商品");
    private List<Record> recordList;

    ShowRecordPanel() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> search());
        showDetailButton.addActionListener((e) -> showRecordDetail());
        initView();
    }

    /**
     * 显示购买的商品
     */
    private void showRecordDetail() {
        int row = tablePanel.getSelectedRow();
        if (row < 0) {
            NotificationManager.showInnerNotification("请选择需要查看的记录");
        } else {
            JFrame frame = new JFrame();
            frame.add(new ShowRecordDetailPanel(recordList.get(0).getRecordNo()));
            frame.pack();
            frame.setResizable(false);
            frame.setLocation(800, 450);
            frame.setVisible(true);
        }
    }

    /**
     * 搜索
     */
    private void search() {
        Map<String, String> input = searchBar.getInput();
        init(input.get("会员卡号"), input.get("会员姓名"), input.get("手机号"));
    }

    @Override
    public void initView() {
        init("", "", "");
    }

    /**
     * 根据用户id 名称和手机号查找记录
     *
     * @param id    用户id
     * @param name  用户名称
     * @param phone 用户手机号
     */
    public void init(String id, String name, String phone) {
        List<Record> recordList = recordService.getAllRecordByIdAndName(id, name, phone);
        this.recordList = recordList;
        Object[][] data = new Object[recordList.size()][COLUMN_NAMES.length];
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            data[i][0] = record.getRecordNo();
            data[i][1] = record.getVipId();
            data[i][2] = record.getVipName();
            data[i][3] = record.getVipPhone();
            data[i][4] = record.getPrice();
            data[i][5] = record.getCreateTime();
        }
        tablePanel.changeData(data, COLUMN_NAMES);
        removeAll();
        add(searchBar, BorderLayout.NORTH);
        add(tablePanel);
        add(showDetailButton, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }
}
