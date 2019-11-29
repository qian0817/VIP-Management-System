package qianlei;

import com.alee.laf.WebLookAndFeel;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;

import javax.swing.*;

/**
 * 启动类
 *
 * @author qianlei
 */
public class Application {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        WebLookAndFeel.setForceSingleEventsThread(true);
        SwingUtilities.invokeLater(() -> {
            WebLookAndFeel.install();
            new LoginFrame().setVisible(true);
        });
        DaoUtil.init("main");
        ViewUtil.loadFont("config.json");
    }

}
