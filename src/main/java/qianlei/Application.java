package qianlei;

import com.alee.laf.WebLookAndFeel;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;

import javax.swing.*;
import java.util.Properties;

/**
 * 启动类
 *
 * @author qianlei
 */
public class Application {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        //在高版本会有问题
        checkJavaVersion();
        WebLookAndFeel.install();
        DaoUtil.init("main.db");
        ViewUtil.loadFont("config.json");
        new LoginFrame().setVisible(true);
    }

    private static void checkJavaVersion() {
        Properties properties = System.getProperties();
        System.out.println(properties.get("java.version"));
        String javaVersion = properties.getProperty("java.version");
        if (!javaVersion.contains("1.8.")) {
            JOptionPane.showMessageDialog(null, "请使用 JAVA 8 版本运行", "JAVA 版本错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
