package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VipServiceTest {

    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private VipService vipService = new VipService();

    @Order(1)
    @Test
    void addVip() {
        Assertions.assertDoesNotThrow(() -> vipService.addVip("test1", "测试VIP1", "男", "12345", "测试地址1", "123456"));
        Assertions.assertThrows(WrongDataException.class, () -> vipService.addVip("test1", "测试VIP1", "男", "12345", "测试地址1", "123456"));
        Assertions.assertThrows(WrongDataException.class, () -> vipService.addVip("test2", "测试VIP1", "男", "12345", "测试地址1", "1234567"));
        Assertions.assertThrows(WrongDataException.class, () -> vipService.addVip("test1", "测试VIP1", "男", "12345e", "测试地址1", "123456"));
    }

    @Order(3)
    @Test
    void deleteById() {
        Assertions.assertDoesNotThrow(() -> vipService.deleteById("test1"));
    }

    @Order(2)
    @Test
    void updateVip() {
        Assertions.assertDoesNotThrow(() -> vipService.updateVip("test1", "测试VIP2", "男", "12345", "测试地址2", "123456"));
    }
}