package dao;

import entity.User;
import org.junit.jupiter.api.Test;
import utils.DaoUtil;

class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    void addUser() {
        DaoUtil.init();
        userDao.addUser(new User("test", "123"));
    }
}