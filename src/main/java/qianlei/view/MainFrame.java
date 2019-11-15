package qianlei.view;

import qianlei.utils.ViewUtil;
import qianlei.view.panel.AddGoodPanel;
import qianlei.view.panel.ToolBarPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 主界面
 *
 * @author qianlei
 */
public class MainFrame extends JFrame {
    private final JPanel detailPanel = new JPanel();
    private final ToolBarPanel menuPanel = new ToolBarPanel(this);

    public MainFrame() {
        setIconImage(ViewUtil.getSvgIcon("icon/icon.svg", 25, 25).asBufferedImage());
        setTitle("VIP管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initView();
        //最大化
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }

    /**
     * 初始化界面
     */
    private void initView() {
        Container container = getContentPane();
        container.removeAll();
        setLayout(new BorderLayout());
        changeDetailPanel(new AddGoodPanel());
        container.add(menuPanel, BorderLayout.WEST);
        container.add(detailPanel);
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