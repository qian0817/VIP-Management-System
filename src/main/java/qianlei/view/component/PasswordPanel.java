package qianlei.view.component;

import com.alee.laf.text.WebPasswordField;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 密码输入组件
 *
 * @author qianlei
 */
public class PasswordPanel extends JPanel {
    private WebPasswordField field;

    public PasswordPanel(String title, String hintText) {
        this(title, hintText, ViewUtil.getCurFont().getSize() * 4);
    }

    private PasswordPanel(String title, String hintText, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //保证右对齐，根据字体大小调节
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(margin - title.length() * ViewUtil.getCurFont().getSize()));
        add(Box.createHorizontalBox());

        JLabel label = new JLabel(title);
        add(label);

        field = new WebPasswordField(20);
        //添加提示文字
        field.setInputPrompt(hintText);
        add(field);

        add(Box.createHorizontalStrut(margin));
    }

    /**
     * 获取输入的密码
     *
     * @return 输入的密码
     */
    public String getText() {
        return String.valueOf(field.getPassword());
    }
}
