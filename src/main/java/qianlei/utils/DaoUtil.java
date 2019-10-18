package qianlei.utils;

import java.sql.*;

public class DaoUtil {
    public static void init() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS user(" +
                    "username VARCHAR(20) NOT NULL ," +
                    "password VARCHAR(20) NOT NULL " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS vip(" +
                    "id VARCHAR(20) NOT NULL PRIMARY KEY ," +
                    "name VARCHAR(20) NOT NULL ," +
                    "sex CHAR(1) NOT NULL ," +
                    "phone VARCHAR(20) NOT NULL ," +
                    "address VARCHAR(100) NOT NULL ," +
                    "postcode INT NOT NULL ," +
                    "createTime DATE NOT NULL ," +
                    "status INT NOT NULL " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS good(" +
                    "id VARCHAR(20) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL ," +
                    "maker VARCHAR(20) NOT NULL ," +
                    "createTime DATE NOT NULL ," +
                    "price DECIMAL NOT NULL ," +
                    "discount DOUBLE NOT NULL ," +
                    "remain LONG NOT NULL ," +
                    "introduction TEXT NOT NULL ," +
                    "remarks TEXT NOT NULL ," +
                    "status INT NOT NULL " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS record(" +
                    "id INTEGER NOT NULL PRIMARY KEY, " +
                    "userId VARCHAR(20) NOT NULL ," +
                    "goodId VARCHAR(20) NOT NULL ," +
                    "createTime DATE NOT NULL" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }


    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:main.db");
    }

    public static void close(Connection connection, Statement statement) {
        closeStatement(statement);
        closeConnection(connection);
    }

    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {

            }
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {

            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        DaoUtil.close(connection, statement);
        DaoUtil.closeResultSet(resultSet);
    }
}
