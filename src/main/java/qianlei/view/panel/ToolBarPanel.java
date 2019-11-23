package qianlei.view.panel;

import com.alee.laf.button.WebButton;
import com.alee.managers.style.StyleId;
import qianlei.utils.Log;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;
import qianlei.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * 选择功能界面
 *
 * @author qianlei
 */
public class ToolBarPanel extends JPanel {
    private final MainFrame parent;
    private final MenuButton addGoodButton = new MenuButton("商品信息录入", "icon/add_good.svg", true);
    private final MenuButton showGoodButton = new MenuButton("商品信息查询", "icon/show_good.svg", true);
    private final MenuButton addVipButton = new MenuButton("会员信息录入", "icon/add_vip.svg", true);
    private final MenuButton showVipButton = new MenuButton("会员信息查询", "icon/show_vip.svg", true);
    private final MenuButton addRecordButton = new MenuButton("消费记录登记", "icon/add_record.svg", true);
    private final MenuButton showRecordButton = new MenuButton("消费记录查询", "icon/show_record.svg", true);
    private final MenuButton changePasswordButton = new MenuButton("修改密码", "icon/password.svg", true);
    private final MenuButton helpButton = new MenuButton("系统帮助", "icon/help.svg", false);
    private final MenuButton quitButton = new MenuButton("退出登录", "icon/quit.svg", false);

    public ToolBarPanel(MainFrame parent) {
        this.parent = parent;
        addComponent();
        addAction();
    }

    /**
     * 打开帮助网页
     */
    private static void openHelpHtml() {
        SwingUtilities.invokeLater(() -> {
            File file = new File("help.html");
            if (!file.exists()) {
                try (
                        InputStream html = ViewUtil.class.getClassLoader().getResourceAsStream("help.html");
                        BufferedInputStream inputStream = new BufferedInputStream(Objects.requireNonNull(html));
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false))
                ) {
                    int onceGetBytes = 1024;
                    byte[] bytes = new byte[onceGetBytes];
                    int count;
                    while ((count = inputStream.read(bytes, 0, onceGetBytes)) != -1) {
                        outputStream.write(bytes, 0, count);
                    }
                } catch (IOException ex) {
                    Log.error(Thread.currentThread(), ex);
                }
            }
            try {
                URI uri = new URI("help.html");
                Desktop.getDesktop().browse(uri);
            } catch (IOException | URISyntaxException e) {
                Log.error(Thread.currentThread(), e);
            }
        });
    }

    private void changeDetailPanel(MenuButton button, AbstractCanInitPanel panel) {
        parent.setTitle(button.getName());
        panel.initView();
        parent.changeDetailPanel(panel);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
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
        helpButton.addActionListener(e -> openHelpHtml());
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

    private class MenuButton extends WebButton {
        private String name;

        private MenuButton(String name, String icon, boolean needChangeStyleWhenClick) {
            super(StyleId.buttonIcon, name, ViewUtil.getSvgIcon(icon));
            setToolTip(name);
            this.name = name;
            if (needChangeStyleWhenClick) {
                addActionListener((e) -> {
                    ToolBarPanel.this.initButtonStyle();
                    MenuButton.this.setStyleId(StyleId.comboboxUndecorated);
                });
            }
        }

        @Override
        public String getName() {
            return name;
        }
    }

}
