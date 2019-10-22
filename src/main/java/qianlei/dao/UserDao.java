package qianlei.dao;

import qianlei.entity.User;
import qianlei.utils.DaoUtil;

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
    public User getUserByName(String name) {
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
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {

        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)")
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ?")
        ) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
