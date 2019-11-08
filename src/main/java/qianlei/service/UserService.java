package qianlei.service;

import qianlei.dao.UserDao;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;

/**
 * 处理用户服务类
 *
 * @author qianlei
 */
public class UserService {
    private static User curUser;
    private UserDao userDao = new UserDao();

    public User getCurUser() {
        return curUser;
    }

    /**
     * 用户登录
     *
     * @param user 需要登录的用户
     */
    public void login(User user) throws WrongDataException {
        User existUser = userDao.getUserByName(user.getUsername());
        if (existUser == null || !existUser.getPassword().equals(user.getPassword())) {
            throw new WrongDataException("用户名或密码错误");
        }
        curUser = user;
    }

    /**
     * 用户注册
     *
     * @param user 需要注册的用户
     */
    public void register(User user) throws WrongDataException {
        if (userDao.getUserByName(user.getUsername()) != null) {
            throw new WrongDataException("该用户名已被注册");
        }
        userDao.addUser(user);
    }

    /**
     * 修改密码
     *@param user 修改后的用户名
     */
    public void changePassword(User user) {
        userDao.updateUser(user);
    }
}
