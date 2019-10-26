package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.component.ComboPanel;
import qianlei.view.component.DateChoosePanel;
import qianlei.view.component.InputPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
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
    private DateChoosePanel dateChoosePanel = new DateChoosePanel("生产日期");

    public AddGoodPanel() {
        for (double i = 100; i > 0; i--) {
            doubleList.add(i * 1.0 / 100);
        }
        discountInputPanel = new ComboPanel<>("折扣", doubleList);
        button.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(AddGoodPanel.this, "是否添加该商品");
            if (a == JOptionPane.YES_OPTION) {
                GoodService goodService = new GoodService();
                String id = idInputPanel.getText();
                String name = nameInputPanel.getText();
                String maker = makerInputPanel.getText();
                String price = priceInputPanel.getText();
                Double discount = discountInputPanel.getSelect();
                String remain = remainInputPanel.getText();
                String introduction = introductionInputPanel.getText();
                String remark = remarkInputPanel.getText();
                Date createTime = dateChoosePanel.getSelectDate();
                try {
                    goodService.addGood(id, name, maker, createTime, price, discount, remain, introduction, remark);
                    JOptionPane.showMessageDialog(AddGoodPanel.this, "添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
                    init();
                } catch (WrongDataException ex) {
                    JOptionPane.showMessageDialog(AddGoodPanel.this, ex.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        init();
    }

    public void init() {
        removeAll();
        setLayout(new GridLayout(21, 1));
        //各组件赋初值
        idInputPanel.setText("");
        nameInputPanel.setText("");
        makerInputPanel.setText("");
        priceInputPanel.setText("");
        remainInputPanel.setText("");
        introductionInputPanel.setText("");
        remarkInputPanel.setText("");
        discountInputPanel = new ComboPanel<>("折扣", doubleList);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        add(new JLabel("商品信息录入", JLabel.CENTER));
        add(new JLabel());
        add(idInputPanel);
        add(new JLabel());
        add(nameInputPanel);
        add(new JLabel());
        add(makerInputPanel);
        add(new JLabel());
        add(dateChoosePanel);
        add(new JLabel());
        add(priceInputPanel);
        add(new JLabel());
        add(discountInputPanel);
        add(new JLabel());
        add(remainInputPanel);
        add(new JLabel());
        add(introductionInputPanel);
        add(new JLabel());
        add(remarkInputPanel);
        add(new JLabel());
        add(panel);
        repaint();
        setVisible(true);
    }
}
