package qianlei;

import com.alee.laf.WebLookAndFeel;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;

/**
 * 启动类
 *
 * @author qianlei
 */
public class Application {
    public static void main(String[] args) {
        Thread t = new Thread(() -> DaoUtil.init("main.db"));
        t.start();
        WebLookAndFeel.install();
        ViewUtil.loadFont("config.json");
        new LoginFrame().setVisible(true);
    }
}
