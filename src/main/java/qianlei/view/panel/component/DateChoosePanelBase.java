package qianlei.view.panel.component;

import com.alee.extended.date.WebDateField;
import qianlei.utils.DateUtil;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期选择组件
 *
 * @author qianlei
 */
public class DateChoosePanelBase extends BaseComponentPanel {
    private final WebDateField webDateField = new WebDateField(new Date());

    public DateChoosePanelBase(String title) {
        webDateField.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日"));
        webDateField.setAllowUserInput(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        add(webDateField);
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
    }

    @Override
    public String getItem() {
        return DateUtil.transferToString(webDateField.getDate());
    }

    @Override
    public void setItem(String s) {
        if (s != null) {
            try {
                webDateField.setDate(DateUtil.transferToDate(s));
            } catch (ParseException e) {
                webDateField.setDate(new Date());
            }
        } else {
            webDateField.setDate(new Date());
        }
    }

    @Override
    public void setEditable(boolean editable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void init() {
        webDateField.setDate(new Date());
    }
}
