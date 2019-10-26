package qianlei.view;

import com.alee.extended.image.WebImage;
import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;
import qianlei.view.detail.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

/**
 * 主界面
 *
 * @author qianlei
 */
public class MainFrame extends JFrame implements ItemListener {
    private DetailPanel detailPanel = new DetailPanel();
    private JPanel topPanel = new JPanel();
    private WebComboBox fontFamilyChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, ViewUtil.getSupportedFont(), ViewUtil.getCurConfig().getFont().getFontName());
    private WebComboBox fontSizeChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48), (Integer) ViewUtil.getCurConfig().getFont().getSize());
    private WebComboBox styleChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList("亮色主题", "暗色主题"));
    private WebButton minButton = new WebButton(StyleId.buttonHover, new SvgIcon(getClass().getClassLoader().getResource("icon/min.svg")));
    private WebButton closeButton = new WebButton(StyleId.buttonHover, new SvgIcon(getClass().getClassLoader().getResource("icon/close.svg")));
    private WebLabel titleLabel = new WebLabel("VIP信息管理系统", JLabel.CENTER);

    MainFrame() {
        setUndecorated(true);
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setTitle("VIP管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        init();
        //设置字体标签
        titleLabel.setForeground(Color.gray);
        styleChoosePanel.setSelectedItem(ViewUtil.getCurConfig().getSkin());
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
        //最大化
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public void init() {
        Container container = getContentPane();
        container.removeAll();
        setLayout(new BorderLayout());
        container.add(new MenuPanel(), BorderLayout.WEST);
        detailPanel.change(new AddGoodPanel());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        container.add(detailPanel);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(new WebImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg"))));
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(fontFamilyChoosePanel);
        topPanel.add(fontSizeChoosePanel);
        topPanel.add(styleChoosePanel);
        topPanel.add(Box.createHorizontalStrut(50));
        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(1000));
        topPanel.add(minButton);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(closeButton);
        container.add(topPanel, BorderLayout.NORTH);
        repaint();
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String fontFamily = (String) fontFamilyChoosePanel.getSelectedItem();
                int fontSize = (int) fontSizeChoosePanel.getSelectedItem();
                ViewUtil.changeFont(new Font(fontFamily, Font.PLAIN, fontSize));
                MainFrame.this.dispose();
                new MainFrame().setVisible(true);
            }
        });
    }

    private static class DetailPanel extends JPanel {

        /**
         * 修改显示内容
         *
         * @param panel 需要显示的内容
         */
        void change(JPanel panel) {
            removeAll();
            setLayout(new BorderLayout());
            add(panel);
            repaint();
            validate();
            updateUI();
            setVisible(true);
        }
    }

    private class MenuPanel extends JPanel implements ActionListener {
        private int size = ViewUtil.getCurConfig().getFont().getSize() * 2;
        private WebButton addGoodButton = new WebButton(StyleId.buttonHover, "商品录入", new SvgIcon(getClass().getClassLoader().getResource("icon/add_good.svg"), size, size));
        private WebButton showGoodButton = new WebButton(StyleId.buttonHover, "商品查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_good.svg"), size, size));
        private WebButton addVipButton = new WebButton(StyleId.buttonHover, "VIP录入", new SvgIcon(getClass().getClassLoader().getResource("icon/add_vip.svg"), size, size));
        private WebButton showVipButton = new WebButton(StyleId.buttonHover, "VIP查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_vip.svg"), size, size));
        private WebButton addRecordButton = new WebButton(StyleId.buttonHover, "消费记录登记", new SvgIcon(getClass().getClassLoader().getResource("icon/add_record.svg"), size, size));
        private WebButton showRecordButton = new WebButton(StyleId.buttonHover, "消费记录查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_record.svg"), size, size));
        private WebButton managerButton = new WebButton(StyleId.buttonHover, "密码修改", new SvgIcon(getClass().getClassLoader().getResource("icon/password.svg"), size, size));
        private WebButton helpButton = new WebButton(StyleId.buttonHover, "系统帮助", new SvgIcon(getClass().getClassLoader().getResource("icon/help.svg"), size, size));
        private WebButton quitButton = new WebButton(StyleId.buttonHover, "退出登录", new SvgIcon(getClass().getClassLoader().getResource("icon/quit.svg"), size, size));

        MenuPanel() {
            setLayout(new GridLayout(9, 1));
            initListener();
            add(addGoodButton);
            add(showGoodButton);
            add(addVipButton);
            add(showVipButton);
            add(addRecordButton);
            add(showRecordButton);
            add(managerButton);
            add(helpButton);
            add(quitButton);
        }

        /**
         * 设置监听事件
         */
        private void initListener() {
            addGoodButton.addActionListener(this);
            showGoodButton.addActionListener(this);
            addVipButton.addActionListener(this);
            showVipButton.addActionListener(this);
            addRecordButton.addActionListener(this);
            showRecordButton.addActionListener(this);
            managerButton.addActionListener(this);
            helpButton.addActionListener(this);
            quitButton.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "商品录入":
                    MainFrame.this.detailPanel.change(new AddGoodPanel());
                    break;
                case "商品查询":
                    MainFrame.this.detailPanel.change(new ShowGoodPanel());
                    break;
                case "VIP录入":
                    MainFrame.this.detailPanel.change(new AddVipPanel());
                    break;
                case "VIP查询":
                    MainFrame.this.detailPanel.change(new ShowVipPanel());
                    break;
                case "消费记录登记":
                    MainFrame.this.detailPanel.change(new AddRecordPanel());
                    break;
                case "消费记录查询":
                    MainFrame.this.detailPanel.change(new ShowRecordPanel());
                    break;
                case "密码修改":
                    MainFrame.this.detailPanel.change(new ChangePasswordPanel());
                    break;
                case "系统帮助":
                    SwingUtilities.invokeLater(() -> {
                        try (
                                InputStream html = getClass().getClassLoader().getResourceAsStream("help.html");
                                BufferedInputStream inputStream = new BufferedInputStream(Objects.requireNonNull(html));
                                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("help.html"))
                        ) {
                            byte[] bytes = new byte[1024];
                            while (inputStream.read(bytes) == 1024) {
                                outputStream.write(bytes);
                            }
                            URI uri = new URI("help.html");
                            Desktop.getDesktop().browse(uri);
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    });
                    break;
                case "退出登录":
                    MainFrame.this.dispose();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    break;
                default:
            }
        }
    }
}
