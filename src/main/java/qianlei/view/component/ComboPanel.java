package qianlei.view.component;

import com.alee.laf.combobox.WebComboBox;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.util.List;

/**
 * @author qianlei
 */
public class ComboPanel<T> extends JPanel {
    private JComboBox webComboBox;

    public ComboPanel(String title, List<T> items) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //右对齐，根据字体大小调节
        add(Box.createHorizontalStrut(ViewUtil.getFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getFont().getSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        webComboBox = new WebComboBox(items);
        add(webComboBox);
        add(Box.createHorizontalStrut(ViewUtil.getFont().getSize() * 4));
    }

    public void setSelectItem(T t) {
        webComboBox.setSelectedItem(t);
    }

    @SuppressWarnings("unchecked")
    public T getSelect() {
        return (T) webComboBox.getSelectedItem();
    }
}
