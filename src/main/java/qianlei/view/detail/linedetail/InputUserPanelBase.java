package qianlei.view.detail.linedetail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.detail.linedetail.component.InputPanelBase;
import qianlei.view.detail.linedetail.component.PasswordPanelBase;

import java.awt.*;

/**
 * 登陆界面
 *
 * @author qianlei
 */
public class InputUserPanelBase extends BaseInputPanel {
    public static final int USERNAME = 1;
    public static final int PASSWORD = 2;

    public InputUserPanelBase() {
        panelMap.put(USERNAME, new InputPanelBase("用户名", "请输入用户名"));
        panelMap.put(PASSWORD, new PasswordPanelBase("密码", "请输入密码"));
        init();
    }

    private void init() {
        removeAll();
        setLayout(new GridLayout(5, 1));
        addToView();
        repaint();
        setVisible(true);
    }

    /**
     * 获取填写的用户信息
     *
     * @return 填写的用户信息
     * @throws WrongDataException 填写的信息错误
     */
    public User getUser() throws WrongDataException {
        String name = get(InputUserPanelBase.USERNAME);
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        String password = get(InputUserPanelBase.PASSWORD);
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        return new User(name, password);
    }
}
