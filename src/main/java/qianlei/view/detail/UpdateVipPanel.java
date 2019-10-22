package qianlei.view.detail;

import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.SexChoosePanel;

import javax.swing.*;
import java.awt.*;

class UpdateVipPanel extends JPanel {
    UpdateVipPanel(String id) {
        //各组件
        VipService vipService = new VipService();
        InputPanel nameInputPanel = new InputPanel("姓名", "请输入商品姓名");
        InputPanel idInputPanel = new InputPanel("证件号", "请输入证件号");
        SexChoosePanel sexInputPanel = new SexChoosePanel();
        InputPanel phoneInputPanel = new InputPanel("电话号码", "请输入电话号码");
        InputPanel addressInputPanel = new InputPanel("联系地址", "请输入联系地址");
        InputPanel postcodeInputPanel = new InputPanel("邮编", "请输入邮编");
        JButton checkButton = new JButton("确认");
        JButton deleteButton = new JButton("删除");

        //组件初始化
        Vip vip = vipService.getAllById(id);
        setLayout(new GridLayout(8, 1));
        add(new JLabel("VIP信息修改", JLabel.CENTER));
        idInputPanel.setText(vip.getId());
        idInputPanel.setEditable(false);
        add(idInputPanel);
        nameInputPanel.setText(vip.getName());
        add(nameInputPanel);
        sexInputPanel.setSelect(vip.getSex());
        add(sexInputPanel);
        phoneInputPanel.setText(vip.getPhone());
        add(phoneInputPanel);
        addressInputPanel.setText(vip.getAddress());
        add(addressInputPanel);
        postcodeInputPanel.setText(String.valueOf(vip.getPostcode()));
        add(postcodeInputPanel);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(checkButton);
        panel.add(deleteButton);
        add(panel);

        //添加事件
        checkButton.addActionListener((e) -> {
            String name = nameInputPanel.getText();
            String sex = sexInputPanel.getSelect();
            String phone = phoneInputPanel.getText();
            String address = addressInputPanel.getText();
            String postcode = postcodeInputPanel.getText();
            try {
                vipService.updateVip(id, name, sex, phone, address, postcode);
                JOptionPane.showMessageDialog(UpdateVipPanel.this, "修改成功", "修改成功", JOptionPane.INFORMATION_MESSAGE);
                removeAll();
                setLayout(new BorderLayout());
                add(new ShowVipPanel());
                repaint();
                setVisible(true);
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(UpdateVipPanel.this, ex.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        deleteButton.addActionListener((e) -> {
            vipService.deleteById(id);
            removeAll();
            setLayout(new BorderLayout());
            add(new ShowVipPanel());
            repaint();
            setVisible(true);
        });
    }
}
