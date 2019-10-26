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
        Vip vip = vipService.getVipById(id);
        setLayout(new GridLayout(15, 1));
        idInputPanel.setText(vip.getId());
        idInputPanel.setEditable(false);
        nameInputPanel.setText(vip.getName());
        sexInputPanel.setSelect(vip.getSex());
        phoneInputPanel.setText(vip.getPhone());
        addressInputPanel.setText(vip.getAddress());
        postcodeInputPanel.setText(String.valueOf(vip.getPostcode()));
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(checkButton);
        panel.add(deleteButton);

        add(new JLabel("VIP信息修改", JLabel.CENTER));
        add(new JLabel());
        add(idInputPanel);
        add(new JLabel());
        add(nameInputPanel);
        add(new JLabel());
        add(sexInputPanel);
        add(new JLabel());
        add(phoneInputPanel);
        add(new JLabel());
        add(addressInputPanel);
        add(new JLabel());
        add(postcodeInputPanel);
        add(new JLabel());
        add(panel);

        //添加事件
        checkButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateVipPanel.this, "是否修改该VIP");
            if (a == JOptionPane.YES_OPTION) {
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
            }
        });
        deleteButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateVipPanel.this, "是否删除该VIP");
            if (a == JOptionPane.YES_OPTION) {
                vipService.deleteVipById(id);
                removeAll();
                setLayout(new BorderLayout());
                add(new ShowVipPanel());
                repaint();
                setVisible(true);
            }
        });
    }
}
