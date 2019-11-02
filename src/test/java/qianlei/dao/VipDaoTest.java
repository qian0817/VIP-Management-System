package qianlei.dao;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import qianlei.TestHelper;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertDoesNotThrow(() -> vipDao.addVip(new Vip("test1", "测试VIP1", "男", "123456789"
                , "测试地址1", 123456, new Date(), StatusEnum.NORMAL)));
        assertDoesNotThrow(() -> vipDao.addVip(new Vip("test2", "测试VIP2", "女", "123456789"
                , "测试地址2", 123456, new Date(), StatusEnum.NORMAL)));
    }

    @Order(2)
    @Test
    void deleteById() {
        assertDoesNotThrow(() -> vipDao.deleteById("test1"));
    }

    @Order(3)
    @Test
    void updateVip() {
        assertDoesNotThrow(() -> vipDao.updateVip(new Vip("test2", "测试VIP3", "女", "123456"
                , "测试地址2", 123456, new Date(), StatusEnum.NORMAL)));
    }

    @Order(4)
    @Test
    void selectVipById() throws WrongDataException {
        assertEquals(vipDao.selectVipById("test1").getStatus(), StatusEnum.DELETED);
        assertEquals(vipDao.selectVipById("test2").getName(), "测试VIP3");
    }

    @Order(5)
    @Test
    void selectAllNormalVipByIdAndNameAndPhone() throws WrongDataException {
        assertEquals(vipDao.selectAllNormalVipByIdAndNameAndPhone("", "", "").size(), 2);
    }
}