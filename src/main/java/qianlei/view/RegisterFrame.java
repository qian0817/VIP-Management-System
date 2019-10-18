package qianlei.view;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import java.awt.*;

class RegisterFrame extends WebFrame {
    RegisterFrame() {
        setLayout(new GridLayout(8, 1));
        add(new WebLabel());
        add(new WebLabel("注册界面", WebLabel.CENTER));
        add(new WebLabel());

        InputPanel usernameInputPanel = new InputPanel("用户名", "请输入用户名");
        add(usernameInputPanel);
        add(new WebLabel());

        PasswordPanel passwordInputPanel = new PasswordPanel("密码", "请输入密码");
        add(passwordInputPanel);
        add(new WebLabel());
        WebButton registerButton = new WebButton("注册");
        registerButton.addActionListener((e) -> {
            UserService userService = new UserService();
            String name = usernameInputPanel.getText();
            String password = passwordInputPanel.getText();
            try {
                userService.register(name, password);
                WebOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "注册成功", WebOptionPane.INFORMATION_MESSAGE);
                RegisterFrame.this.dispose();
            } catch (WrongDataException ex) {
                WebOptionPane.showMessageDialog(RegisterFrame.this, ex.getMessage(), "注册失败", WebOptionPane.INFORMATION_MESSAGE);
            }
        });
        WebPanel registerPanel = new WebPanel();
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
