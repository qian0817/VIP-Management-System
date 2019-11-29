package qianlei.view.panel;

import com.alee.laf.button.WebButton;
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
    private final WebButton check = new WebButton("确认");
    private final JPanel button = new JPanel(new FlowLayout());

    ChangePasswordPanel() {
        //添加组件
        inputChangeUserPanel.setEditable(0, false);
        inputChangeUserPanel.init(userService.getCurUser().getUsername());
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
            userService.changePassword(user);
            result = new Result(true, "添加成功");
        } catch (WrongDataException e) {
            result = new Result(false, e.getMessage());
        }
        if (result.isSuccess()) {
            JOptionPane.showMessageDialog(ChangePasswordPanel.this, "修改密码成功", "修改密码成功", JOptionPane.INFORMATION_MESSAGE);
            initView();
        } else {
            JOptionPane.showMessageDialog(ChangePasswordPanel.this, result.getMessage(), "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
        }
        inputChangeUserPanel.changeVerifyCode();
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            inputChangeUserPanel.init(userService.getCurUser().getUsername());
            inputChangeUserPanel.setItem(1, "");
            inputChangeUserPanel.setItem(2, "");
        });
    }
}
