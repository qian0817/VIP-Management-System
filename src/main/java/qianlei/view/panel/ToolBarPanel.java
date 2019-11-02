package qianlei.view.panel;

import com.alee.laf.button.WebButton;
import com.alee.managers.style.StyleId;
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
    private final MenuButton addGoodButton = new MenuButton("商品信息录入", "icon/add_good.svg");
    private final MenuButton showGoodButton = new MenuButton("商品信息查询", "icon/show_good.svg");
    private final MenuButton addVipButton = new MenuButton("VIP信息录入", "icon/add_vip.svg");
    private final MenuButton showVipButton = new MenuButton("VIP信息查询", "icon/show_vip.svg");
    private final MenuButton addRecordButton = new MenuButton("VIP消费记录登记", "icon/add_record.svg");
    private final MenuButton showRecordButton = new MenuButton("VIP消费记录查询", "icon/show_record.svg");
    private final MenuButton changePasswordButton = new MenuButton("修改密码", "icon/password.svg");
    private final MenuButton helpButton = new MenuButton("系统帮助", "icon/help.svg");
    private final MenuButton quitButton = new MenuButton("退出登录", "icon/quit.svg");

    public ToolBarPanel(MainFrame parent) {
        this.parent = parent;
        addComponent();
        addAction();
    }

    /**
     * 打开帮助网页
     */
    private static void openHelpHtml() {
        try {
            URI uri = new URI("help.html");
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成帮助网页
     */
    private static void createHelpHtml() {
        File file = new File("help.html");
        if (file.exists()) {
            return;
        }
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
            ex.printStackTrace();
        }
    }

    /**
     * 添加动作
     */
    private void addAction() {
        addGoodButton.addActionListener((e) -> changeDetailPanel(addGoodButton, new AddGoodPanel()));
        showGoodButton.addActionListener((e) -> changeDetailPanel(showGoodButton, new ShowGoodPanel(parent)));
        addVipButton.addActionListener((e) -> changeDetailPanel(addVipButton, new AddVipPanel()));
        showVipButton.addActionListener((e) -> changeDetailPanel(showVipButton, new ShowVipPanel(parent)));
        addRecordButton.addActionListener((e) -> changeDetailPanel(addRecordButton, new AddRecordPanel()));
        showRecordButton.addActionListener((e) -> changeDetailPanel(showRecordButton, new ShowRecordPanel()));
        changePasswordButton.addActionListener((e) -> changeDetailPanel(changePasswordButton, new ChangePasswordPanel()));
        helpButton.addActionListener((e) -> {
            createHelpHtml();
            openHelpHtml();
        });
        quitButton.addActionListener((e) -> {
            parent.dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void changeDetailPanel(MenuButton button, AbstractCanInitPanel panel) {
        parent.changeTitle(button.getName());
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

    private static class MenuButton extends WebButton {
        private String name;

        private MenuButton(String name, String icon) {
            super(StyleId.buttonHover, ViewUtil.getSvgIcon(icon));
            setToolTip(name);
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
