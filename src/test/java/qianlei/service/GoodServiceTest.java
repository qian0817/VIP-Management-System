package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodServiceTest {
    private GoodService goodService = new GoodService();

    @BeforeAll
    static void start() {
        TestHelper.deleteTestDb();
        DaoUtil.init("test");
        UserService.setCurUser(new User(1, "test", "123456"));
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addGood() {
        assertTrue(goodService.addGood(new Good(1, 1, "1", "测试商品", "测试制造商", new Date(), new BigDecimal("123.45"),
                new BigDecimal("0.94"), 1234, "测试简介1", "测试备注1")).isSuccess());
        assertFalse(goodService.addGood(new Good(1, 1, "1", "测试商品", "测试制造商", new Date(), new BigDecimal("123.45"),
                new BigDecimal("0.94"), 1234, "测试简介1", "测试备注1")).isSuccess());
        assertTrue(goodService.addGood(new Good(1, 1, "2", "测试商品", "测试制造商", new Date(), new BigDecimal("123.45"),
                new BigDecimal("0.94"), 1234, "测试简介1", "测试备注1")).isSuccess());
    }

    @Order(2)
    @Test
    void updateGood() {
        assertDoesNotThrow(() -> goodService.updateGood(new Good(1, 1, "1", "测试商品2", "测试制造商", new Date(), new BigDecimal("123.45"),
                new BigDecimal("0.94"), 1234, "测试简介1", "测试备注1")));
    }

    @Order(3)
    @Test
    void getGoodByGoodNo() {
        assertEquals(goodService.getGoodByGoodNo("1").getName(), "测试商品2");
    }

    @Order(4)
    @Test
    void deleteGood() {
        Good good = new Good();
        good.setUserId(1);
        good.setGoodNo("1");
        assertDoesNotThrow(() -> goodService.deleteGood(new Good(1, 1, "1", "测试商品", "测试制造商", new Date(), new BigDecimal("123.45"),
                new BigDecimal("0.94"), 1234, "测试简介1", "测试备注1")));
    }

    @Order(5)
    @Test
    void selectAllNormalGoodByNoAndName() {
        assertEquals(1, goodService.selectAllNormalGoodByNoAndName("2", "测试商品").size());
        assertEquals(1, goodService.selectAllNormalGoodByNoAndName("2", "").size());
    }

}