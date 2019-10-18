package qianlei.view.detail;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.component.ComboPanel;
import qianlei.view.component.InputPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加商品界面
 *
 * @author qianlei
 */
public class AddGoodPanel extends WebPanel {
    private InputPanel idInputPanel = new InputPanel("商品编号", "请输入商品编号");
    private InputPanel nameInputPanel = new InputPanel("商品名称", "请输入商品名称");
    private InputPanel makerInputPanel = new InputPanel("制造商", "请输入制造商");
    private InputPanel priceInputPanel = new InputPanel("价格", "请输入价格");
    private ComboPanel<Double> discountInputPanel;
    private InputPanel remainInputPanel = new InputPanel("商品库存", "请输入商品库存");
    private InputPanel introductionInputPanel = new InputPanel("商品简介", "请输入商品简介");
    private InputPanel remarkInputPanel = new InputPanel("备注", "请输入备注");
    private WebButton button = new WebButton("确认");

    public AddGoodPanel() {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 100; i > 0; i--) {
            doubleList.add(i / 100.0);
        }
        discountInputPanel = new ComboPanel<>("折扣", doubleList);
        button.addActionListener((e) -> {
            GoodService goodService = new GoodService();
            String id = idInputPanel.getText();
            String name = nameInputPanel.getText();
            String maker = makerInputPanel.getText();
            String price = priceInputPanel.getText();
            Double discount = discountInputPanel.getSelect();
            String remain = remainInputPanel.getText();
            String introduction = introductionInputPanel.getText();
            String remark = remarkInputPanel.getText();
            try {
                goodService.addGood(id, name, maker, price, discount, remain, introduction, remark);
                WebOptionPane.showMessageDialog(AddGoodPanel.this, "添加成功", "添加成功", WebOptionPane.INFORMATION_MESSAGE);

            } catch (WrongDataException ex) {
                WebOptionPane.showMessageDialog(AddGoodPanel.this, ex.getMessage(), "添加失败", WebOptionPane.INFORMATION_MESSAGE);
            }
        });
        init();
    }

    public void init() {
        removeAll();
        setLayout(new GridLayout(10, 1));
        add(new WebLabel("商品信息录入", WebLabel.CENTER));
        add(idInputPanel);
        add(nameInputPanel);
        add(makerInputPanel);
        add(priceInputPanel);
        add(discountInputPanel);
        add(remainInputPanel);
        add(introductionInputPanel);
        add(remarkInputPanel);
        WebPanel panel = new WebPanel(new FlowLayout());
        panel.add(button);
        add(panel);
        repaint();
        setVisible(true);
    }
}
