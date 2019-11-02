package qianlei.view.panel.linedetail.component;

import com.alee.laf.text.WebPasswordField;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 密码输入组件
 *
 * @author qianlei
 */
public class PasswordPanelBase extends BaseComponentPanel {
    private final WebPasswordField field;

    public PasswordPanelBase(String title, String hintText) {
        this(title, hintText, ViewUtil.getFontSize() * 4);
    }

    private PasswordPanelBase(String title, String hintText, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //保证右对齐，根据字体大小调节
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(margin - title.length() * ViewUtil.getFontSize()));
        add(Box.createHorizontalBox());

        JLabel label = new JLabel(title);
        add(label);

        field = new WebPasswordField(20);
        //添加提示文字
        field.setInputPrompt(hintText);
        add(field);

        add(Box.createHorizontalStrut(margin));
    }

    @Override
    public String getItem() {
        return String.valueOf(field.getPassword());
    }

    @Override
    public void setItem(String s) {
        if (s != null) {
            field.setText(s);
        }
    }

    @Override
    public void setEditable(boolean editable) {

    }
}
