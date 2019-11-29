package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.entity.Record;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordDaoTest {
    private static VipDao vipDao = new VipDao();
    private static GoodDao goodDao = new GoodDao();
    private RecordDao recordDao = new RecordDao();

    @BeforeAll
    static void start() {
        DaoUtil.init("test");
        goodDao.addGood(new Good("record-test-good", "测试商品1", "测试制造商1", new Date(),
                new BigDecimal(123), new BigDecimal("0.65"), 123L, "测试1", "测试1", StatusEnum.NORMAL));
        vipDao.addVip(new Vip("record-test-vip", "测试VIP1", "男", "123456789"
                , "测试地址1", "123456", new Date(), StatusEnum.NORMAL));
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addRecord() {
        assertDoesNotThrow(() -> recordDao.addRecord(new Record("record-test", "record-test-vip", new Date(), BigDecimal.valueOf(1L))));
    }

    @Order(2)
    @Test
    void selectAllRecord() {
        assertEquals(recordDao.selectAllRecordByIdAndNameAndPhone("record-test-vip", "", "").size(), 1);
    }
}