package qianlei.view.detail;

import com.alee.laf.button.WebButton;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.view.detail.linedetail.InputChangeUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 登陆密码修改界面
 *
 * @author qianlei
 */
public class ChangePasswordPanel extends JPanel implements CanInitPanel, CanSubmitPanel {
    private UserService userService = new UserService();
    private InputChangeUserPanel inputChangeUserPanel = new InputChangeUserPanel();
    private WebButton check = new WebButton("确认");
    private JLabel titleLabel = new JLabel("密码修改", JLabel.CENTER);
    private JPanel button = new JPanel(new FlowLayout());
    public ChangePasswordPanel() {
        addAction();
        addComponent();
    }

    /**
     * 添加事件
     */
    private void addAction() {
        check.addHotkey(10);
        check.addActionListener((e) -> {
            Result result = submit();
            if (result.isSuccess()) {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, "修改密码成功", "修改密码成功", JOptionPane.INFORMATION_MESSAGE);
                init();
            } else {
                JOptionPane.showMessageDialog(ChangePasswordPanel.this, result.getMessage(), "修改密码失败", JOptionPane.INFORMATION_MESSAGE);
            }
            inputChangeUserPanel.changeVerifyCode();
        });
    }

    /**
     * 提交
     *
     * @return 结果
     */
    @Override
    public Result submit() {
        try {
            User user = inputChangeUserPanel.getUser();
            userService.changePassword(user);
            return new Result(true, "添加成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        inputChangeUserPanel.setEditable(InputChangeUserPanel.USERNAME, false);
        inputChangeUserPanel.init(userService.getCurUser().getUsername());
        button.add(check);
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(inputChangeUserPanel);
        add(button, BorderLayout.SOUTH);
    }

    @Override
    public void init() {
        inputChangeUserPanel.init(userService.getCurUser().getUsername());
    }
}
