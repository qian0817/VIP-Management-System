package qianlei.dao;

import qianlei.entity.Record;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 记录的dao层
 *
 * @author qianlei
 */
public class RecordDao {
    /**
     * 根据vip的id name和phone获取记录
     *
     * @param getId    获取到的id
     * @param getName  获取到的name
     * @param getPhone 获取到的phone
     * @return 符合条件的vip
     */
    public List<Record> selectAllRecordByIdAndNameAndPhone(String getId, String getName, String getPhone) {
        ResultSet resultSet = null;
        List<Record> recordList = new LinkedList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT r.id AS record_id, r.vipId AS vip_id," +
                        "r.price AS record_price, r.createTime AS record_create_time, " +
                        "v.phone AS vip_phone ,v.name AS vip_name," +
                        "v.status AS vip_status " +
                        "FROM record r " +
                        "LEFT JOIN vip v on r.vipId = v.id " +
                        "WHERE v.id LIKE ? AND v.name LIKE ? AND v.phone LIKE ? " +
                        "ORDER BY LENGTH(r.id) LIMIT 500")
        ) {
            statement.setString(1, "%" + getId + "%");
            statement.setString(2, "%" + getName + "%");
            statement.setString(3, "%" + getPhone + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("record_id");
                String vipId = resultSet.getString("vip_id");
                Date createTime = resultSet.getDate("record_create_time");
                StatusEnum vipStatus = StatusEnum.getById(resultSet.getInt("vip_status"));
                String phone = resultSet.getString("vip_phone");
                String vipName = resultSet.getString("vip_name");
                BigDecimal price = resultSet.getBigDecimal("record_price");
                Record record = new Record();
                Vip vip = new Vip();
                vip.setId(vipId);
                vip.setName(vipName);
                vip.setPhone(phone);
                vip.setStatus(vipStatus);
                record.setId(id);
                record.setVipId(vipId);
                record.setCreateTime(createTime);
                record.setVip(vip);
                record.setPrice(price);
                recordList.add(record);
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
    public void addRecord(Record record) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO record(id ,vipId, createtime,price)" +
                        " VALUES (?,?,?,?)")
        ) {
            statement.setString(1, record.getId());
            statement.setString(2, record.getVipId());
            statement.setDate(3, new java.sql.Date(record.getCreateTime().getTime()));
            statement.setBigDecimal(4, record.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }
}
