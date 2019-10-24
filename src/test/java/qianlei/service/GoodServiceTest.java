package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoodServiceTest {
    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private GoodService goodService = new GoodService();

    @Order(1)
    @Test
    void addGood() {
        Assertions.assertDoesNotThrow(() -> goodService.addGood("test1", "测试商品", "测试制造商", new Date(),
                "123.45", 0.94, "1234", "测试简介1", "测试备注1"));
        Assertions.assertThrows(WrongDataException.class, () -> goodService.addGood("test1", "测试商品", "测试制造商", new Date(),
                "123.45", 0.94, "1234", "测试简介1", "测试备注1"));
        Assertions.assertThrows(WrongDataException.class, () -> goodService.addGood("test2", "测试商品2", "测试制造商2", new Date(),
                "123.45", 0.94, "1234dw", "测试简介2", "测试备注2"));
        Assertions.assertThrows(WrongDataException.class, () -> goodService.addGood("test2", "测试商品3", "测试制造商3", new Date(),
                "123.45d", 0.94, "1234", "测试简介3", "测试备注3"));
        Assertions.assertThrows(WrongDataException.class, () -> goodService.addGood("te st2", "测试商品4", "测试制造商4", new Date(),
                "123.45", 0.94, "1234", "测试简介4", "测试备注4"));
        Assertions.assertDoesNotThrow(() -> goodService.addGood("test2", "测试商品2", "测试制造商2", new Date(),
                "123.45", 0.94, "1234", "测试简介2", "测试备注2"));
    }

    @Order(5)
    @Test
    void getAllNormalGoodByIdAndName() {
        Assertions.assertEquals(1, goodService.getAllNormalGoodByIdAndName("test2", "2").size());
        Assertions.assertEquals(0, goodService.getAllNormalGoodByIdAndName("test2", "1").size());
    }

    @Order(3)
    @Test
    void getGoodById() {
        Assertions.assertNotNull(goodService.getGoodById("test1"));
        Assertions.assertNull(goodService.getGoodById("test3"));
    }

    @Order(4)
    @Test
    void deleteById() {
        Assertions.assertDoesNotThrow(() -> goodService.deleteGoodById("test1"));
    }

    @Order(2)
    @Test
    void update() {
        Assertions.assertDoesNotThrow(() -> goodService.updateGood("test1", "测试商品2", "测试制造商2",
                "123.45", 0.94, "1234", "测试简介2", "测试备注2"));
    }
}