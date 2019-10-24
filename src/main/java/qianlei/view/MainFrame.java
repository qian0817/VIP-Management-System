package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.managers.style.StyleId;
import com.alee.managers.style.StyleManager;
import com.alee.skin.dark.DarkSkin;
import com.alee.skin.web.WebSkin;
import qianlei.utils.ViewUtil;
import qianlei.view.detail.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * 主界面
 *
 * @author qianlei
 */
public class MainFrame extends JFrame implements ItemListener {
    private DetailPanel detailPanel = new DetailPanel();
    private JPanel setFontPanel = new JPanel();
    private WebComboBox fontFamilyChoosePanel = new WebComboBox(ViewUtil.getSupportedFont(), ViewUtil.getCurFont().getFontName());
    private WebComboBox fontSizeChoosePanel = new WebComboBox(Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48), (Integer) ViewUtil.getCurFont().getSize());
    private WebComboBox styleChoosePanel = new WebComboBox(Arrays.asList("亮色主题", "黑色主题"));

    MainFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg")).asBufferedImage());
        setTitle("Vip管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        fontFamilyChoosePanel.setStyleId(StyleId.comboboxUndecorated);
        fontSizeChoosePanel.setStyleId(StyleId.comboboxUndecorated);
        styleChoosePanel.setStyleId(StyleId.comboboxUndecorated);
        //设置字体标签
        styleChoosePanel.setSelectedItem("亮色主题");
        fontFamilyChoosePanel.addItemListener(this);
        fontSizeChoosePanel.addItemListener(this);
        styleChoosePanel.addItemListener(e ->
                SwingUtilities.invokeLater(() -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (e.getItem().equals("黑色主题")) {
                            StyleManager.setSkin(new DarkSkin());
                        } else {
                            StyleManager.setSkin(new WebSkin());
                        }
                    }
                })
        );
        pack();
        //最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void init() {
        Container container = getContentPane();
        container.removeAll();
        setLayout(new BorderLayout());
        container.add(new MenuPanel(), BorderLayout.WEST);
        detailPanel.change(new AddGoodPanel());
        setFontPanel.setLayout(new GridLayout(1, 10));
        container.add(detailPanel);
        setFontPanel.add(fontFamilyChoosePanel);
        setFontPanel.add(fontSizeChoosePanel);
        setFontPanel.add(styleChoosePanel);
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        setFontPanel.add(new JLabel());
        container.add(setFontPanel, BorderLayout.NORTH);
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
        private int size = ViewUtil.getCurFont().getSize() * 2;
        private WebButton addGoodButton = new WebButton("商品录入", new SvgIcon(getClass().getClassLoader().getResource("icon/add_good.svg"), size, size));
        private WebButton showGoodButton = new WebButton("商品查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_good.svg"), size, size));
        private WebButton addVipButton = new WebButton("VIP录入", new SvgIcon(getClass().getClassLoader().getResource("icon/add_vip.svg"), size, size));
        private WebButton showVipButton = new WebButton("VIP查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_vip.svg"), size, size));
        private WebButton addRecordButton = new WebButton("消费记录登记", new SvgIcon(getClass().getClassLoader().getResource("icon/add_record.svg"), size, size));
        private WebButton showRecordButton = new WebButton("消费记录查询", new SvgIcon(getClass().getClassLoader().getResource("icon/show_record.svg"), size, size));
        private WebButton managerButton = new WebButton("密码修改", new SvgIcon(getClass().getClassLoader().getResource("icon/password.svg"), size, size));
        private WebButton helpButton = new WebButton("系统帮助", new SvgIcon(getClass().getClassLoader().getResource("icon/help.svg"), size, size));
        private WebButton quitButton = new WebButton("退出登录", new SvgIcon(getClass().getClassLoader().getResource("icon/quit.svg"), size, size));

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

        @SuppressWarnings("all")
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
                                BufferedInputStream inputStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("help.html"));
                                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("help.html"))
                        ) {
                            byte[] bytes = new byte[inputStream.available()];
                            inputStream.read(bytes);
                            outputStream.write(bytes);
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
