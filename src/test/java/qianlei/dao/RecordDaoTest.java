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
    private RecordDao recordDao = new RecordDao();
    private VipDao vipDao = new VipDao();
    private GoodDao goodDao = new GoodDao();

    @BeforeAll
    static void start() {
        DaoUtil.init("test");
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addRecord() {
        goodDao.addGood(new Good("1", "测试商品1", "测试制造商1", new Date(),
                new BigDecimal(123), 0.65, 123L, "测试1", "测试1", StatusEnum.NORMAL));
        vipDao.addVip(new Vip("1", "测试VIP1", "男", "123456789"
                , "测试地址1", "123456", new Date(), StatusEnum.NORMAL));
        assertDoesNotThrow(() -> recordDao.addRecord(new Record(1, "1", "1", new Date(), BigDecimal.valueOf(1L))));
    }

    @Order(2)
    @Test
    void selectAllRecord() {
        assertEquals(recordDao.selectAllRecordByIdAndNameAndPhone("", "", "").size(), 1);
    }
}