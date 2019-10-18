package qianlei.view.detail;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.PasswordPanel;

import java.awt.*;

/**
 * 登陆密码修改界面
 *
 * @author qianlei
 */
public class ChangePasswordPanel extends WebPanel {
    private UserService userService = new UserService();

    public ChangePasswordPanel() {
        InputPanel nameInputPanel = new InputPanel("用户名", "");
        PasswordPanel passwordPanel = new PasswordPanel("密码", "请输入修改后密码");
        WebButton check = new WebButton("确认");
        nameInputPanel.setText(userService.getCurUser().getUsername());
        check.addActionListener((e) -> {
            String name = nameInputPanel.getText();
            String password = passwordPanel.getText();
            try {
                userService.changePassword(name, password);
                WebOptionPane.showMessageDialog(ChangePasswordPanel.this, "修改密码成功", "修改密码成功", WebOptionPane.INFORMATION_MESSAGE);
            } catch (WrongDataException ex) {
                WebOptionPane.showMessageDialog(ChangePasswordPanel.this, ex.getMessage(), "修改密码失败", WebOptionPane.INFORMATION_MESSAGE);
            }
        });
        setLayout(new GridLayout(7, 1));
        add(new WebLabel("密码修改", WebLabel.CENTER));
        add(new WebLabel());
        add(nameInputPanel);
        add(new WebLabel());
        add(passwordPanel);
        add(new WebLabel());
        WebPanel button = new WebPanel(new FlowLayout());
        button.add(check);
        add(button);
    }
}
