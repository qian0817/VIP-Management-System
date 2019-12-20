package qianlei.view.panel.detail;

import org.jetbrains.annotations.Nullable;
import qianlei.view.component.BaseComponentPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qianlei
 */
public abstract class BaseInputPanel extends JPanel {
    final List<BaseComponentPanel> panels = new ArrayList<>();

    /**
     * 获取填写的内容
     *
     * @param index 从上到下第几个组件
     * @return 填写的内容
     */
    @Nullable
    public final String get(int index) {
        if (index >= panels.size() || index < 0) {
            return null;
        }
        BaseComponentPanel panel = panels.get(index);
        if (panel == null) {
            return null;
        }
        return panel.getItem();
    }

    /**
     * 设置该项可否被修改
     *
     * @param index    从上到下第几个组件
     * @param editable 可否被修改
     */
    public final void setEditable(int index, boolean editable) {
        if (index >= panels.size() || index < 0) {
            return;
        }
        BaseComponentPanel panel = panels.get(index);
        if (panel != null) {
            panel.setEditable(editable);
        }
    }

    public final void setItem(int index, String s) {
        if (index >= panels.size() || index < 0) {
            return;
        }
        BaseComponentPanel panel = panels.get(index);
        if (panel != null) {
            panel.setItem(s);
        }
    }

    /**
     * 将panels内的组件添加到界面
     */
    protected final void addToView() {
        for (BaseComponentPanel panel : panels) {
            add(new JLabel());
            add(panel);
        }
        add(new JLabel());
    }

}
