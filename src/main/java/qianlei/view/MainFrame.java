package qianlei.view;

import com.alee.extended.image.WebImage;
import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;
import qianlei.view.detail.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

/**
 * 主界面
 *
 * @author qianlei
 */
public class MainFrame extends JFrame implements ItemListener {
    private final static JLabel TITLE_LABEL = new JLabel(MenuEnum.ADD_GOOD_MENU.getName(), JLabel.CENTER);
    private final JPanel detailPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private final WebComboBox fontFamilyChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, ViewUtil.getSupportedFont(), ViewUtil.getCurConfig().getFont().getFontName());
    private final WebComboBox fontSizeChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48), (Integer) ViewUtil.getCurConfig().getFont().getSize());
    private final WebComboBox styleChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(ViewUtil.LIGHT_SKIN, ViewUtil.DARK_SKIN), ViewUtil.getCurConfig().getSkin());
    private final WebButton minButton = new WebButton(StyleId.buttonHover, ViewUtil.getSvgIcon("icon/min.svg", 25, 25));
    private final WebButton closeButton = new WebButton(StyleId.buttonHover, ViewUtil.getSvgIcon("icon/close.svg", 25, 25));
    private final JPanel menuPanel = new JPanel();

    MainFrame() {
        setUndecorated(true);
        setIconImage(ViewUtil.getSvgIcon("icon/icon.svg", 25, 25).asBufferedImage());
        setTitle("VIP管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        init();
        addAction();
        //最大化
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    /**
     * 添加事件
     */
    private void addAction() {
        fontFamilyChoosePanel.addItemListener(this);
        fontSizeChoosePanel.addItemListener(this);
        styleChoosePanel.addItemListener(e ->
                SwingUtilities.invokeLater(() -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        ViewUtil.changeSkin((String) e.getItem());
                    }
                })
        );
        minButton.addActionListener((e) -> MainFrame.this.setExtendedState(JFrame.ICONIFIED));
        closeButton.addActionListener((e) -> MainFrame.this.dispose());
    }

    public void init() {
        initTopPanel();
        initMenuPanel();
        Container container = getContentPane();
        container.removeAll();
        setLayout(new BorderLayout());
        changeDetailPanel(new AddGoodPanel());
        container.add(menuPanel, BorderLayout.WEST);
        container.add(detailPanel);
        container.add(topPanel, BorderLayout.NORTH);
        repaint();
        setVisible(true);
    }

    /**
     * 初始化菜单界面
     */
    private void initMenuPanel() {
        setLayout(new GridLayout(MenuEnum.values().length, 1));
        for (MenuEnum menuEnum : MenuEnum.values()) {
            add(menuEnum.getButton());
        }
        MenuEnum.ADD_GOOD_MENU.addActionListener((e) -> {
            changeDetailPanel(new AddGoodPanel());
            TITLE_LABEL.setText(MenuEnum.ADD_GOOD_MENU.getName());
        });
        MenuEnum.SHOW_GOOD_MENU.addActionListener((e) -> {
            changeDetailPanel(new ShowGoodPanel());
            TITLE_LABEL.setText(MenuEnum.SHOW_GOOD_MENU.getName());
        });
        MenuEnum.ADD_VIP_MENU.addActionListener((e) -> {
            changeDetailPanel(new AddVipPanel());
            TITLE_LABEL.setText(MenuEnum.ADD_VIP_MENU.getName());
        });
        MenuEnum.SHOW_VIP_MENU.addActionListener((e) -> {
            changeDetailPanel(new ShowVipPanel());
            TITLE_LABEL.setText(MenuEnum.SHOW_VIP_MENU.getName());
        });
        MenuEnum.ADD_RECORD_MENU.addActionListener((e) -> {
            changeDetailPanel(new AddRecordPanel());
            TITLE_LABEL.setText(MenuEnum.ADD_RECORD_MENU.getName());
        });
        MenuEnum.SHOW_RECORD_MENU.addActionListener((e) -> {
            changeDetailPanel(new ShowRecordPanel());
            TITLE_LABEL.setText(MenuEnum.SHOW_RECORD_MENU.getName());
        });
        MenuEnum.CHANGE_PASSWORD_MENU.addActionListener((e) -> {
            changeDetailPanel(new ChangePasswordPanel());
            TITLE_LABEL.setText(MenuEnum.CHANGE_PASSWORD_MENU.getName());
        });
        MenuEnum.HELP_MENU.addActionListener((e) -> SwingUtilities.invokeLater(() -> {
            ViewUtil.createHelpHtml();
            ViewUtil.openHelpHtml();
        }));
        MenuEnum.QUIT_MENU.addActionListener((e) -> {
            MainFrame.this.dispose();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }

    /**
     * 修改主内容界面
     *
     * @param panel 修改后的界面
     */
    private void changeDetailPanel(JPanel panel) {
        detailPanel.removeAll();
        detailPanel.setLayout(new BorderLayout());
        detailPanel.add(panel);
        detailPanel.repaint();
        detailPanel.validate();
        panel.setVisible(true);
        detailPanel.setVisible(true);
    }

    /**
     * 初始化topPanel
     */
    private void initTopPanel() {
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(new WebImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg"))));
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(fontFamilyChoosePanel);
        topPanel.add(fontSizeChoosePanel);
        topPanel.add(styleChoosePanel);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(TITLE_LABEL);
        topPanel.add(Box.createHorizontalStrut(1000));
        topPanel.add(minButton);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(closeButton);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String fontFamily = (String) fontFamilyChoosePanel.getSelectedItem();
                int fontSize = (int) fontSizeChoosePanel.getSelectedItem();
                ViewUtil.changeFont(new Font(fontFamily, Font.PLAIN, fontSize));
                new MainFrame().setVisible(true);
                MainFrame.this.dispose();
            }
        });
    }

    private enum MenuEnum {
        //商品信息录入按钮
        ADD_GOOD_MENU("商品信息录入", "icon/add_good.svg"),
        //商品信息查询
        SHOW_GOOD_MENU("商品信息查询", "icon/show_good.svg"),
        //VIP信息录入
        ADD_VIP_MENU("VIP信息录入", "icon/show_good.svg"),
        //VIP信息查询
        SHOW_VIP_MENU("VIP信息查询", "icon/show_vip.svg"),
        //VIP消费购物记录登记
        ADD_RECORD_MENU("VIP消费购物记录登记", "icon/add_record.svg"),
        //VIP消费记录查询
        SHOW_RECORD_MENU("VIP消费记录查询", "icon/show_record.svg"),
        //修改密码
        CHANGE_PASSWORD_MENU("修改密码", "icon/password.svg"),
        //系统帮助
        HELP_MENU("系统帮助", "icon/help.svg"),
        //退出登录
        QUIT_MENU("退出登录", "icon/quit.svg");

        private String name;
        private WebButton button;

        MenuEnum(String name, String icon) {
            this.name = name;
            button = new WebButton(StyleId.buttonHover, ViewUtil.getSvgIcon(icon));
            button.setToolTip(name);
        }

        public String getName() {
            return name;
        }

        public void addActionListener(ActionListener e) {
            button.addActionListener(e);
        }

        public WebButton getButton() {
            return button;
        }
    }
}
