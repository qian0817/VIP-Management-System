package qianlei.view.detail.linedetail;

import qianlei.view.detail.linedetail.component.BaseComponentPanel;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author qianlei
 */
public abstract class BaseInputPanel extends JPanel implements ILine {
    final Map<Integer, BaseComponentPanel> panelMap = new TreeMap<>();

    @Override
    public String get(int field) {
        BaseComponentPanel panel = panelMap.get(field);
        if (panel == null) {
            return null;
        }
        return panel.getItem();
    }

    @Override
    public void setEditable(int field, boolean editable) {
        BaseComponentPanel panel = panelMap.get(field);
        if (panel != null) {
            panel.setEditable(editable);
        }
    }

    @Override
    public void addToView() {
        for (BaseComponentPanel panel : panelMap.values()) {
            add(new JLabel());
            add(panel);
        }
        add(new JLabel());
    }
}
