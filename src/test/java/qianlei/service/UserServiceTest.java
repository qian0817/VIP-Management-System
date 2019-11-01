package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private UserService userService = new UserService();

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
    void register() {
        assertDoesNotThrow(() -> userService.register(new User("test", "123456")));
        assertThrows(WrongDataException.class, () -> userService.register(new User("test", "123456")));
        assertThrows(WrongDataException.class, () -> userService.register(new User("test1", "123456+-")));
    }

    @Order(2)
    @Test
    void changePassword() {
        assertDoesNotThrow(() -> userService.changePassword(new User("test", "123456789")));
    }

    @Order(3)
    @Test
    void login() {
        assertDoesNotThrow(() -> userService.login(new User("test", "123456789")));
        assertThrows(WrongDataException.class, () -> userService.login(new User("test", "123456789")));
        assertThrows(WrongDataException.class, () -> userService.login(new User("test1", "123456789")));
    }
}