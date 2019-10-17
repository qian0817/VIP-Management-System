package view.component;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebPasswordField;

import javax.swing.*;

/**
 * 密码输入组件
 *
 * @author qianlei
 */
public class PasswordPanel extends WebPanel {
    private WebPasswordField field;
    private WebLabel label;

    public PasswordPanel(String title, String hintText) {
        this(title, hintText, 80);
    }

    public PasswordPanel(String title, String hintText, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(80));
        add(Box.createHorizontalStrut(margin - title.length() * 20));
        add(Box.createHorizontalBox());

        label = new WebLabel(title);
        add(label);

        field = new WebPasswordField(20);
        field.setInputPrompt(hintText);
        add(field);

        add(Box.createHorizontalStrut(margin));
    }

    public String getText() {
        return String.valueOf(field.getPassword());
    }
}
