package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodDaoTest {
    private GoodDao goodDao = new GoodDao();

    @BeforeAll
    static void start() {
        TestHelper.deleteTestDb();
        DaoUtil.init("test");
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addGood() {
        assertDoesNotThrow(() -> goodDao.addGood(new Good(1, "1", "测试商品1", "测试制造商1", new Date(),
                new BigDecimal(123), new BigDecimal("0.65"), 123L, "测试1", "测试1")));
        assertDoesNotThrow(() -> goodDao.addGood(new Good(1, "2", "测试商品2", "测试制造商2", new Date(),
                new BigDecimal(123), new BigDecimal("0.65"), 123L, "测试2", "测试2")));
    }


    @Order(2)
    @Test
    void updateGood() {
        assertDoesNotThrow(() -> goodDao.updateGood(new Good(1, "2", "测试商品3", "测试制造商3", new Date(),
                new BigDecimal(123), new BigDecimal("0.65"), 123L, "测试3", "测试3")));
    }


    @Order(3)
    @Test
    void selectAllNormalGoodByNoAndName() {
        assertEquals(2, goodDao.selectAllNormalGoodByNoAndName("", "", 1).size());
        assertEquals(1, goodDao.selectAllNormalGoodByNoAndName("1", "", 1).size());
    }

    @Order(4)
    @Test
    void selectGoodByGoodNo() {
        assertEquals(goodDao.selectGoodByGoodNo("1", 1).getName(), "测试商品1");
        assertEquals(goodDao.selectGoodByGoodNo("2", 1).getName(), "测试商品3");
    }


    @Order(5)
    @Test
    void deleteById() {
        assertDoesNotThrow(() -> goodDao.deleteById(new Good().setUserId(1).setGoodNo("1")));
        assertDoesNotThrow(() -> goodDao.deleteById(new Good().setUserId(1).setGoodNo("2")));
    }
}