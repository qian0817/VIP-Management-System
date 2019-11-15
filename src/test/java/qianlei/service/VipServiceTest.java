package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VipServiceTest {

    private VipService vipService = new VipService();

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
    void addVip() {
        assertDoesNotThrow(() -> vipService.addVip(new Vip("test1", "测试VIP1", "男", "12345", "测试地址1", "123456")));
        assertThrows(WrongDataException.class, () -> vipService.addVip(new Vip("test1", "测试VIP1", "男", "12345", "测试地址1", "123456")));
    }

    @Order(2)
    @Test
    void updateVip() {
        assertDoesNotThrow(() -> vipService.updateVip(new Vip("test1", "测试VIP2", "男", "12345", "测试地址2", "123456")));
    }

    @Order(3)
    @Test
    void deleteById() {
        assertDoesNotThrow(() -> vipService.deleteVipById("test1"));
    }
}