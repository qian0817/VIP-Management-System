package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.component.ComboPanel;
import qianlei.view.component.InputPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加商品界面
 *
 * @author qianlei
 */
public class AddGoodPanel extends JPanel {
    private InputPanel idInputPanel = new InputPanel("商品编号", "请输入商品编号");
    private InputPanel nameInputPanel = new InputPanel("商品名称", "请输入商品名称");
    private InputPanel makerInputPanel = new InputPanel("制造商", "请输入制造商");
    private InputPanel priceInputPanel = new InputPanel("价格", "请输入价格");
    private ComboPanel<Double> discountInputPanel;
    private InputPanel remainInputPanel = new InputPanel("商品库存", "请输入商品库存");
    private InputPanel introductionInputPanel = new InputPanel("商品简介", "请输入商品简介");
    private InputPanel remarkInputPanel = new InputPanel("备注", "请输入备注");
    private JButton button = new JButton("确认");
    private List<Double> doubleList = new ArrayList<>();

    public AddGoodPanel() {
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
                JOptionPane.showMessageDialog(AddGoodPanel.this, "添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
                init();
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(AddGoodPanel.this, ex.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        init();
    }

    public void init() {
        removeAll();
        setLayout(new GridLayout(10, 1));
        add(new JLabel("商品信息录入", JLabel.CENTER));
        idInputPanel.setText("");
        add(idInputPanel);
        nameInputPanel.setText("");
        add(nameInputPanel);
        makerInputPanel.setText("");
        add(makerInputPanel);
        priceInputPanel.setText("");
        add(priceInputPanel);
        discountInputPanel = new ComboPanel<>("折扣", doubleList);
        add(discountInputPanel);
        remainInputPanel.setText("");
        add(remainInputPanel);
        introductionInputPanel.setText("");
        add(introductionInputPanel);
        remarkInputPanel.setText("");
        add(remarkInputPanel);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        add(panel);
        repaint();
        setVisible(true);
    }
}
