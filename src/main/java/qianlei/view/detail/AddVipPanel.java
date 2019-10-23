package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.SexChoosePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 添加Vip界面
 *
 * @author qianlei
 */
public class AddVipPanel extends JPanel {
    private InputPanel nameInputPanel = new InputPanel("姓名", "请输入商品姓名");
    private InputPanel idInputPanel = new InputPanel("证件号", "请输入证件号");
    private SexChoosePanel sexInputPanel = new SexChoosePanel();
    private InputPanel phoneInputPanel = new InputPanel("电话号码", "请输入电话号码");
    private InputPanel addressInputPanel = new InputPanel("联系地址", "请输入联系地址");
    private InputPanel postcodeInputPanel = new InputPanel("邮编", "请输入邮编");
    private JButton button = new JButton("确认");

    public AddVipPanel() {
        button.addActionListener((e) -> {
            VipService vipService = new VipService();
            String id = idInputPanel.getText();
            String name = nameInputPanel.getText();
            String sex = sexInputPanel.getSelect();
            String phone = phoneInputPanel.getText();
            String address = addressInputPanel.getText();
            String postcode = postcodeInputPanel.getText();
            try {
                vipService.addVip(id, name, sex, phone, address, postcode);
                JOptionPane.showMessageDialog(AddVipPanel.this, "添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
                init();
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(AddVipPanel.this, ex.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        init();
    }

    /**
     * 初始化界面
     */
    public void init() {
        removeAll();
        //重置界面
        setLayout(new GridLayout(15, 1));
        idInputPanel.setText("");
        nameInputPanel.setText("");
        sexInputPanel.setSelect("男");
        phoneInputPanel.setText("");
        addressInputPanel.setText("");
        postcodeInputPanel.setText("");
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        //添加组件
        add(new JLabel("VIP信息录入", JLabel.CENTER));
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
        repaint();
        setVisible(true);
    }
}
