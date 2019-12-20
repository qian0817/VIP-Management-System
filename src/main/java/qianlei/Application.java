package qianlei;

import com.alee.laf.WebLookAndFeel;
import com.alee.skin.light.WebLightSkin;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.frame.LoginFrame;

import javax.swing.*;

/**
 * 启动类
 *
 * @author qianlei
 */
public class Application {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        DaoUtil.init("main");
        ViewUtil.loadFont("config.json");
        SwingUtilities.invokeLater(() -> {
            WebLookAndFeel.install(WebLightSkin.class);
            new LoginFrame().setVisible(true);
        });
    }
}
