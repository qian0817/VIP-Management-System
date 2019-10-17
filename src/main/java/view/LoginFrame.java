package view;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;
import entity.User;
import service.UserService;
import view.component.InputPanel;
import view.component.PasswordPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class LoginFrame extends WebFrame {
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 1));
        add(new WebLabel());
        add(new WebLabel("登陆界面", WebLabel.CENTER));
        add(new WebLabel());

        InputPanel usernameInputPanel = new InputPanel("用户名", "请输入用户名");
        add(usernameInputPanel);
        add(new WebLabel());

        PasswordPanel passwordInputPanel = new PasswordPanel("密码", "请输入密码");
        add(passwordInputPanel);
        add(new WebLabel());
        WebPanel loginAndRegister = new WebPanel();
        WebButton loginButton = new WebButton("登录");
        loginButton.addHotkey(10);
        loginButton.addActionListener((e) -> {
            UserService userService = new UserService();
            String name = usernameInputPanel.getText();
            String password = passwordInputPanel.getText();
            User user;
            user = userService.login(name, password);
            if (user == null) {
                JOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误", "登陆失败", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "登陆成功", "登陆成功", JOptionPane.INFORMATION_MESSAGE);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                LoginFrame.this.dispose();
            }

        });
        WebButton registerButton = new WebButton("注册");
        registerButton.addActionListener((e) -> new RegisterFrame().setVisible(true));
        loginAndRegister.add(loginButton);
        loginAndRegister.add(registerButton);
        loginAndRegister.setLayout(new FlowLayout());
        add(loginAndRegister);
        pack();
    }
}
