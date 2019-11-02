package qianlei.view;

import com.alee.laf.button.WebButton;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.utils.ViewUtil;
import qianlei.view.panel.linedetail.InputChangeUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 注册界面
 *
 * @author qianlei
 */
class RegisterFrame extends JFrame {
    private final UserService userService = new UserService();
    private final InputChangeUserPanel inputChangeUserPanel = new InputChangeUserPanel();
    private final JPanel registerPanel = new JPanel();
    private final WebButton registerButton = new WebButton("注册");

    RegisterFrame() {
        setIconImage(ViewUtil.getSvgIcon("icon/icon.svg").asBufferedImage());
        setTitle("注册界面");
        addAction();
        addComponent();
        pack();
        setLocationCenter();
    }

    /**
     * 设置事件
     */
    private void addAction() {
        registerButton.addActionListener((e) -> {
            Result result = submit();
            if (result.isSuccess()) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE));
                RegisterFrame.this.dispose();
            } else {
                inputChangeUserPanel.changeVerifyCode();
                JOptionPane.showMessageDialog(RegisterFrame.this, result.getMessage(), "注册失败", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        registerButton.addHotkey(10);
    }

    private Result submit() {
        try {
            User user = inputChangeUserPanel.getUser();
            userService.register(user);
            return new Result(true, "登陆成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        setLayout(new BorderLayout());
        registerPanel.add(registerButton);
        registerPanel.setLayout(new FlowLayout());
        add(new JLabel("用户注册", JLabel.CENTER), BorderLayout.NORTH);
        add(inputChangeUserPanel);
        add(registerPanel, BorderLayout.SOUTH);
    }

    /**
     * 设置窗口居中显示
     */
    private void setLocationCenter() {
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setLocation(screenWidth / 2 - windowWidth / 2 + 50, screenHeight / 2 - windowHeight / 2 + 50);
    }
}
