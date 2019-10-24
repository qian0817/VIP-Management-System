package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;
import qianlei.view.component.VerifyCodePanel;

import javax.swing.*;
import java.awt.*;

class RegisterFrame extends JFrame {
    private VerifyCodePanel verifyCodePanel = new VerifyCodePanel();
    private InputPanel usernameInputPanel = new InputPanel("用户名", "请输入用户名");
    private PasswordPanel passwordInputPanel = new PasswordPanel("密码", "请输入密码");
    private PasswordPanel recheckPasswordInputPanel = new PasswordPanel("确认密码", "请再次填写密码");
    private WebButton registerButton = new WebButton("注册");
    RegisterFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setLayout(new GridLayout(12, 1));
        setTitle("注册界面");
        //注册事件
        registerButton.addActionListener((e) -> {
            UserService userService = new UserService();
            String name = usernameInputPanel.getText();
            String password = passwordInputPanel.getText();
            String remarkPassword = recheckPasswordInputPanel.getText();
            if (verifyCodePanel.isVerifyCodeWrong()) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "验证码错误", "注册失败", JOptionPane.INFORMATION_MESSAGE);
            } else if (!remarkPassword.equals(password)) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "两次填写的密码不一致", "注册失败", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    userService.register(name, password);
                    JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
                    RegisterFrame.this.dispose();
                } catch (WrongDataException ex) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, ex.getMessage(), "注册失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //快捷键
        registerButton.addHotkey(10);
        JPanel registerPanel = new JPanel();
        registerPanel.add(registerButton);
        registerPanel.setLayout(new FlowLayout());
        //添加组件
        add(new JLabel());
        add(new JLabel("注册界面", JLabel.CENTER));
        add(new JLabel());
        add(usernameInputPanel);
        add(new JLabel());
        add(passwordInputPanel);
        add(new JLabel());
        add(recheckPasswordInputPanel);
        add(new JLabel());
        add(verifyCodePanel);
        add(new JLabel());
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
