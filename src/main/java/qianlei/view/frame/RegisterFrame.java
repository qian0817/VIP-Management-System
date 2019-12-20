package qianlei.view.frame;

import com.alee.laf.button.WebButton;
import com.alee.laf.window.WebFrame;
import com.alee.managers.style.StyleId;
import qianlei.entity.Result;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.service.UserService;
import qianlei.utils.ViewUtil;
import qianlei.view.panel.detail.InputChangeUserPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 注册界面
 *
 * @author qianlei
 */
class RegisterFrame extends WebFrame<RegisterFrame> {
    private final UserService userService = new UserService();
    private final InputChangeUserPanel inputChangeUserPanel = new InputChangeUserPanel();
    private final JPanel registerPanel = new JPanel();
    private final WebButton registerButton = new WebButton("注册");

    RegisterFrame() {
        super(StyleId.frameDecorated);
        setResizable(false);
        setIconImage(ViewUtil.getSvgIcon("icon.svg").asBufferedImage());
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
            Result result;
            try {
                User user = inputChangeUserPanel.getUser();
                result = userService.register(user);
            } catch (WrongDataException ex) {
                result = new Result(false, ex.getMessage());
            }
            JOptionPane.showMessageDialog(RegisterFrame.this, result.getMessage());
            if (result.isSuccess()) {
                RegisterFrame.this.dispose();
            } else {
                inputChangeUserPanel.changeVerifyCode();
            }
        });
        registerButton.addHotkey(10);
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
