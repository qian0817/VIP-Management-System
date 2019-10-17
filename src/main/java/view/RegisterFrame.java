package view;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;
import exception.WrongDataException;
import service.UserService;
import view.component.InputPanel;
import view.component.PasswordPanel;

import javax.swing.*;
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
                JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
                RegisterFrame.this.dispose();
            } catch (WrongDataException ex) {
                JOptionPane.showMessageDialog(RegisterFrame.this, ex.getMessage(), "注册失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        WebPanel registerPanel = new WebPanel();
        registerPanel.add(registerButton);
        registerPanel.setLayout(new FlowLayout());
        add(registerPanel);
        pack();
    }
}
