package qianlei.view.component;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 组件panel
 *
 * @author qianlei
 */
public abstract class BaseComponentPanel extends JPanel {
    /**
     * 获取当前输入的值
     *
     * @return 当前输入的值
     */
    public abstract String getItem();

    /**
     * 设置输入的值
     *
     * @param s 需要设置的值
     */
    public abstract void setItem(@Nullable String s);

    /**
     * 设置是否可修改
     *
     * @param editable 是否可修改
     */
    public abstract void setEditable(boolean editable);

    /**
     * 回复界面初始状态
     */
    public abstract void init();
}
