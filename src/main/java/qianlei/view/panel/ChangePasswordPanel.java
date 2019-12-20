package qianlei.view.panel;

import com.alee.laf.button.WebButton;
import com.alee.managers.notification.NotificationManager;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.panel.detail.InputChangeUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆密码修改界面
 *
 * @author qianlei
 */
public class ChangePasswordPanel extends AbstractCanInitPanel {
    private final UserService userService = new UserService();
    private final InputChangeUserPanel inputChangeUserPanel = new InputChangeUserPanel();

    ChangePasswordPanel() {
        final JPanel button = new JPanel(new FlowLayout());
        final WebButton check = new WebButton("确认");

        //添加组件
        inputChangeUserPanel.setEditable(0, false);
        inputChangeUserPanel.init(UserService.getCurUser().getUsername());
        button.add(check);
        setLayout(new BorderLayout());
        add(inputChangeUserPanel);
        add(button, BorderLayout.SOUTH);
        //添加监听器
        check.addHotkey(10);
        check.addActionListener((e) -> changePassword());
    }

    /**
     * 修改密码
     */
    private void changePassword() {
        Result result;
        try {
            User user = inputChangeUserPanel.getUser();
            result = userService.changePassword(user);
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        NotificationManager.showInnerNotification(result.getMessage());
        if (result.isSuccess()) {
            initView();
        }
        inputChangeUserPanel.changeVerifyCode();
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            inputChangeUserPanel.init(UserService.getCurUser().getUsername());
            inputChangeUserPanel.setItem(1, "");
            inputChangeUserPanel.setItem(2, "");
        });
    }
}
