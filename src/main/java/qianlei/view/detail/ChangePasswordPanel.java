package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;
import qianlei.view.component.VerifyCodePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆密码修改界面
 *
 * @author qianlei
 */
public class ChangePasswordPanel extends JPanel {
    private UserService userService = new UserService();
    private PasswordPanel recheckPasswordPanel = new PasswordPanel("确认密码", "请再次填写密码");
    private VerifyCodePanel verifyCodePanel = new VerifyCodePanel();
    public ChangePasswordPanel() {
        setLayout(new GridLayout(11, 1));
        InputPanel nameInputPanel = new InputPanel("用户名", "");
        nameInputPanel.setEditable(false);
        PasswordPanel passwordPanel = new PasswordPanel("密码", "请输入修改后密码");
        JButton check = new JButton("确认");
        nameInputPanel.setText(userService.getCurUser().getUsername());
        JPanel button = new JPanel(new FlowLayout());
        button.add(check);
        check.addActionListener((e) -> {
            String name = nameInputPanel.getText();
            String password = passwordPanel.getText();
            String recheckPassword = recheckPasswordPanel.getText();
            if (verifyCodePanel.isVerifyCodeWrong()) {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, "验证码错误", "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else if (!recheckPassword.equals(password)) {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, "两次填写的密码不一致", "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                userService.changePassword(name, password);
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, "修改密码成功", "修改密码成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, ex.getMessage(), "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(new JLabel("密码修改", JLabel.CENTER));
        add(new JLabel());
        add(nameInputPanel);
        add(new JLabel());
        add(passwordPanel);
        add(new JLabel());
        add(recheckPasswordPanel);
        add(new JLabel());
        add(verifyCodePanel);
        add(new JLabel());
        add(button);
    }
}
