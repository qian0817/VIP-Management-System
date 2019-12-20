package qianlei.dao;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.User;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户的数据库层
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
    public User getUserByName(String name) {
        User user = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,password FROM user  WHERE username = ?")
        ) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                user = new User(id, name, password);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
        return user;
    }

    /**
     * 添加用户
     *
     * @param user 需要添加的用户
     */
    public void addUser(@NotNull User user) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)")
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 修改账号
     *
     * @param user 修改后的账号
     */
    public void updateUser(@NotNull User user) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ?")
        ) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }
}
