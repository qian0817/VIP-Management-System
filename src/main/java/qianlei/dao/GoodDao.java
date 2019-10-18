package qianlei.dao;

import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * good的数据库层
 *
 * @author qianlei
 */
public class GoodDao {

    public void addGood(Good good) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO good(id, name, maker, createTime, price, discount, remain, introduction, remarks,status)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, good.getId());
            statement.setString(2, good.getName());
            statement.setString(3, good.getMaker());
            statement.setDate(4, new Date(good.getCreateTime().getTime()));
            statement.setBigDecimal(5, good.getPrice());
            statement.setDouble(6, good.getDiscount());
            statement.setLong(7, good.getRemain());
            statement.setString(8, good.getIntroduction());
            statement.setString(9, good.getRemarks());
            statement.setInt(10, good.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement);
        }
    }

    public Good selectGoodById(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT name, maker, createtime, price, discount, remain, introduction, remarks,status FROM good WHERE id = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String maker = resultSet.getString("maker");
                Date createtime = resultSet.getDate("createtime");
                BigDecimal price = resultSet.getBigDecimal("price");
                double discount = resultSet.getDouble("discount");
                Long remain = resultSet.getLong("remain");
                String introduction = resultSet.getString("introduction");
                String remarks = resultSet.getString("remarks");
                StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
                return new Good(id, name, maker, createtime, price, discount, remain, introduction, remarks, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public List<Good> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Good> goodList = new LinkedList<>();
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement("SELECT id,name, maker, createtime, price, discount, remain, introduction, remarks,status FROM good");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String maker = resultSet.getString("maker");
                Date createtime = resultSet.getDate("createtime");
                BigDecimal price = resultSet.getBigDecimal("price");
                double discount = resultSet.getDouble("discount");
                Long remain = resultSet.getLong("remain");
                String introduction = resultSet.getString("introduction");
                String remarks = resultSet.getString("remarks");
                StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
                goodList.add(new Good(id, name, maker, createtime, price, discount, remain, introduction, remarks, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.close(connection, statement, resultSet);
        }
        return goodList;
    }
}
