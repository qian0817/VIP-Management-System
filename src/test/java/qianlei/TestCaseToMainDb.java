package qianlei;

import qianlei.entity.Good;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.service.RecordService;
import qianlei.service.VipService;
import qianlei.utils.DaoUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * 添加测试用例
 * 需先删除main.db文件
 *
 * @author qianlei
 */
public class TestCaseToMainDb {
    private volatile static int j = 0;
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
                Good good = new Good(String.valueOf(i), "测试商品" + i, "测试制造商" + i,
                        new Date((long) (Math.random() * System.currentTimeMillis())),
                        BigDecimal.valueOf(random.nextInt(10000)), (int) (Math.random() * 100) * 1.0 / 100,
                        random.nextInt(100000), "", "", StatusEnum.NORMAL);
                try {
                    goodService.addGood(good);
                } catch (WrongDataException e) {
                    e.printStackTrace();
                }
                j++;
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            Random random = new Random();
            for (int i = 1; i <= n; i++) {
                Vip vip = new Vip(String.valueOf(i), "测试VIP" + i, "男", String.valueOf((long) (Math.random() * 20000000000L)),
                        "测试地址" + i, random.nextInt(900000) + 100000);
                try {
                    vipService.addVip(vip);
                } catch (WrongDataException e) {
                    e.printStackTrace();
                }
                j++;
            }
        });
        t2.start();
        while (j != n * 2) {
            Thread.sleep(2000);
            System.out.println(j * 100.0 / n / 2);
        }
        t1.join();
        t2.join();
        Random random = new Random();
        j = 0;
        new Thread(() -> {
            for (int i = 1; i <= n; i++) {
                try {
                    recordService.addRecord(String.valueOf(random.nextInt(n) + 1), String.valueOf(random.nextInt(n) + 1));
                } catch (WrongDataException e) {
                    e.printStackTrace();
                }
                j++;
            }
        }).start();
        while (j != n) {
            Thread.sleep(2000);
            System.out.println(j * 100.0 / n);
        }
    }
}
