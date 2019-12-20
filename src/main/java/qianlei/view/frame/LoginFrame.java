package qianlei.view.frame;

import com.alee.laf.button.WebButton;
import com.alee.laf.window.WebFrame;
import com.alee.managers.style.StyleId;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.utils.ViewUtil;
import qianlei.view.panel.detail.InputUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class LoginFrame extends WebFrame<LoginFrame> {
    private final InputUserPanel inputUserPanel = new InputUserPanel();
    private final JPanel loginAndRegister = new JPanel();
    private final WebButton loginButton = new WebButton("登录");
    private final JButton registerButton = new JButton("注册");
    private final JLabel titleLabel = new JLabel("登陆界面", JLabel.CENTER);
    private UserService userService = new UserService();

    public LoginFrame() {
        super(StyleId.frameDecorated);
        setResizable(false);
        setIconImage(ViewUtil.getSvgIcon("icon.svg").asBufferedImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginButton.addHotkey(10);
        loginButton.addActionListener((e) -> login());
        registerButton.addActionListener((e) -> new RegisterFrame().setVisible(true));
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

    private void login() {
        Result result;
        try {
            User user = inputUserPanel.getUser();
            result = userService.login(user);
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        if (result.isSuccess()) {
            new MainFrame().setVisible(true);
            LoginFrame.this.dispose();
        } else {
            JOptionPane.showMessageDialog(LoginFrame.this, result.getMessage());
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
