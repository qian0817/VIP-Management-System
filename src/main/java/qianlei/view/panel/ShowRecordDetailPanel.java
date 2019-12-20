package qianlei.view.panel;

import qianlei.entity.RecordDetail;
import qianlei.service.RecordService;
import qianlei.view.component.TablePanel;

import javax.swing.*;
import java.util.List;

/**
 * 展示
 *
 * @author qianlei
 */
class ShowRecordDetailPanel extends JPanel {
    private static final String[] COLUMN_NAME = new String[]{"商品编号", "商品名称", "商品单价", "购买数量", "商品总价"};

    ShowRecordDetailPanel(String recordId) {
        final RecordService recordService = new RecordService();
        final List<RecordDetail> recordDetails = recordService.getAllRecordDetailByRecordId(recordId);
        final Object[][] objects = new Object[recordDetails.size()][COLUMN_NAME.length];
        for (int i = 0; i < recordDetails.size(); i++) {
            objects[i][0] = recordDetails.get(i).getGoodNo();
            objects[i][1] = recordDetails.get(i).getGoodName();
            objects[i][2] = recordDetails.get(i).getPrice();
            objects[i][3] = recordDetails.get(i).getNumber();
            objects[i][4] = recordDetails.get(i).getTotalPrice();
        }
        final TablePanel tablePanel = new TablePanel(objects, COLUMN_NAME);
        add(tablePanel);
    }
}
