package qianlei.view.panel.component;

import com.alee.laf.button.WebToggleButton;
import com.alee.laf.grouping.GroupPane;
import qianlei.entity.Vip;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 性别选择界面
 *
 * @author qianlei
 */
public class SexChoosePanelBase extends BaseComponentPanel {
    private final WebToggleButton manButton = new WebToggleButton("  男  ", true);
    private final WebToggleButton womanButton = new WebToggleButton("  女  ");

    @Override
    public void init() {
        manButton.setSelected(true);
    }

    public SexChoosePanelBase(String title) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        manButton.setSelected(true);
        JLabel label = new JLabel(title);
        add(label);
        GroupPane groupPane = new GroupPane(manButton, womanButton);
        add(groupPane);
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
    }

    @Override
    public String getItem() {
        return manButton.isSelected() ? "男" : "女";
    }

    @Override
    public void setItem(String s) {
        if (Vip.WOMAN.equals(s)) {
            womanButton.setSelected(true);
        } else {
            manButton.setSelected(true);
        }
    }

    @Override
    public void setEditable(boolean editable) {

    }
}
