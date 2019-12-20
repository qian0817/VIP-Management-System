package qianlei.view.panel;

import com.alee.laf.button.WebButton;
import com.alee.managers.style.StyleId;
import org.jetbrains.annotations.NotNull;
import qianlei.utils.ViewUtil;
import qianlei.view.frame.LoginFrame;
import qianlei.view.frame.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 选择功能界面
 *
 * @author qianlei
 */
public class ToolBarPanel extends JPanel {
    private final MainFrame parent;
    private final MenuButton addGoodButton = new MenuButton("商品信息录入", "add_good.svg");
    private final MenuButton showGoodButton = new MenuButton("商品信息查询", "show_good.svg");
    private final MenuButton addVipButton = new MenuButton("会员信息录入", "add_vip.svg");
    private final MenuButton showVipButton = new MenuButton("会员信息查询", "show_vip.svg");
    private final MenuButton addRecordButton = new MenuButton("消费记录登记", "add_record.svg");
    private final MenuButton showRecordButton = new MenuButton("消费记录查询", "show_record.svg");
    private final MenuButton changePasswordButton = new MenuButton("修改密码", "change_password.svg");
    private final MenuButton helpButton = new MenuButton("系统帮助", "help.svg");
    private final MenuButton quitButton = new MenuButton("退出登录", "quit.svg");

    public ToolBarPanel(MainFrame parent) {
        this.parent = parent;
        addComponent();
        addAction();
        changeDetailPanel(addGoodButton, new AddGoodPanel());
    }

    private void changeDetailPanel(@NotNull MenuButton button, @NotNull AbstractCanInitPanel panel) {
        parent.setTitle(button.getName());
        panel.initView();
        parent.changeDetailPanel(panel);
        initButtonStyle();
        button.setStyleId(StyleId.comboboxUndecorated);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new GridLayout(9, 1));
            add(addGoodButton);
            add(showGoodButton);
            add(addVipButton);
            add(showVipButton);
            add(addRecordButton);
            add(showRecordButton);
            add(changePasswordButton);
            add(helpButton);
            add(quitButton);
        });
    }

    /**
     * 添加动作
     */
    private void addAction() {
        addGoodButton.addActionListener(e -> changeDetailPanel(addGoodButton, new AddGoodPanel()));
        showGoodButton.addActionListener(e -> changeDetailPanel(showGoodButton, new ShowGoodPanel(parent)));
        addVipButton.addActionListener(e -> changeDetailPanel(addVipButton, new AddVipPanel()));
        showVipButton.addActionListener(e -> changeDetailPanel(showVipButton, new ShowVipPanel(parent)));
        addRecordButton.addActionListener(e -> changeDetailPanel(addRecordButton, new AddRecordPanel()));
        showRecordButton.addActionListener(e -> changeDetailPanel(showRecordButton, new ShowRecordPanel()));
        changePasswordButton.addActionListener(e -> changeDetailPanel(changePasswordButton, new ChangePasswordPanel()));
        helpButton.addActionListener(e -> changeDetailPanel(helpButton, new HelpPanel()));
        quitButton.addActionListener(e -> {
            parent.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void initButtonStyle() {
        addGoodButton.setStyleId(StyleId.button);
        showGoodButton.setStyleId(StyleId.button);
        addVipButton.setStyleId(StyleId.button);
        showVipButton.setStyleId(StyleId.button);
        addRecordButton.setStyleId(StyleId.button);
        showRecordButton.setStyleId(StyleId.button);
        changePasswordButton.setStyleId(StyleId.button);
        helpButton.setStyleId(StyleId.button);
        quitButton.setStyleId(StyleId.button);
    }

    private static class MenuButton extends WebButton {
        private String name;

        private MenuButton(String name, String icon) {
            super(StyleId.buttonIcon, name, ViewUtil.getSvgIcon(icon));
            setToolTip(name);
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

}
