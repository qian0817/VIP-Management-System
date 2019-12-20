package qianlei.view.panel.detail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.component.InputPanelBase;
import qianlei.view.component.PasswordPanelBase;
import qianlei.view.component.VerifyCodePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 填写注册界面 用于用户注册和修改密码界面
 *
 * @author qianlei
 */
public class InputChangeUserPanel extends BaseInputPanel {
    private final InputPanelBase usernameInputPanel = new InputPanelBase("用户名", "请输入用户名", "username.svg");
    private final PasswordPanelBase passwordInputPanel = new PasswordPanelBase("密码", "密码只能由字母数字和下划线构成,长度为6-16位", "password.svg");
    private final PasswordPanelBase checkPasswordInputPanel = new PasswordPanelBase("重复密码", "请重复密码", "check_password.svg");
    private final VerifyCodePanel verifyCodePanel = new VerifyCodePanel();

    public InputChangeUserPanel() {
        panels.add(usernameInputPanel);
        panels.add(passwordInputPanel);
        panels.add(checkPasswordInputPanel);
        init();
    }

    private void init() {
        init("");
    }

    /**
     * 修改验证码
     */
    public void changeVerifyCode() {
        verifyCodePanel.changeVerifyCode();
    }

    /**
     * 根据用户名初始化 为用户名输入框添加用户名
     *
     * @param username 用户名
     */
    public void init(String username) {
        usernameInputPanel.setItem(username);
        verifyCodePanel.setText("");
        removeAll();
        setLayout(new GridLayout(panels.size() * 2 + 3, 1));
        addToView();
        add(verifyCodePanel);
        add(new JLabel());
        repaint();
        setVisible(true);
    }

    /**
     * 获取填写的用户
     *
     * @return 填写的用户信息
     * @ 填写的信息错误
     */
    public User getUser() throws WrongDataException {
        if (!verifyCodePanel.isVerifyCodeTrue()) {
            throw new WrongDataException("验证码错误");
        }

        String name = usernameInputPanel.getItem();
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        String password = passwordInputPanel.getItem();
        String remarkPassword = checkPasswordInputPanel.getItem();
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        if (password != null && !password.equals(remarkPassword)) {
            throw new WrongDataException("两次输入密码不一致");
        }
        User user = new User();
        user.setUsername(name);
        return new User(name, password);
    }
}
