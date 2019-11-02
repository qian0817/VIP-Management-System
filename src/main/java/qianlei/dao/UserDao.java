package qianlei.dao;

import qianlei.entity.User;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;
import qianlei.utils.LogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * user的数据库层
 *
 * @author qianlei
 */
public class UserDao {
    /**
     * 根据用户名获取名称
     *
     * @param name 用户名称
     * @return 该名称的用户
     */
    public User getUserByName(String name) throws WrongDataException {
        User user = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT username,password FROM user  WHERE username = ?")
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                user = new User(name, password);
            }
        } catch (SQLException e) {
            LogUtil.error(e);
            throw new WrongDataException("数据库错误");
        }
        return user;
    }

    /**
     * 添加用户
     *
     * @param user 需要添加的用户
     */
    public void addUser(User user) throws WrongDataException {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)")
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.error(e);
            throw new WrongDataException("数据库错误");
        }
    }

    /**
     * 修改账号
     *
     * @param user 修改后的账号
     */
    public void updateUser(User user) throws WrongDataException {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ?")
        ) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            LogUtil.error(e);
            throw new WrongDataException("数据库错误");
        }
    }
}
