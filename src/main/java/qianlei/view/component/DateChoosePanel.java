package qianlei.view.component;

import qianlei.utils.DateUtil;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class DateChoosePanel extends JPanel implements ItemListener {
    private JComboBox<Integer> yearChooseComboBox;
    private JComboBox<Integer> monthChooseComboBox;
    private JComboBox<Integer> dayChooseComboBox;

    public DateChoosePanel(String title) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        initChooseComboBoxPanel(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * (4 - title.length())));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel(title);
        add(label);
        JPanel dataChoosePanel = new JPanel();
        dataChoosePanel.setLayout(new GridLayout(1, 3));
        dataChoosePanel.add(yearChooseComboBox);
        dataChoosePanel.add(monthChooseComboBox);
        dataChoosePanel.add(dayChooseComboBox);
        add(dataChoosePanel);

        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
    }

    private void initChooseComboBoxPanel(int year, int month) {
        Vector<Integer> years = new Vector<>(40);
        for (int i = 1990; i <= year; i++) {
            years.add(i);
        }
        yearChooseComboBox = new JComboBox<>(years);
        yearChooseComboBox.setSelectedItem(year);
        Vector<Integer> months = new Vector<>(12);
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }
        monthChooseComboBox = new JComboBox<>(months);
        monthChooseComboBox.setSelectedItem(month);
        Vector<Integer> days = new Vector<>(31);
        for (int i = 1; i <= DateUtil.getDayOfMonth(year, month); i++) {
            days.add(i);
        }
        dayChooseComboBox = new JComboBox<>(days);
        dayChooseComboBox.setSelectedItem(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        yearChooseComboBox.addItemListener(this);
        monthChooseComboBox.addItemListener(this);
    }

    @SuppressWarnings("all")
    public Date getSelectDate() {
        int year = (int) yearChooseComboBox.getSelectedItem();
        int month = (int) monthChooseComboBox.getSelectedItem();
        int day = (int) dayChooseComboBox.getSelectedItem();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            int day = DateUtil.getDayOfMonth((int) yearChooseComboBox.getSelectedItem(), (int) monthChooseComboBox.getSelectedItem());
            if (day > dayChooseComboBox.getItemCount()) {
                for (int i = dayChooseComboBox.getItemCount() + 1; i <= day; i++) {
                    dayChooseComboBox.addItem(i);
                }
            } else {
                for (int i = dayChooseComboBox.getItemCount(); i > day; i--) {
                    dayChooseComboBox.removeItem(i);
                }
            }
        }
    }
}
