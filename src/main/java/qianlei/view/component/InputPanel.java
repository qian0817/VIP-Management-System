package qianlei.view.component;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;

import javax.swing.*;

/**
 * 输入组件
 *
 * @author qianlei
 */
public class InputPanel extends WebPanel {
    private WebTextField field;
    private WebLabel label;

    public InputPanel(String title, String hintText) {
        this(title, hintText, 80);
    }

    public InputPanel(String title, String hintText, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(80));
        add(Box.createHorizontalStrut(margin - title.length() * 20));
        add(Box.createHorizontalBox());

        label = new WebLabel(title);
        add(label);

        field = new WebTextField(20);
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
}
