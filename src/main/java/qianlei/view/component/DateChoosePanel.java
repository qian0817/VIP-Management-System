package qianlei.view.component;

import com.alee.extended.date.WebDateField;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期选择组件
 *
 * @author qianlei
 */
public class DateChoosePanel extends JPanel {
    private WebDateField webDateField = new WebDateField();
    public DateChoosePanel(String title) {
        webDateField.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日"));
        webDateField.setAllowUserInput(false);
        webDateField.setDate(new Date());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        add(webDateField);
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
    }


    /**
     * 获取当前选中的日期
     *
     * @return 当前选中的日期
     */
    public Date getSelectDate() {
        return webDateField.getDate();
    }
}
