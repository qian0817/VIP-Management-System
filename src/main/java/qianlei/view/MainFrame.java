package qianlei.view;

import com.alee.extended.svg.SvgIcon;
import com.alee.laf.button.WebButton;
import qianlei.utils.ViewUtil;
import qianlei.view.detail.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主界面
 *
 * @author qianlei
 */
class MainFrame extends JFrame {
    private DetailPanel detailPanel = new DetailPanel();

    MainFrame() {
        setIconImage(new SvgIcon(getClass().getClassLoader().getResource("icon.svg")).asBufferedImage());
        setTitle("Vip管理系统");
        //最大化
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new MenuPanel(), BorderLayout.WEST);
        detailPanel.change(new AddGoodPanel());
        add(detailPanel);
        pack();
    }

    private static class DetailPanel extends JPanel {

        /**
         * 修改显示内容
         *
         * @param panel 需要显示的内容
         */
        void change(JPanel panel) {
            removeAll();
            setLayout(new BorderLayout());
            add(panel);
            repaint();
            setVisible(true);
        }
    }

    private class MenuPanel extends JPanel implements ActionListener {
        private int size = ViewUtil.getCurFont().getSize() * 2;
        private WebButton addGoodButton = new WebButton("商品录入", new SvgIcon(getClass().getClassLoader().getResource("add_good.svg"), size, size));
        private WebButton showGoodButton = new WebButton("商品查询", new SvgIcon(getClass().getClassLoader().getResource("show_good.svg"), size, size));
        private WebButton addVipButton = new WebButton("VIP录入", new SvgIcon(getClass().getClassLoader().getResource("add_vip.svg"), size, size));
        private WebButton showVipButton = new WebButton("VIP查询", new SvgIcon(getClass().getClassLoader().getResource("show_vip.svg"), size, size));
        private WebButton addRecordButton = new WebButton("消费记录登记", new SvgIcon(getClass().getClassLoader().getResource("add_record.svg"), size, size));
        private WebButton showRecordButton = new WebButton("消费记录查询", new SvgIcon(getClass().getClassLoader().getResource("show_record.svg"), size, size));
        private WebButton managerButton = new WebButton("密码修改", new SvgIcon(getClass().getClassLoader().getResource("password.svg"), size, size));
        private WebButton helpButton = new WebButton("系统帮助", new SvgIcon(getClass().getClassLoader().getResource("help.svg"), size, size));
        private WebButton quitButton = new WebButton("退出登录", new SvgIcon(getClass().getClassLoader().getResource("quit.svg"), size, size));

        MenuPanel() {
            setLayout(new GridLayout(9, 1));
            initListener();
            add(addGoodButton);
            add(showGoodButton);
            add(addVipButton);
            add(showVipButton);
            add(addRecordButton);
            add(showRecordButton);
            add(managerButton);
            add(helpButton);
            add(quitButton);
        }

        /**
         * 设置监听事件
         */
        private void initListener() {
            addGoodButton.addActionListener(this);
            showGoodButton.addActionListener(this);
            addVipButton.addActionListener(this);
            showVipButton.addActionListener(this);
            addRecordButton.addActionListener(this);
            showRecordButton.addActionListener(this);
            managerButton.addActionListener(this);
            helpButton.addActionListener(this);
            quitButton.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "商品录入":
                    MainFrame.this.detailPanel.change(new AddGoodPanel());
                    break;
                case "商品查询":
                    MainFrame.this.detailPanel.change(new ShowGoodPanel());
                    break;
                case "VIP录入":
                    MainFrame.this.detailPanel.change(new AddVipPanel());
                    break;
                case "VIP查询":
                    MainFrame.this.detailPanel.change(new ShowVipPanel());
                    break;
                case "消费记录登记":
                    MainFrame.this.detailPanel.change(new AddRecordPanel());
                    break;
                case "消费记录查询":
                    MainFrame.this.detailPanel.change(new ShowRecordPanel());
                    break;
                case "密码修改":
                    MainFrame.this.detailPanel.change(new ChangePasswordPanel());
                    break;
                case "系统帮助":
                    MainFrame.this.detailPanel.change(new HelpPanel());
                    break;
                case "退出登录":
                    MainFrame.this.dispose();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    break;
                default:
            }
        }
    }
}
