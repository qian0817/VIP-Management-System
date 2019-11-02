package qianlei.view.panel;

import qianlei.entity.Record;
import qianlei.service.RecordService;
import qianlei.view.panel.tabledetail.component.SearchBar;
import qianlei.view.panel.tabledetail.component.TablePanel;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qianlei
 */
public class ShowRecordPanel extends AbstractCanInitPanel {
    private final SearchBar searchBar = new SearchBar(Arrays.asList("姓名", "证件号", "手机号"));
    private final RecordService goodService = new RecordService();

    ShowRecordPanel() {
        setLayout(new BorderLayout());
        searchBar.addActionListener((e) -> {
            Map<String, String> input = searchBar.getInput();
            init(input.get("证件号"), input.get("姓名"), input.get("手机号"));
        });
        initView();
    }

    @Override
    public void initView() {
        init("", "", "");
    }

    public void init(String id, String name, String phone) {
        removeAll();
        add(searchBar, BorderLayout.NORTH);
        List<Record> recordList = goodService.getAllRecordByIdAndName(id, name, phone);
        Object[][] data = new Object[recordList.size()][10];
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            data[i][0] = record.getId();
            data[i][1] = record.getVipId();
            data[i][2] = record.getVip().getName();
            data[i][3] = record.getVip().getPhone();
            data[i][4] = record.getVip().getStatus().getMessage();
            data[i][5] = record.getGoodId();
            data[i][6] = record.getGood().getName();
            data[i][7] = record.getPrice();
            data[i][8] = record.getGood().getStatus().getMessage();
            data[i][9] = record.getCreateTime();
        }
        TablePanel tablePanel = new TablePanel(data, new String[]{"记录编号", "VIP证件号", "VIP名称", "VIP手机号", "VIP状态",
                "商品编号", "商品名称", "购买时价格", "商品状态", "创建日期"});
        add(tablePanel);
        repaint();
        setVisible(true);
    }
}
