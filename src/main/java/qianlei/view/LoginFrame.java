package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import qianlei.entity.User;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 1));
        setTitle("登录界面");
        add(new JLabel());
        add(new JLabel("VIP管理系统", JLabel.CENTER));
        add(new JLabel());

        InputPanel usernameInputPanel = new InputPanel("用户名", "请输入用户名");
        add(usernameInputPanel);
        add(new JLabel());

        PasswordPanel passwordInputPanel = new PasswordPanel("密码", "请输入密码");
        add(passwordInputPanel);
        add(new JLabel());
        JPanel loginAndRegister = new JPanel();
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
                userService.setCurUser(user);
                new MainFrame().setVisible(true);
                LoginFrame.this.dispose();
            }
        });
        JButton registerButton = new JButton("注册");
        registerButton.addActionListener((e) -> new RegisterFrame().setVisible(true));
        loginAndRegister.add(loginButton);
        loginAndRegister.add(registerButton);
        loginAndRegister.setLayout(new FlowLayout());
        add(loginAndRegister);
        pack();
        // 设置窗口居中显示
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
