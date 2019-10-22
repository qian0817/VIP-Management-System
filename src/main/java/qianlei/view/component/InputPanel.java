package qianlei.view.component;

import com.alee.laf.text.WebTextField;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 输入组件
 *
 * @author qianlei
 */
public class InputPanel extends JPanel {
    private WebTextField field = new WebTextField(20);

    public InputPanel(String title, String hintText) {
        this(title, hintText, ViewUtil.getFont().getSize() * 4);
    }

    InputPanel(String title, String hintText, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //保证右对齐
        add(Box.createHorizontalStrut(ViewUtil.getFont().getSize() * 4));
        add(Box.createHorizontalStrut(margin - title.length() * ViewUtil.getFont().getSize()));
        add(Box.createHorizontalBox());

        JLabel label = new JLabel(title);
        add(label);

        //添加提示文字
        field.setInputPrompt(hintText);
        add(field);

        add(Box.createHorizontalStrut(margin));
    }

    public String getText() {
        return field.getText();
    }

    public void setText(String s) {
        field.setText(s);
    }

    public void setEditable(boolean editable) {
        field.setEditable(editable);
    }
}
