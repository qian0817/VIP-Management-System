package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    private UserDao userDao = new UserDao();

    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    @Order(2)
    @Test
    void getUserByName() {
        Assertions.assertEquals(userDao.getUserByName("测试用户").getUsername(), "测试用户");
    }

    @Order(1)
    @Test
    void addUser() {
        Assertions.assertDoesNotThrow(() -> userDao.addUser(new User("测试用户", "123456")));
    }

    @Order(3)
    @Test
    void updateUser() {
        Assertions.assertDoesNotThrow(() -> userDao.updateUser(new User("测试用户", "123")));
    }
}