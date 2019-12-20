package qianlei.view.frame;

import com.alee.laf.window.WebFrame;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;
import qianlei.view.panel.BottomPanel;
import qianlei.view.panel.ToolBarPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面
 *
 * @author qianlei
 */
public class MainFrame extends WebFrame<MainFrame> {
    private final JPanel detailPanel = new JPanel();
    private final ToolBarPanel menuPanel = new ToolBarPanel(this);
    private final BottomPanel bottomPanel = new BottomPanel(this);

    public MainFrame() {
        super(StyleId.frameDecorated);
        setIconImage(ViewUtil.getSvgIcon("icon.svg", 25, 25).asBufferedImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initView();
        //最大化
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        Container container = getContentPane();
        container.removeAll();
        setLayout(new BorderLayout());
        container.add(menuPanel, BorderLayout.WEST);
        container.add(detailPanel);
        container.add(bottomPanel, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }

    /**
     * 修改主内容界面
     *
     * @param panel 修改后的界面
     */
    public void changeDetailPanel(JPanel panel) {
        detailPanel.removeAll();
        detailPanel.setLayout(new BorderLayout());
        detailPanel.add(panel);
        detailPanel.repaint();
        detailPanel.validate();
        panel.setVisible(true);
        detailPanel.setVisible(true);
    }
}