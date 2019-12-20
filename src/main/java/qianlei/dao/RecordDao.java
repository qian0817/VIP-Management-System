package qianlei.dao;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.Record;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 购买记录的数据库层
 *
 * @author qianlei
 */
public class RecordDao {
    /**
     * 根据vip的id name和phone获取记录
     *
     * @param getVipNo 会员卡号
     * @param getName  会员姓名
     * @param getPhone 会员手机号
     * @return 符合条件的vip
     */
    public List<Record> selectAllRecordByIdAndNameAndPhone(String getVipNo, String getName, String getPhone, int userId) {
        ResultSet resultSet = null;
        List<Record> recordList = new ArrayList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id, userId, recordNo," +
                        " vipId, vipName, vipPhone, createTime, price FROM record WHERE vipid LIKE ? " +
                        "AND vipname LIKE ? AND vipPhone LIKE ? AND userId=?" +
                        "ORDER BY LENGTH(recordNo) LIMIT 500")
        ) {
            statement.setString(1, "%" + getVipNo + "%");
            statement.setString(2, "%" + getName + "%");
            statement.setString(3, "%" + getPhone + "%");
            statement.setInt(4, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recordList.add(getRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return recordList;
    }

    /**
     * 添加记录
     *
     * @param record 需要添加的记录
     */
    public void addRecord(@NotNull Record record) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO record(userid, recordno, " +
                        "vipid, vipname, vipPhone, createtime, price) VALUES (?,?,?,?,?,?,?)")
        ) {
            statement.setInt(1, record.getUserId());
            statement.setString(2, record.getRecordNo());
            statement.setString(3, record.getVipId());
            statement.setString(4, record.getVipName());
            statement.setString(5, record.getVipPhone());
            statement.setDate(6, new java.sql.Date(record.getCreateTime().getTime()));
            statement.setBigDecimal(7, record.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    public List<Record> selectAllRecordByRecordNo(String recordId, int userId) {
        List<Record> recordList = new ArrayList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM record WHERE userId=? AND recordNo = ?")
        ) {
            statement.setInt(1, userId);
            statement.setString(2, recordId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                recordList.add(getRecordFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
        return recordList;
    }

    @NotNull
    private Record getRecordFromResultSet(@NotNull ResultSet resultSet) throws SQLException {
        Record record = new Record();
        Integer id = resultSet.getInt("id");
        Integer userId = resultSet.getInt("userId");
        String recordNo = resultSet.getString("recordNo");
        String vipId = resultSet.getString("vipId");
        String vipName = resultSet.getString("vipName");
        String vipAddress = resultSet.getString("vipPhone");
        Date createTime = resultSet.getDate("createTime");
        BigDecimal price = resultSet.getBigDecimal("price");

        record.setId(id);
        record.setUserId(userId);
        record.setRecordNo(recordNo);
        record.setUserId(userId);
        record.setVipId(vipId);
        record.setVipName(vipName);
        record.setVipPhone(vipAddress);
        record.setCreateTime(createTime);
        record.setPrice(price);
        return record;
    }
}
