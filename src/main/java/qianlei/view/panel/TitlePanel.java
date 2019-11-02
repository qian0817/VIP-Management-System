package qianlei.view.panel;

import com.alee.extended.image.WebImage;
import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;
import qianlei.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

/**
 * 标题栏界面
 *
 * @author qianlei
 */
public class TitlePanel extends JPanel implements ItemListener {
    private final WebComboBox fontFamilyChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, ViewUtil.getSupportedFont(), ViewUtil.getCurConfig().getFont().getFontName());
    private final WebComboBox fontSizeChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48), (Integer) ViewUtil.getFontSize());
    private final WebComboBox styleChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(ViewUtil.LIGHT_SKIN, ViewUtil.DARK_SKIN), ViewUtil.getCurConfig().getSkin());
    private final WebButton minButton = new WebButton(StyleId.buttonHover, ViewUtil.getSvgIcon("icon/min.svg", ViewUtil.getFontSize(), ViewUtil.getFontSize()));
    private final WebButton closeButton = new WebButton(StyleId.buttonHover, ViewUtil.getSvgIcon("icon/close.svg", ViewUtil.getFontSize(), ViewUtil.getFontSize()));
    private final JLabel titleLabel = new JLabel("商品信息录入", JLabel.CENTER);
    private JFrame parent;

    public TitlePanel(JFrame parent) {
        this.parent = parent;
        initView();
        addAction();
    }

    /**
     * 初始化topPanel
     */
    private void initView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(10));
        add(new WebImage(new SvgIcon(getClass().getClassLoader().getResource("icon/icon.svg"))));
        add(Box.createHorizontalStrut(10));
        add(fontFamilyChoosePanel);
        add(fontSizeChoosePanel);
        add(styleChoosePanel);
        add(Box.createHorizontalStrut(50));
        add(titleLabel);
        add(Box.createHorizontalStrut(1000));
        add(minButton);
        add(Box.createHorizontalStrut(20));
        add(closeButton);
    }

    /**
     * 添加事件
     */
    private void addAction() {
        fontFamilyChoosePanel.addItemListener(this);
        fontSizeChoosePanel.addItemListener(this);
        styleChoosePanel.addItemListener(e ->
                SwingUtilities.invokeLater(() -> {
                    //修改主题
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        ViewUtil.changeSkin((String) e.getItem());
                    }
                })
        );
        minButton.addActionListener((e) -> parent.setExtendedState(JFrame.ICONIFIED));
        closeButton.addActionListener((e) -> parent.dispose());
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String fontFamily = (String) fontFamilyChoosePanel.getSelectedItem();
                int fontSize = (int) fontSizeChoosePanel.getSelectedItem();
                //修改字体
                ViewUtil.changeFont(new Font(fontFamily, Font.PLAIN, fontSize));
                new MainFrame().setVisible(true);
                parent.dispose();
            }
        });
    }

    public void setTitle(String s) {
        titleLabel.setText(s);
    }
}
