package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.panel.linedetail.InputUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class LoginFrame extends JFrame {
    private final InputUserPanel inputUserPanel = new InputUserPanel();
    private final JPanel loginAndRegister = new JPanel();
    private final WebButton loginButton = new WebButton("登录");
    private final JButton registerButton = new JButton("注册");
    private final JLabel titleLabel = new JLabel("登陆界面", JLabel.CENTER);
    private UserService userService = new UserService();
    public LoginFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("登录界面");
        addAction();
        addComponent();
        pack();
        setLocationCenter();
    }

    /**
     * 添加到界面
     */
    private void addComponent() {
        setLayout(new BorderLayout());
        loginAndRegister.add(loginButton);
        loginAndRegister.add(registerButton);
        loginAndRegister.setLayout(new FlowLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(inputUserPanel);
        add(loginAndRegister, BorderLayout.SOUTH);
    }

    /**
     * 添加事件
     */
    private void addAction() {
        loginButton.addHotkey(10);
        loginButton.addActionListener((e) -> {
            Result result = submit();
            if (result.isSuccess()) {
                new MainFrame().setVisible(true);
                LoginFrame.this.dispose();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, result.getMessage(), "登陆失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        registerButton.addActionListener((e) -> new RegisterFrame().setVisible(true));
    }

    private Result submit() {
        try {
            User user = inputUserPanel.getUser();
            userService.login(user);
            return new Result(true, "登陆成功");
        } catch (WrongDataException e) {
            return new Result(false, "用户名或密码错误");
        }
    }

    /**
     * 设置窗口居中显示
     */
    private void setLocationCenter() {
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
