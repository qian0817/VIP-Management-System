package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VipDaoTest {
    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private VipDao vipDao = new VipDao();

    @Order(1)
    @Test
    void addVip() {
        Assertions.assertDoesNotThrow(() -> vipDao.addVip(new Vip("test1", "测试VIP1", "男", "123456789"
                , "测试地址1", 123456, new Date(), StatusEnum.Normal)));
        Assertions.assertDoesNotThrow(() -> vipDao.addVip(new Vip("test2", "测试VIP2", "女", "123456789"
                , "测试地址2", 123456, new Date(), StatusEnum.Normal)));
    }

    @Order(4)
    @Test
    void selectVipById() {
        Assertions.assertEquals(vipDao.selectVipById("test1").getStatus(), StatusEnum.Deleted);
        Assertions.assertEquals(vipDao.selectVipById("test2").getName(), "测试VIP3");
    }

    @Order(5)
    @Test
    void selectAllNormalVipByIdAndNameAndPhone() {
        Assertions.assertEquals(vipDao.selectAllNormalVipByIdAndNameAndPhone("", "", "").size(), 2);
    }

    @Order(2)
    @Test
    void deleteById() {
        Assertions.assertDoesNotThrow(() -> vipDao.deleteById("test1"));
    }

    @Order(3)
    @Test
    void updateVip() {
        Assertions.assertDoesNotThrow(() -> vipDao.updateVip(new Vip("test2", "测试VIP3", "女", "123456"
                , "测试地址2", 123456, new Date(), StatusEnum.Normal)));
    }
}