package qianlei.dao;

import qianlei.entity.RecordDetail;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qianlei
 */
public class RecordDetailDao {
    public void addRecordDetail(RecordDetail detail) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO record_detail(recordId, goodId, goodName, price, number) VALUES (?,?,?,?,?)")
        ) {
            statement.setString(1, detail.getRecordId());
            statement.setString(2, detail.getGoodId());
            statement.setString(3, detail.getGoodName());
            statement.setBigDecimal(4, detail.getPrice());
            statement.setInt(5, detail.getNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    public List<RecordDetail> getAllRecordDetailByRecordId(String recordId) {
        ResultSet resultSet = null;
        List<RecordDetail> recordList = new LinkedList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT goodId, goodName, price, number FROM record_detail WHERE recordId = ?")
        ) {
            statement.setString(1, recordId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String goodId = resultSet.getString("goodId");
                String goodName = resultSet.getString("goodName");
                BigDecimal price = resultSet.getBigDecimal("price");
                int number = resultSet.getInt("number");
                RecordDetail record = new RecordDetail();
                record.setRecordId(recordId);
                record.setGoodId(goodId);
                record.setGoodName(goodName);
                record.setPrice(price);
                record.setNumber(number);
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
