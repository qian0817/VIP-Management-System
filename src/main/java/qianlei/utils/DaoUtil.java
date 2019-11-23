package qianlei.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * dao工具类
 *
 * @author qianlei
 */
public final class DaoUtil {
    private static ComboPooledDataSource dataSource;
    private DaoUtil() {
    }

    /**
     * 初始化数据库
     *
     */
    public static void init(String useDataSource) {
        dataSource = new ComboPooledDataSource(useDataSource);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user(" +
                    "username VARCHAR(20) NOT NULL," +
                    "password VARCHAR(20) NOT NULL " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS vip(" +
                    "id VARCHAR(20) NOT NULL PRIMARY KEY ," +
                    "name VARCHAR(20) NOT NULL ," +
                    "sex CHAR(1)  ," +
                    "phone VARCHAR(20)  ," +
                    "address VARCHAR(100)  ," +
                    "email VARCHAR(30)  ," +
                    "createTime DATE NOT NULL ," +
                    "status INT NOT NULL " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS good(" +
                    "id VARCHAR(20) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL ," +
                    "maker VARCHAR(20)  ," +
                    "createTime DATE  ," +
                    "price DECIMAL ," +
                    "discount DOUBLE  ," +
                    "remain LONG  ," +
                    "introduction TEXT  ," +
                    "remarks TEXT  ," +
                    "status INT " +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS record(" +
                    "id INTEGER NOT NULL PRIMARY KEY, " +
                    "vipId VARCHAR(20) NOT NULL ," +
                    "goodId VARCHAR(20) NOT NULL ," +
                    "createTime DATE NOT NULL ," +
                    "price DECIMAL NOT NULL " +
                    ")");
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     * @throws SQLException sql错误
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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
                Log.error(Thread.currentThread(), e);
            }
        }
    }
}
