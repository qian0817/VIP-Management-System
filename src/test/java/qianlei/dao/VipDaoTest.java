package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Vip;
import qianlei.utils.DaoUtil;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VipDaoTest {
    private VipDao vipDao = new VipDao();
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
    void addVip() {
        assertDoesNotThrow(() -> vipDao.addVip(new Vip(1, "test1", "测试VIP1", "男", "123456789"
                , "测试地址1", "123456", new Date())));
        assertDoesNotThrow(() -> vipDao.addVip(new Vip(1, "test2", "测试VIP2", "女", "123456789"
                , "测试地址2", "123456", new Date())));
    }


    @Order(2)
    @Test
    void updateVip() {
        assertDoesNotThrow(() -> vipDao.updateVip(new Vip(1, "test2", "测试VIP3", "女", "123456"
                , "测试地址2", "123456", new Date())));
    }

    @Order(3)
    @Test
    void selectVipById() {
        assertEquals(vipDao.selectVipById("test1", 1).getName(), "测试VIP1");
        assertEquals(vipDao.selectVipById("test2", 1).getName(), "测试VIP3");
    }

    @Order(4)
    @Test
    void selectAllNormalVipByIdAndNameAndPhone() {
        assertEquals(vipDao.selectAllNormalVipByIdAndNameAndPhone("", "", "", 1).size(), 2);
    }

    @Order(5)
    @Test
    void deleteById() {
        assertDoesNotThrow(() -> vipDao.deleteById(new Vip().setVipNo("test1").setUserId(1)));
        assertDoesNotThrow(() -> vipDao.deleteById(new Vip().setVipNo("test2").setUserId(1)));
    }
}