package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodServiceTest {
    private GoodService goodService = new GoodService();

    @BeforeAll
    static void start() {
        DaoUtil.init("test.db");
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addGood() {
        assertDoesNotThrow(() -> goodService.addGood(new Good("1", "测试商品", "测试制造商", new Date(),
                new BigDecimal("123.45"), 0.94, 1234, "测试简介1", "测试备注1", StatusEnum.NORMAL)));
        assertThrows(WrongDataException.class, () -> goodService.addGood(new Good("1", "测试商品", "测试制造商",
                new Date(), new BigDecimal("123.45"), 0.94, 1234, "测试简介1", "测试备注1", StatusEnum.NORMAL)));
        assertDoesNotThrow(() -> goodService.addGood(new Good("2", "测试商品", "测试制造商", new Date(), new BigDecimal("123.45"), 0.94, 1234, "测试简介1", "测试备注1", StatusEnum.NORMAL)));
    }

    @Order(2)
    @Test
    void update() {
        assertDoesNotThrow(() -> goodService.updateGood(new Good("2", "测试商品2", "测试制造商", new Date(), new BigDecimal("123.45"), 0.94, 1234, "测试简介1", "测试备注1", StatusEnum.NORMAL)));
    }

    @Order(3)
    @Test
    void getGoodById() throws WrongDataException {
        assertNotNull(goodService.getGoodById("1"));
        assertNull(goodService.getGoodById("3"));
    }

    @Order(4)
    @Test
    void deleteById() {
        assertDoesNotThrow(() -> goodService.deleteGoodById("1"));
    }

    @Order(5)
    @Test
    void getAllNormalGoodByIdAndName() throws WrongDataException {
        assertEquals(1, goodService.getAllNormalGoodByIdAndName("2", "2").size());
        assertEquals(0, goodService.getAllNormalGoodByIdAndName("2", "1").size());
    }

}