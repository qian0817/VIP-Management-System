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
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        webComboBox = new WebComboBox(items);
        add(webComboBox);
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
    }

    /**
     * 设置选择项
     *
     * @param t 需要选择的内容
     */
    public void setSelectItem(T t) {
        webComboBox.setSelectedItem(t);
    }

    /**
     * 获取当前选择内容
     *
     * @return 当前选择的内容
     */
    @SuppressWarnings("unchecked")
    public T getSelect() {
        return (T) webComboBox.getSelectedItem();
    }
}
