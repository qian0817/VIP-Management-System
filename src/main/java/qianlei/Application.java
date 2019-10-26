package qianlei;

import com.alee.laf.WebLookAndFeel;
import qianlei.service.GoodService;
import qianlei.service.RecordService;
import qianlei.service.UserService;
import qianlei.service.VipService;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;

import java.util.Date;

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
        // createTestCase();
        t.join();
        new LoginFrame().setVisible(true);
    }

    private static void createTestCase() {
        GoodService goodService = new GoodService();
        UserService userService = new UserService();
        VipService vipService = new VipService();
        RecordService recordService = new RecordService();
        for (int i = 1; i <= 100; i++) {
            goodService.addGood(String.valueOf(i), "测试商品" + i, "测试制造商" + i, new Date((long) (Math.random() * new Date().getTime()))
                    , String.valueOf(Math.random() * 100000), (int) (Math.random() * 100) * 1.0 / 100, String.valueOf((int) (Math.random() * 1000)), "测试简介", "测试备注");
        }
        for (int i = 1; i <= 100; i++) {
            vipService.addVip(String.valueOf(i), "测试VIP" + i, "男", String.valueOf((long) (Math.random() * 10000000)), "测试地址" + i, "123456");
        }
        for (int i = 1; i < 100; i++) {
            recordService.addRecord(String.valueOf((int) (Math.random() * 100) + 1), String.valueOf((int) (Math.random() * 100) + 1));
        }
    }
}
