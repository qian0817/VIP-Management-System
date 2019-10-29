package qianlei.dao;

import qianlei.entity.Good;
import qianlei.entity.Record;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

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
     * 选择所有记录
     *
     * @return 所有记录
     */
    public List<Record> selectAllRecordByIdAndNameAndPhone(String getId, String getName, String getPhone) {
        ResultSet resultSet = null;
        List<Record> recordList = new LinkedList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT r.id AS record_id, r.userId AS vip_id," +
                        "r.price AS record_price ," +
                        "r.goodId AS good_id, r.createTime AS record_create_time, v.phone AS vip_phone ,v.name AS vip_name," +
                        "v.status AS vip_status,g.name AS  good_name,g.status AS good_status " +
                        "FROM record r " +
                        "LEFT JOIN good g on r.goodId = g.id " +
                        "LEFT JOIN vip v on r.userId = v.id " +
                        "WHERE v.id LIKE ? AND v.name LIKE ? AND v.phone LIKE ?" +
                        "LIMIT 500")
        ) {
            statement.setString(1, "%" + getId + "%");
            statement.setString(2, "%" + getName + "%");
            statement.setString(3, "%" + getPhone + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("record_id");
                String vipId = resultSet.getString("vip_id");
                String goodId = resultSet.getString("good_id");
                Date createTime = resultSet.getDate("record_create_time");
                StatusEnum vipStatus = StatusEnum.getById(resultSet.getInt("vip_status"));
                String phone = resultSet.getString("vip_phone");
                String vipName = resultSet.getString("vip_name");
                String goodName = resultSet.getString("good_name");
                StatusEnum goodStatus = StatusEnum.getById(resultSet.getInt("good_status"));
                BigDecimal price = resultSet.getBigDecimal("record_price");
                Record record = new Record();
                record.setId(id);
                record.setGoodId(goodId);
                record.setVipId(vipId);
                record.setCreateTime(createTime);
                record.setGood(new Good());
                record.getGood().setId(goodId);
                record.getGood().setName(goodName);
                record.getGood().setPrice(price);
                record.getGood().setStatus(goodStatus);
                record.setVip(new Vip());
                record.getVip().setId(vipId);
                record.getVip().setStatus(vipStatus);
                record.getVip().setPhone(phone);
                record.getVip().setName(vipName);
                record.setPrice(price);
                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                PreparedStatement statement = connection.prepareStatement("INSERT INTO record(userid, goodid, createtime,price)" +
                        " VALUES (?,?,?,?)")
        ) {
            statement.setString(1, record.getVipId());
            statement.setString(2, record.getGoodId());
            statement.setDate(3, new java.sql.Date(record.getCreateTime().getTime()));
            statement.setBigDecimal(4, record.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
