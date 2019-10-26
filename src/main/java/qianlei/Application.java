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
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(WebLookAndFeel::install);
        t.start();
        ViewUtil.loadFont("config.json");
        DaoUtil.init("main.db");
        t.join();
        new LoginFrame().setVisible(true);
    }
}
