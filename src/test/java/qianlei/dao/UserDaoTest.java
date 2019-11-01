package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    private UserDao userDao = new UserDao();

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
    void addUser() {
        assertDoesNotThrow(() -> userDao.addUser(new User("测试用户", "123456")));
    }

    @Order(2)
    @Test
    void getUserByName() {
        assertEquals(userDao.getUserByName("测试用户").getUsername(), "测试用户");
    }

    @Order(3)
    @Test
    void updateUser() {
        assertDoesNotThrow(() -> userDao.updateUser(new User("测试用户", "123")));
    }
}