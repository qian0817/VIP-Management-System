package qianlei.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;

/**
 * dao工具类
 *
 * @author qianlei
 */
public final class DaoUtil {
    private static String url;

    private DaoUtil() {
    }

    /**
     * 初始化数据库
     */
    public static void init(String fileName) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            Log.error(Thread.currentThread(), e);
        }
        url = "jdbc:sqlite:" + fileName + ".db";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             InputStream inputStream = DaoUtil.class.getClassLoader().getResourceAsStream("create.sql")) {
            byte[] bytes = new byte[0];
            if (inputStream != null) {
                bytes = new byte[inputStream.available()];
                //noinspection ResultOfMethodCallIgnored
                inputStream.read(bytes);
            }
            String[] s = new String(bytes, StandardCharsets.UTF_8).split(";");
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
    @NotNull
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }


    /**
     * 关闭resultSet
     *
     * @param resultSet resultSet
     */
    public static void closeResultSet(@Nullable ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                Log.error(Thread.currentThread(), e);
            }
        }
    }
}
