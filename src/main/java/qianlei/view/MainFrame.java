package qianlei.view;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.window.WebFrame;
import qianlei.view.detail.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主界面
 *
 * @author qianlei
 */
class MainFrame extends WebFrame {
    private DetailPanel detailPanel = new DetailPanel();

    MainFrame() {
        setExtendedState(WebFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WebFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new MenuPanel(), BorderLayout.WEST);
        detailPanel.init(new AddGoodPanel());
        add(detailPanel);
        pack();
    }

    private static class DetailPanel extends WebPanel {
        private AddGoodPanel addGoodPanel = new AddGoodPanel();

        void init(WebPanel panel) {
            removeAll();
            add(panel);
            repaint();
            setVisible(true);
        }
    }

    private class MenuPanel extends WebPanel implements ActionListener {
        private WebButton addGoodButton = new WebButton("商品信息录入");
        private WebButton showGoodButton = new WebButton("商品信息查询");
        private WebButton addVipButton = new WebButton("VIP信息录入");
        private WebButton showVipButton = new WebButton("VIP信息查询");
        private WebButton addRecordButton = new WebButton("VIP消费购物记录登记");
        private WebButton showRecordButton = new WebButton("VIP消费记录查询");
        private WebButton managerButton = new WebButton("登录密码修改");
        private WebButton helpButton = new WebButton("系统帮助");
        private WebButton quitButton = new WebButton("退出登录");

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
                case "商品信息录入":
                    MainFrame.this.detailPanel.init(new AddGoodPanel());
                    break;
                case "商品信息查询":
                    MainFrame.this.detailPanel.init(new ShowGoodPanel());
                    break;
                case "VIP信息录入":
                    MainFrame.this.detailPanel.init(new AddVipPanel());
                    break;
                case "VIP信息查询":
                    MainFrame.this.detailPanel.init(new ShowVipPanel());
                    break;
                case "VIP消费购物记录登记":
                    MainFrame.this.detailPanel.init(new AddRecordPanel());
                    break;
                case "VIP消费记录查询":
                    MainFrame.this.detailPanel.init(new ShowRecordPanel());
                    break;
                case "登录密码修改":
                    MainFrame.this.detailPanel.init(new ChangePasswordPanel());
                    break;
                case "系统帮助":
                    MainFrame.this.detailPanel.init(new HelpPanel());
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
