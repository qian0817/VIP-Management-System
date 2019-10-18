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

public class RecordDao {
    public List<Record> selectAllRecord() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Record> recordList = new LinkedList<>();
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT r.id AS record_id, r.userId AS vip_id, r.goodId AS good_id, " +
                    "r.createTime AS record_create_time, v.phone AS vip_phone ,v.name AS vip_name," +
                    "v.status AS vip_status,g.name AS  good_name,g.price AS good_price,g.status AS good_status " +
                    "FROM record r " +
                    "LEFT JOIN good g on r.goodId = g.id " +
                    "LEFT JOIN vip v on r.userId = v.id");
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
                BigDecimal price = resultSet.getBigDecimal("good_price");
                StatusEnum goodStatus = StatusEnum.getById(resultSet.getInt("good_status"));
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
                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement, resultSet);
        }
        return recordList;
    }

    public void addRecord(Record record) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO record(userid, goodid, createtime)" +
                    " VALUES (?,?,?)");
            statement.setString(1, record.getVipId());
            statement.setString(2, record.getGoodId());
            statement.setDate(3, new java.sql.Date(record.getCreateTime().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement);
        }
    }
}
