package qianlei.view.detail;

import qianlei.entity.Good;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.component.ComboPanel;
import qianlei.view.component.InputPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qianlei
 */
class UpdateGoodPanel extends JPanel {
    UpdateGoodPanel(String id) {
        List<Double> doubleList = new LinkedList<>();
        for (int i = 100; i > 0; i--) {
            doubleList.add(i / 100.0);
        }
        //各组件定义
        InputPanel idInputPanel = new InputPanel("商品编号", "请输入商品编号");
        InputPanel nameInputPanel = new InputPanel("商品名称", "请输入商品名称");
        InputPanel makerInputPanel = new InputPanel("制造商", "请输入制造商");
        InputPanel priceInputPanel = new InputPanel("价格", "请输入价格");
        ComboPanel<Double> discountInputPanel = new ComboPanel<>("折扣", doubleList);
        InputPanel remainInputPanel = new InputPanel("商品库存", "请输入商品库存");
        InputPanel introductionInputPanel = new InputPanel("商品简介", "请输入商品简介");
        InputPanel remarkInputPanel = new InputPanel("备注", "请输入备注");
        GoodService goodService = new GoodService();
        JButton checkButton = new JButton("确认");
        JButton deleteButton = new JButton("删除");

        //各组件初始化
        Good good = goodService.getGoodById(id);
        setLayout(new GridLayout(10, 1));
        add(new JLabel("商品信息修改", JLabel.CENTER));
        idInputPanel.setText(good.getId());
        idInputPanel.setEditable(false);
        add(idInputPanel);
        nameInputPanel.setText(good.getName());
        add(nameInputPanel);
        makerInputPanel.setText(good.getMaker());
        add(makerInputPanel);
        priceInputPanel.setText(good.getPrice().toString());
        add(priceInputPanel);
        discountInputPanel.setSelectItem(good.getDiscount());
        add(discountInputPanel);
        remainInputPanel.setText(String.valueOf(good.getRemain()));
        add(remainInputPanel);
        introductionInputPanel.setText(good.getIntroduction());
        add(introductionInputPanel);
        remarkInputPanel.setText(good.getRemarks());
        add(remarkInputPanel);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(checkButton);
        panel.add(deleteButton);
        add(panel);

        //添加事件
        deleteButton.addActionListener((e) -> {
            goodService.deleteGoodById(id);
            removeAll();
            setLayout(new BorderLayout());
            add(new ShowGoodPanel());
            repaint();
            setVisible(true);
        });
        checkButton.addActionListener((e) -> {
            String name = nameInputPanel.getText();
            String maker = makerInputPanel.getText();
            String price = priceInputPanel.getText();
            Double discount = discountInputPanel.getSelect();
            String remain = remainInputPanel.getText();
            String introduction = introductionInputPanel.getText();
            String remark = remarkInputPanel.getText();
            try {
                goodService.updateGood(id, name, maker, price, discount, remain, introduction, remark);
                JOptionPane.showMessageDialog(UpdateGoodPanel.this, "修改成功", "修改成功", JOptionPane.INFORMATION_MESSAGE);
                removeAll();
                setLayout(new BorderLayout());
                add(new ShowGoodPanel());
                repaint();
                setVisible(true);
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(UpdateGoodPanel.this, ex.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
