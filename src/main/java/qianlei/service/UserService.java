package qianlei.service;

import qianlei.dao.UserDao;
import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;

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

    public void setCurUser(User user) {
        UserService.curUser = user;
    }

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 登陆成功返回用户 失败返回null
     */
    public User login(String name, String password) {
        User user = userDao.getUserByName(name);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    /**
     * 用户注册
     *
     * @param name     用户名
     * @param password 密码
     * @throws WrongDataException 输入的数据有格式错误
     */
    public void register(String name, String password) throws WrongDataException {
        if (StringUtil.containsBlank(name)) {
            throw new WrongDataException("用户名中不能有空格");
        }
        if (StringUtil.isNotPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        if (userDao.getUserByName(name) != null) {
            throw new WrongDataException("该用户名已被注册");
        }
        userDao.addUser(new User(name, password));
    }

    /**
     * 修改密码
     *
     * @param name     用户名
     * @param password 密码
     * @throws WrongDataException 输入的数据有格式错误
     */
    public void changePassword(String name, String password) throws WrongDataException {
        if (StringUtil.isNotPassword(password)) {
            throw new WrongDataException("密码格式错误，只能由字母数字和下划线构成,长度为6-16位");
        }
        User user = new User(name, password);
        userDao.updateUser(user);
    }
}
