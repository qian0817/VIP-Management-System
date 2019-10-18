package qianlei.view.detail;

import com.alee.laf.panel.WebPanel;
import qianlei.entity.Record;
import qianlei.service.RecordService;
import qianlei.view.component.SearchBar;
import qianlei.view.component.TablePanel;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qianlei
 */
public class ShowRecordPanel extends WebPanel {
    private SearchBar searchBar = new SearchBar(Arrays.asList("姓名", "证件号", "手机号"));
    private TablePanel tablePanel;
    private RecordService goodService = new RecordService();

    public ShowRecordPanel() {
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
            data[i][7] = record.getGood().getPrice();
            data[i][8] = record.getGood().getStatus().getMessage();
            data[i][9] = record.getCreateTime();
        }
        tablePanel = new TablePanel(data, new String[]{"记录编号", "Vip证件号", "vip名称", "vip手机号", "vip状态",
                "商品编号", "商品名称", "商品价格", "商品状态", "创建日期"});
        add(tablePanel);
        repaint();
        setVisible(true);
    }
}
