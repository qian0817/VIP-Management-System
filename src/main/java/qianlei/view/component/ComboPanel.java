package qianlei.view.component;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;

import javax.swing.*;
import java.util.List;

/**
 * @author qianlei
 */
public class ComboPanel<T> extends WebPanel {
    private WebComboBox webComboBox;

    public ComboPanel(String title, List<T> items) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(80));
        add(Box.createHorizontalStrut(80 - title.length() * 20));
        add(Box.createHorizontalBox());
        WebLabel label = new WebLabel(title);
        add(label);
        webComboBox = new WebComboBox(items);
        add(webComboBox);
        add(Box.createHorizontalStrut(80));
    }

    @SuppressWarnings("unchecked")
    public T getSelect() {
        return (T) webComboBox.getSelectedItem();
    }
}
