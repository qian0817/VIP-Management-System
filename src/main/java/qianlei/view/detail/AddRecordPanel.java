package qianlei.view.detail;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import qianlei.exception.WrongDataException;
import qianlei.service.RecordService;

import java.awt.*;

/**
 * 添加记录界面
 *
 * @author qianlei
 */
public class AddRecordPanel extends WebPanel {
    private ShowGoodPanel showGoodPanel = new ShowGoodPanel();
    private ShowVipPanel showVipPanel = new ShowVipPanel();

    public AddRecordPanel() {
        setLayout(new BorderLayout());
        showGoodPanel.removeMouseListener();
        showVipPanel.removeMouseListener();

        WebPanel center = new WebPanel(new GridLayout(2, 1));

        WebPanel chooseGoodPanel = new WebPanel(new BorderLayout());
        chooseGoodPanel.add(new WebLabel("请选择商品", WebLabel.CENTER), BorderLayout.NORTH);
        chooseGoodPanel.add(showGoodPanel);
        center.add(chooseGoodPanel);

        WebPanel chooseVipPanel = new WebPanel(new BorderLayout());
        chooseVipPanel.add(new WebLabel("请选择VIP用户", WebLabel.CENTER), BorderLayout.NORTH);
        chooseVipPanel.add(showVipPanel);
        center.add(chooseVipPanel);

        add(center);
        WebButton check = new WebButton("确认");
        add(check, BorderLayout.SOUTH);
        check.addActionListener((e) -> {
            RecordService recordService = new RecordService();
            String goodId = showGoodPanel.getSelectedRow();
            String vipId = showVipPanel.getSelectedRow();
            try {
                recordService.addRecord(goodId, vipId);
                WebOptionPane.showMessageDialog(AddRecordPanel.this, "添加成功", "添加成功", WebOptionPane.INFORMATION_MESSAGE);
            } catch (WrongDataException ex) {
                WebOptionPane.showMessageDialog(AddRecordPanel.this, ex.getMessage(), "添加失败", WebOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
