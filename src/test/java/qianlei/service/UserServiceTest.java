package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private UserService userService = new UserService();

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
    void register() {
        assertTrue(userService.register(new User("test", "123456")).isSuccess());
        assertFalse(userService.register(new User("test", "123456")).isSuccess());
    }

    @Order(2)
    @Test
    void changePassword() {
        assertDoesNotThrow(() -> userService.changePassword(new User("test", "123456789")));
    }

    @Order(3)
    @Test
    void login() {
        assertTrue(userService.login(new User("test", "123456789")).isSuccess());
        assertFalse(userService.login(new User("test", "123456")).isSuccess());
        assertFalse(userService.login(new User("test1", "123456789")).isSuccess());
    }
}