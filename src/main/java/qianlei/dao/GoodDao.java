package qianlei.dao;

import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

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

    /**
     * 添加商品
     *
     * @param good 需要添加的商品
     */
    public void addGood(Good good) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO good(id, name, maker, createTime, " +
                        "price, discount, remain, introduction, remarks,status) VALUES (?,?,?,?,?,?,?,?,?,?)")
        ) {
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
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据id选择商品
     *
     * @param id id
     * @return 该id的商品
     */
    public Good selectGoodById(String id) {
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,name, maker, createtime, " +
                        "price, discount, remain, introduction, remarks,status FROM good WHERE id = ?")
        ) {
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getGoodByResult(resultSet);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return null;
    }

    /**
     * 删除指定id的商品
     *
     * @param id 指定id
     */
    public void deleteById(String id) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE good SET status = ? WHERE id = ?")
        ) {
            statement.setInt(1, StatusEnum.DELETED.getId());
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 修改商品
     *
     * @param good 修改后的商品
     */
    public void updateGood(Good good) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE good SET name = ?,maker = ?,price=?,discount=? ," +
                        "remain=?, introduction=?, remarks=?, status=?WHERE id = ?")
        ) {
            statement.setString(1, good.getName());
            statement.setString(2, good.getMaker());
            statement.setBigDecimal(3, good.getPrice());
            statement.setDouble(4, good.getDiscount());
            statement.setLong(5, good.getRemain());
            statement.setString(6, good.getIntroduction());
            statement.setString(7, good.getRemarks());
            statement.setInt(8, good.getStatus().getId());
            statement.setString(9, good.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据id和name获取数据
     *
     * @param searchId   需要包含的id
     * @param searchName 需要包含的name
     * @return 获取的商品的集合
     */
    public List<Good> selectAllNormalGoodByIdAndName(String searchId, String searchName) {
        List<Good> goodList = new LinkedList<>();
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,name, maker, createtime, " +
                        "price, discount, remain, introduction, remarks,status FROM good " +
                        "WHERE id LIKE ? AND name LIKE ? AND status = ?" +
                        "ORDER BY length(id)" +
                        "LIMIT 500 ")
        ) {
            statement.setString(1, "%" + searchId + "%");
            statement.setString(2, "%" + searchName + "%");
            statement.setInt(3, StatusEnum.NORMAL.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Good good = getGoodByResult(resultSet);
                goodList.add(good);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return goodList;
    }

    private Good getGoodByResult(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String maker = resultSet.getString("maker");
        Date createTime = resultSet.getDate("createTime");
        BigDecimal price = resultSet.getBigDecimal("price");
        double discount = resultSet.getDouble("discount");
        long remain = resultSet.getLong("remain");
        String introduction = resultSet.getString("introduction");
        String remarks = resultSet.getString("remarks");
        StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
        return new Good(id, name, maker, createTime, price, discount, remain, introduction, remarks, status);
    }
}
