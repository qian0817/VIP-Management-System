package qianlei;

import qianlei.service.GoodService;
import qianlei.service.RecordService;
import qianlei.service.VipService;
import qianlei.utils.DaoUtil;

import java.io.File;
import java.util.Date;
import java.util.Random;

/**
 * 添加测试用例
 *
 * @author qianlei
 */
public class TestCaseToMainDb {
    public static void main(String[] args) throws InterruptedException {
        if (new File("main.db").exists()) {
            return;
        }
        int n = 100000;
        GoodService goodService = new GoodService();
        RecordService recordService = new RecordService();
        VipService vipService = new VipService();
        DaoUtil.init("main.db");
        Thread t1 = new Thread(() -> {
            Random random = new Random();
            for (int i = 1; i <= n; i++) {
                goodService.addGood(String.valueOf(i), "测试商品" + i, "测试制造商" + i, new Date((long) (Math.random() * System.currentTimeMillis())),
                        String.valueOf(random.nextInt(10000)), Math.random() * 100000, String.valueOf(random.nextInt(100000)), "", "");
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            for (int i = 1; i <= n; i++) {
                vipService.addVip(String.valueOf(i), "测试VIP" + i, "男", String.valueOf((long) (Math.random() * 17857694680L)), "测试地址" + i,
                        String.valueOf(random.nextInt(900000) + 100000));
            }
        });
        t2.start();
        t1.join();
        t2.join();
        Random random = new Random();
        for (int i = 1; i <= n; i++) {
            recordService.addRecord(String.valueOf(random.nextInt(n) + 1), String.valueOf(random.nextInt(n) + 1));
        }
    }
}
