package qianlei.service;

import org.junit.jupiter.api.Test;
import qianlei.exception.WrongDataException;

class UserServiceTest {
    private UserService userService = new UserService();

    @Test
    void login() {
        userService.login("test", "123456");
    }

    @Test
    void register() throws WrongDataException {
        userService.register("test", "123456");
    }
}