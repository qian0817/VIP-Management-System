package qianlei.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import qianlei.dao.UserDao;
import qianlei.entity.Result;
import qianlei.entity.User;

/**
 * 处理用户服务类
 *
 * @author qianlei
 */
public class UserService {
    private static User curUser;
    private UserDao userDao = new UserDao();

    public static User getCurUser() {
        return curUser;
    }

    @TestOnly
    public static void setCurUser(User curUser) {
        UserService.curUser = curUser;
    }

    /**
     * 用户登录
     *
     * @param user 需要登录的用户
     */
    public Result login(@NotNull User user) {
        User existUser = userDao.getUserByName(user.getUsername());
        if (existUser == null || !existUser.getPassword().equals(user.getPassword())) {
            return new Result(false, "用户名或密码错误");
        }
        curUser = userDao.getUserByName(user.getUsername());
        return new Result(true, "登陆成功");
    }

    /**
     * 用户注册
     *
     * @param user 需要注册的用户
     */
    public Result register(@NotNull User user) {
        if (userDao.getUserByName(user.getUsername()) != null) {
            return new Result(false, "该用户名已被注册");
        }
        userDao.addUser(user);
        return new Result(true, "注册成功");
    }

    /**
     * 修改密码
     *
     * @param user 修改后的用户名
     */
    public Result changePassword(@NotNull User user) {
        userDao.updateUser(user);
        return new Result(true, "修改密码成功");
    }
}
