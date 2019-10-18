package qianlei.view;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;
import qianlei.entity.User;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class LoginFrame extends WebFrame {
    public LoginFrame() {
        setDefaultCloseOperation(WebFrame.EXIT_ON_CLOSE);
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
                WebOptionPane.showMessageDialog(LoginFrame.this, "用户名或密码错误", "登陆失败", WebOptionPane.INFORMATION_MESSAGE);
            } else {
                WebOptionPane.showMessageDialog(LoginFrame.this, "登陆成功", "登陆成功", WebOptionPane.INFORMATION_MESSAGE);
                MainFrame mainFrame = new MainFrame();
                userService.setCurUser(user);
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
        // 设置窗口居中显示
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
