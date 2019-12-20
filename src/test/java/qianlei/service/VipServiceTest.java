package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.User;
import qianlei.entity.Vip;
import qianlei.utils.DaoUtil;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VipServiceTest {

    private VipService vipService = new VipService();

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
    void addVip() {
        assertTrue(vipService.addVip(new Vip(1, "test1", "测试VIP1", "男", "12345", "测试地址1", "1@q.c")).isSuccess());
        assertFalse(vipService.addVip(new Vip(1, "test1", "测试VIP1", "男", "12345", "测试地址1", "1@q.c")).isSuccess());
    }

    @Order(2)
    @Test
    void getAllNormalVipByIdAndNameAndPhone() {
        assertEquals(vipService.getAllNormalVipByIdAndNameAndPhone("test", "", "").size(), 1);
        assertEquals(vipService.getAllNormalVipByIdAndNameAndPhone("test", "t", "").size(), 0);
    }

    @Order(3)
    @Test
    void updateVip() {
        assertDoesNotThrow(() -> vipService.updateVip(new Vip(1, "test1", "测试VIP2", "男", "12345", "测试地址2", "123456")));
    }

    @Order(4)
    @Test
    void getVipById() {
        assertNull(vipService.getVipById("test"));
        assertEquals(vipService.getVipById("test1").getVipNo(), "test1");
    }

    @Order(5)
    @Test
    void deleteVipById() {
        assertDoesNotThrow(() -> vipService.deleteVipById(new Vip().setUserId(1).setVipNo("1")));
    }


}