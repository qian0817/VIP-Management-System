package qianlei.dao;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.RecordDetail;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 详细记录的数据库层
 *
 * @author qianlei
 */
public class RecordDetailDao {
    /**
     * 添加具体的记录
     *
     * @param detail 需要添加的记录
     */
    public void addRecordDetail(@NotNull RecordDetail detail) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO record_detail(recordNo, goodNo,userId," +
                        " goodName, price, number) VALUES (?,?,?,?,?,?)")
        ) {
            statement.setString(1, detail.getRecordNo());
            statement.setString(2, detail.getGoodNo());
            statement.setInt(3, detail.getUserId());
            statement.setString(4, detail.getGoodName());
            statement.setBigDecimal(5, detail.getPrice());
            statement.setInt(6, detail.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据订单号和用户的id获取到该订单的具体细节
     *
     * @param recordNo 订单号
     * @param userId   用户的id
     * @return 该订单的具体细节
     */
    public List<RecordDetail> getAllRecordDetailByRecordId(String recordNo, int userId) {
        ResultSet resultSet = null;
        List<RecordDetail> recordList = new ArrayList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, goodNo, goodName, price, number FROM record_detail WHERE recordNo = ? AND userId = ?")
        ) {
            statement.setString(1, recordNo);
            statement.setInt(2, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String goodNo = resultSet.getString("goodNo");
                String goodName = resultSet.getString("goodName");
                BigDecimal price = resultSet.getBigDecimal("price");
                int number = resultSet.getInt("number");
                RecordDetail record = new RecordDetail(id, recordNo, userId, goodNo, goodName, price, number);
                recordList.add(record);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return recordList;
    }
}
