package qianlei.view.panel.detail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.component.InputPanelBase;
import qianlei.view.component.PasswordPanelBase;

import java.awt.*;

/**
 * 输入用户名和密码的界面，用于登陆界面
 *
 * @author qianlei
 */
public class InputUserPanel extends BaseInputPanel {
    private final InputPanelBase usernameInputPanel = new InputPanelBase("用户名", "请输入用户名", "username.svg");
    private final PasswordPanelBase passwordInputPanel = new PasswordPanelBase("密码", "请输入密码", "password.svg");

    public InputUserPanel() {
        panels.add(usernameInputPanel);
        panels.add(passwordInputPanel);
        init();
    }

    private void init() {
        removeAll();
        setLayout(new GridLayout(panels.size() * 2 + 1, 1));
        addToView();
        repaint();
        setVisible(true);
    }

    /**
     * 获取填写的用户信息
     *
     * @return 填写的用户信息
     * @ 填写的信息错误
     */
    public User getUser() throws WrongDataException {
        String name = usernameInputPanel.getItem();
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        String password = passwordInputPanel.getItem();
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        return new User(name, password);
    }
}
