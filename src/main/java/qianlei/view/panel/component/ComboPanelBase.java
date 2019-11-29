package qianlei.view.panel.component;

import com.alee.laf.combobox.WebComboBox;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.util.List;

/**
 * 下拉菜单
 *
 * @author qianlei
 */
public class ComboPanelBase extends BaseComponentPanel {
    private final WebComboBox webComboBox;

    @Override
    public void init() {
        webComboBox.setEditable(false);
        webComboBox.setSelectedIndex(0);
    }

    public ComboPanelBase(String title, List<String> items) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //右对齐，根据字体大小调节
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        webComboBox = new WebComboBox(items);
        add(webComboBox);
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
    }

    @Override
    public String getItem() {
        return (String) webComboBox.getSelectedItem();
    }

    @Override
    public void setItem(String s) {
        if (s != null) {
            webComboBox.setSelectedItem(s);
        } else {
            if (webComboBox.getItemCount() != 0) {
                webComboBox.setSelectedIndex(0);
            }
        }
    }

    @Override
    public void setEditable(boolean editable) {
        webComboBox.setEditable(editable);
    }
}
