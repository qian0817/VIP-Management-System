package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private UserService userService = new UserService();

    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    @Order(3)
    @Test
    void login() {
        Assertions.assertNotNull(userService.login("test", "123456789"));
        Assertions.assertNull(userService.login("test", "123456"));
        Assertions.assertNull(userService.login("test1", "1234569"));
    }

    @Order(1)
    @Test
    void register() {

        Assertions.assertDoesNotThrow(() -> userService.register("test", "123456"));
        Assertions.assertThrows(WrongDataException.class, () -> userService.register("te st", "123456"));
        Assertions.assertThrows(WrongDataException.class, () -> userService.register("test1", "123456+-"));
        Assertions.assertThrows(WrongDataException.class, () -> userService.register("test", "123456"));
    }

    @Order(2)
    @Test
    void changePassword() {
        Assertions.assertDoesNotThrow(() -> userService.changePassword("test", "123456789"));
    }
}