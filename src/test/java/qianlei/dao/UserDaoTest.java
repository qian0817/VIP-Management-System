package qianlei.dao;

import org.junit.jupiter.api.Test;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;

class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    void addUser() {
        DaoUtil.init();
        userDao.addUser(new User("test", "123"));
    }
}