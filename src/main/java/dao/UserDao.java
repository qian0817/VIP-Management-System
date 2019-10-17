package dao;

import entity.User;
import utils.DaoUtil;

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
        Connection connection = null;
        PreparedStatement statement = null;
        User user;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT username,password FROM user  WHERE username = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                user = new User(name, password);
                return user;
            }
        } catch (SQLException ignored) {
        } finally {
            DaoUtil.close(connection, statement);
        }
        return null;
    }

    public void addUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO user (username,password) VALUES (?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException ignored) {
        } finally {
            DaoUtil.close(connection, statement);
        }
    }
}
