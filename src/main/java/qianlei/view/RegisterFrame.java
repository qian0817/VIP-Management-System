package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import javax.swing.*;
import java.awt.*;

class RegisterFrame extends JFrame {
    RegisterFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setLayout(new GridLayout(8, 1));
        setTitle("注册界面");
        add(new JLabel());
        add(new JLabel("注册界面", JLabel.CENTER));
        add(new JLabel());

        InputPanel usernameInputPanel = new InputPanel("用户名", "请输入用户名");
        add(usernameInputPanel);
        add(new JLabel());

        PasswordPanel passwordInputPanel = new PasswordPanel("密码", "请输入密码");
        add(passwordInputPanel);
        add(new JLabel());
        WebButton registerButton = new WebButton("注册");
        registerButton.addActionListener((e) -> {
            UserService userService = new UserService();
            String name = usernameInputPanel.getText();
            String password = passwordInputPanel.getText();
            try {
                userService.register(name, password);
                JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
                RegisterFrame.this.dispose();
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(RegisterFrame.this, ex.getMessage(), "注册失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        registerButton.addHotkey(10);
        JPanel registerPanel = new JPanel();
        registerPanel.add(registerButton);
        registerPanel.setLayout(new FlowLayout());
        add(registerPanel);
        pack();
        // 设置窗口居中显示
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2 + 50, screenHeight / 2 - windowHeight / 2 + 50);
    }
}
