package qianlei.dao;

import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class VipDao {

    public void addVip(Vip vip) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO vip(id, name, sex, phone, address, postcode, createTime, status)" +
                    " VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, vip.getId());
            statement.setString(2, vip.getName());
            statement.setString(3, vip.getSex());
            statement.setString(4, vip.getPhone());
            statement.setString(5, vip.getAddress());
            statement.setInt(6, vip.getPostcode());
            statement.setDate(7, new Date(vip.getCreateTime().getTime()));
            statement.setInt(8, vip.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement);
        }
    }

    public Vip selectVipById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT name, sex, phone, address, postcode, createtime, status FROM vip WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Date createtime = resultSet.getDate("createtime");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int postcode = resultSet.getInt("postcode");
                StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
                return new Vip(id, name, sex, phone, address, postcode, createtime, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public List<Vip> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Vip> vipList = new LinkedList<>();
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT id,name, sex, phone, address, postcode, createtime, status FROM vip");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Date createtime = resultSet.getDate("createtime");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int postcode = resultSet.getInt("postcode");
                StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
                vipList.add(new Vip(id, name, sex, phone, address, postcode, createtime, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement, resultSet);
        }
        return vipList;
    }
}
