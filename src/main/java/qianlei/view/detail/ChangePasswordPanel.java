package qianlei.view.detail;

import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆密码修改界面
 *
 * @author qianlei
 */
public class ChangePasswordPanel extends JPanel {
    private UserService userService = new UserService();

    public ChangePasswordPanel() {
        InputPanel nameInputPanel = new InputPanel("用户名", "");
        PasswordPanel passwordPanel = new PasswordPanel("密码", "请输入修改后密码");
        JButton check = new JButton("确认");
        nameInputPanel.setText(userService.getCurUser().getUsername());
        check.addActionListener((e) -> {
            String name = nameInputPanel.getText();
            String password = passwordPanel.getText();
            try {
                userService.changePassword(name, password);
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, "修改密码成功", "修改密码成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, ex.getMessage(), "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        setLayout(new GridLayout(7, 1));
        add(new JLabel("密码修改", JLabel.CENTER));
        add(new JLabel());
        add(nameInputPanel);
        add(new JLabel());
        add(passwordPanel);
        add(new JLabel());
        JPanel button = new JPanel(new FlowLayout());
        button.add(check);
        add(button);
    }
}
