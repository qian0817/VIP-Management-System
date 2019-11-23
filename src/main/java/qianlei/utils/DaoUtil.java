package qianlei.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
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
     */
    public static void init(String useDataSource) {
        dataSource = new ComboPooledDataSource(useDataSource);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             InputStream inputStream = DaoUtil.class.getClassLoader().getResourceAsStream("create.sql")
        ) {
            byte[] bytes = new byte[0];
            if (inputStream != null) {
                bytes = new byte[inputStream.available()];
                //noinspection ResultOfMethodCallIgnored
                inputStream.read(bytes);
            }
            String[] s = new String(bytes).split(";");
            for (String sql : s) {
                statement.execute(sql);
            }
        } catch (SQLException | IOException e) {
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
