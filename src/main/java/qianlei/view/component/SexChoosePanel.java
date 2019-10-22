package qianlei.view.component;

import com.alee.laf.button.WebToggleButton;
import com.alee.laf.grouping.GroupPane;
import qianlei.utils.ViewUtil;

import javax.swing.*;

/**
 * 性别选择界面
 *
 * @author qianlei
 */
public class SexChoosePanel extends JPanel {
    private WebToggleButton manButton = new WebToggleButton("                          男                          ", true);
    private WebToggleButton womanButton = new WebToggleButton("                          女                          ");

    public SexChoosePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 2));
        add(Box.createHorizontalBox());
        manButton.setSelected(true);
        JLabel label = new JLabel("性别");
        add(label);
        GroupPane groupPane = new GroupPane(manButton, womanButton);
        add(groupPane);

        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
    }

    /**
     * 获取选择的性别
     *
     * @return 当前选择的性别
     */
    public String getSelect() {
        return manButton.isSelected() ? "男" : "女";
    }

    /**
     * 修改选择的性别
     *
     * @param s 修改后的性别
     */
    public void setSelect(String s) {
        if ("男".equals(s)) {
            manButton.setSelected(true);
        } else {
            womanButton.setSelected(true);
        }
    }
}
