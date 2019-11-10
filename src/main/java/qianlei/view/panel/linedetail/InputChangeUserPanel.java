package qianlei.view.panel.linedetail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.panel.linedetail.component.BaseComponentPanel;
import qianlei.view.panel.linedetail.component.InputPanelBase;
import qianlei.view.panel.linedetail.component.PasswordPanelBase;
import qianlei.view.panel.linedetail.component.VerifyCodePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 填写注册界面 用于用户注册和修改密码界面
 *
 * @author qianlei
 */
public class InputChangeUserPanel extends BaseInputPanel {
    private final VerifyCodePanel verifyCodePanel = new VerifyCodePanel();

    public InputChangeUserPanel() {
        PanelEnum.addToPanelMap(this);
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
        panelMap.get(PanelEnum.USERNAME.getId()).setItem(username);
        panelMap.get(PanelEnum.PASSWORD.getId()).setItem(null);
        panelMap.get(PanelEnum.CHECK_PASSWORD.getId()).setItem(null);
        verifyCodePanel.setText("");
        removeAll();
        setLayout(new GridLayout(PanelEnum.values().length * 2 + 3, 1));
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
        if (verifyCodePanel.isVerifyCodeWrong()) {
            throw new WrongDataException("验证码错误");
        }
        User user = new User();
        setName(user);
        setPassword(user);
        return user;
    }

    private void setPassword(User user) throws WrongDataException {
        String password = get(PanelEnum.PASSWORD.getId());
        String remarkPassword = get(PanelEnum.CHECK_PASSWORD.getId());
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        if (!password.equals(remarkPassword)) {
            throw new WrongDataException("两次输入密码不一致");
        }
        user.setPassword(password);
    }

    private void setName(User user) throws WrongDataException {
        String name = get(PanelEnum.USERNAME.getId());
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        user.setUsername(name);
    }

    /**
     * 为每个输入框分配对应的id 将按照id大小从上到下添加到界面
     */
    public enum PanelEnum {
        USERNAME(1, new InputPanelBase("用户名", "请输入商品编号")),
        PASSWORD(2, new PasswordPanelBase("密码", "请输入密码")),
        CHECK_PASSWORD(3, new PasswordPanelBase("确认密码", "请重复密码"));
        private int id;
        private BaseComponentPanel panel;

        PanelEnum(int id, BaseComponentPanel panel) {
            this.id = id;
            this.panel = panel;
        }

        /**
         * 将所有的panel添加到panelMap中
         *
         * @param panel 需要添加的panel
         */
        private static void addToPanelMap(InputChangeUserPanel panel) {
            for (PanelEnum panelEnum : values()) {
                panelEnum.panel.setEditable(true);
                panel.panelMap.put(panelEnum.id, panelEnum.panel);
            }
        }

        public int getId() {
            return id;
        }
    }
}
