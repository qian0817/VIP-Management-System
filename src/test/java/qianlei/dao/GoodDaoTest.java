package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodDaoTest {
    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private GoodDao goodDao = new GoodDao();

    @Order(1)
    @Test
    void addGood() {
        Assertions.assertDoesNotThrow(() -> goodDao.addGood(new Good("Test1", "测试商品1", "测试制造商1", new Date(),
                new BigDecimal(123), 0.65, 123L, "测试1", "测试1", StatusEnum.Normal)));
        Assertions.assertDoesNotThrow(() -> goodDao.addGood(new Good("Test2", "测试商品2", "测试制造商2", new Date(),
                new BigDecimal(123), 0.65, 123L, "测试2", "测试2", StatusEnum.Normal)));
    }

    @Order(5)
    @Test
    void selectGoodById() {
        Assertions.assertEquals(goodDao.selectGoodById("Test1").getStatus(), StatusEnum.Deleted);
        Assertions.assertEquals(goodDao.selectGoodById("Test2").getName(), "测试商品3");
    }

    @Order(4)
    @Test
    void selectAllNormalByIdAndName() {
        Assertions.assertEquals(2, goodDao.selectAllNormalByIdAndName("", "").size());
    }

    @Order(2)
    @Test
    void deleteById() {
        Assertions.assertDoesNotThrow(() -> goodDao.deleteById("Test1"));
    }

    @Order(3)
    @Test
    void updateGood() {
        Assertions.assertDoesNotThrow(() -> goodDao.updateGood(new Good("Test2", "测试商品3", "测试制造商3", new Date(),
                new BigDecimal(123), 0.65, 123L, "测试3", "测试3", StatusEnum.Normal)));
    }
}