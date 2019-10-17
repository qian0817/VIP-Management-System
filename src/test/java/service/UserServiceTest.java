package service;

import exception.WrongDataException;
import org.junit.jupiter.api.Test;

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