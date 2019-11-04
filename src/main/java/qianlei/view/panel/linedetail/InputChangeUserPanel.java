package qianlei.view.panel.linedetail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.panel.linedetail.component.InputPanelBase;
import qianlei.view.panel.linedetail.component.PasswordPanelBase;
import qianlei.view.panel.linedetail.component.VerifyCodePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 填写注册界面
 *
 * @author qianlei
 */
public class InputChangeUserPanel extends BaseInputPanel {
    public static final int USERNAME = 1;
    @SuppressWarnings("WeakerAccess")
    public static final int PASSWORD = 2;
    @SuppressWarnings("WeakerAccess")
    public static final int CHECK_PASSWORD = 3;
    private final VerifyCodePanel verifyCodePanel = new VerifyCodePanel();

    public InputChangeUserPanel() {
        panelMap.put(USERNAME, new InputPanelBase("用户名", "请输入商品编号"));
        panelMap.put(PASSWORD, new PasswordPanelBase("密码", "请输入密码"));
        panelMap.put(CHECK_PASSWORD, new PasswordPanelBase("确认密码", "请重复密码"));
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
        panelMap.get(USERNAME).setItem(username);
        panelMap.get(PASSWORD).setItem(null);
        panelMap.get(CHECK_PASSWORD).setItem(null);
        verifyCodePanel.setText("");
        removeAll();
        setLayout(new GridLayout(9, 1));
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
    public User getUser() {
        if (verifyCodePanel.isVerifyCodeWrong()) {
            throw new WrongDataException("验证码错误");
        }
        User user = new User();
        setName(user);
        setPassword(user);
        return user;
    }

    private void setPassword(User user) {
        String password = get(InputChangeUserPanel.PASSWORD);
        String remarkPassword = get(InputChangeUserPanel.CHECK_PASSWORD);
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        if (!password.equals(remarkPassword)) {
            throw new WrongDataException("两次输入密码不一致");
        }
        user.setPassword(password);
    }

    private void setName(User user) {
        String name = get(InputChangeUserPanel.USERNAME);
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        user.setUsername(name);
    }
}