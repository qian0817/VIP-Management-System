package qianlei.view.panel.linedetail.component;

import com.alee.laf.text.WebTextField;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 输入组件
 *
 * @author qianlei
 */
public class InputPanelBase extends BaseComponentPanel {
    private final WebTextField field = new WebTextField(20);

    public InputPanelBase(String title, String hintText) {
        this(title, hintText, ViewUtil.getFontSize() * 4);
    }

    public InputPanelBase(String title, String hintText, int margin) {
        //添加提示文字
        field.setInputPrompt(hintText);
        addComponent(title, margin);
    }

    /**
     * 添加组件
     *
     * @param title  名称
     * @param margin 间距
     */
    private void addComponent(String title, int margin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //保证右对齐
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(margin - title.length() * ViewUtil.getFontSize()));
        add(Box.createHorizontalBox());
        add(new JLabel(title));
        add(field);
        add(Box.createHorizontalStrut(margin));
    }

    @Override
    public void setEditable(boolean editable) {
        field.setEditable(editable);
    }

    @Override
    public String getItem() {
        return field.getText();
    }

    @Override
    public void setItem(String s) {
        if (s != null) {
            field.setText(s);
        } else {
            field.setText("");
        }
    }

}
