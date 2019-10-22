package qianlei.utils;

import java.sql.*;

/**
 * dao工具类
 *
 * @author qianlei
 */
public class DaoUtil {
    private static String fileName;

    /**
     * 初始化数据库
     *
     * @param fileName 数据库文件名称
     */
    public static void init(String fileName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DaoUtil.fileName = fileName;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
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
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     * @throws SQLException sql错误
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + fileName);
    }


    /**
     * 关闭resultSet
     *
     * @param resultSet resultSet
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
