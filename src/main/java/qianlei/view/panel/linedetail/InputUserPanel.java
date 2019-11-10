package qianlei.view.panel.linedetail;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.panel.linedetail.component.BaseComponentPanel;
import qianlei.view.panel.linedetail.component.InputPanelBase;
import qianlei.view.panel.linedetail.component.PasswordPanelBase;

import java.awt.*;

/**
 * 输入用户名和密码的界面，用于登陆界面
 *
 * @author qianlei
 */
public class InputUserPanel extends BaseInputPanel {
    public InputUserPanel() {
        PanelEnum.addToPanelMap(this);
        init();
    }

    private void init() {
        removeAll();
        setLayout(new GridLayout(PanelEnum.values().length * 2 + 1, 1));
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
        String name = get(PanelEnum.USERNAME.getId());
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        String password = get(PanelEnum.PASSWORD.getId());
        if (!StringUtil.isPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        return new User(name, password);
    }

    /**
     * 为每个输入框分配对应的id 将按照id大小从上到下添加到界面
     */
    public enum PanelEnum {
        USERNAME(1, new InputPanelBase("用户名", "请输入用户名")),
        PASSWORD(2, new PasswordPanelBase("密码", "请输入密码"));
        private int id;
        private BaseComponentPanel panel;

        PanelEnum(int id, BaseComponentPanel panel) {
            this.id = id;
            this.panel = panel;
        }

        private static void addToPanelMap(InputUserPanel panel) {
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
