package qianlei.view.panel;

import com.alee.laf.combobox.WebComboBox;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;
import qianlei.view.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

/**
 * 底栏界面
 *
 * @author qianlei
 */
public class BottomPanel extends AbstractCanInitPanel implements ItemListener {
    private final WebComboBox fontFamilyChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, ViewUtil.getSupportedFont(), ViewUtil.getCurConfig().getFont().getFontName());
    private final WebComboBox fontSizeChoosePanel = new WebComboBox(StyleId.comboboxUndecorated, Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48), (Integer) ViewUtil.getFontSize());
    private final MainFrame parent;

    public BottomPanel(MainFrame parent) {
        initView();
        this.parent = parent;
        fontFamilyChoosePanel.addItemListener(this);
        fontSizeChoosePanel.addItemListener(this);
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            add(new JLabel(ViewUtil.getSvgIcon("text-family.svg", 20, 20)));
            add(fontFamilyChoosePanel);
            add(Box.createHorizontalStrut(50));
            add(new JLabel(ViewUtil.getSvgIcon("text-size.svg", 20, 20)));
            add(fontSizeChoosePanel);
            add(Box.createHorizontalStrut(1000));
        });
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
}
