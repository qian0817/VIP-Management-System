package qianlei.view.panel;


import com.alee.laf.panel.WebPanel;
import com.alee.managers.style.StyleId;

/**
 * 可被初始化的界面
 *
 * @author qianlei
 */
public abstract class AbstractCanInitPanel extends WebPanel {
    public AbstractCanInitPanel() {
        super(StyleId.panelTransparent);
    }

    /**
     * 初始化界面
     */
    public abstract void initView();
}
