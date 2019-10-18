package qianlei.view.component;

import com.alee.laf.button.WebToggleButton;
import com.alee.laf.grouping.GroupPane;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;

import javax.swing.*;

/**
 * 性别选择界面
 *
 * @author qianlei
 */
public class SexChoosePanel extends WebPanel {
    private WebToggleButton manButton = new WebToggleButton("                          男                          ", true);
    private WebToggleButton womanButton = new WebToggleButton("                          女                          ");

    public SexChoosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(80));
        add(Box.createHorizontalStrut(40));
        add(Box.createHorizontalBox());
        manButton.setSelected(true);
        WebLabel label = new WebLabel("性别");
        add(label);
        GroupPane groupPane = new GroupPane(manButton, womanButton);
        add(groupPane);

        add(Box.createHorizontalStrut(80));
    }

    public String getSelect() {
        return manButton.isSelected() ? "男" : "女";
    }
}
