package qianlei.dao;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.Good;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品的数据库层
 *
 * @author qianlei
 */
public class GoodDao {

    /**
     * 添加商品
     *
     * @param good 需要添加的商品
     */
    public void addGood(@NotNull Good good) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO good(userId,goodNo,name, maker, createTime, " +
                        "price, discount, remain, introduction, remarks) VALUES (?,?,?,?,?,?,?,?,?,?)")
        ) {
            statement.setInt(1, good.getUserId());
            statement.setString(2, good.getGoodNo());
            statement.setString(3, good.getName());
            statement.setString(4, good.getMaker());
            statement.setDate(5, new Date(good.getCreateTime().getTime()));
            statement.setBigDecimal(6, good.getPrice());
            statement.setBigDecimal(7, good.getDiscount());
            statement.setLong(8, good.getRemain());
            statement.setString(9, good.getIntroduction());
            statement.setString(10, good.getRemarks());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据id选择商品
     *
     * @param goodNo 商品编号
     * @param userId 用户编号
     * @return 该id的商品
     */
    public Good selectGoodByGoodNo(String goodNo, int userId) {
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT *" +
                        " FROM good WHERE goodNo = ? AND userId = ?")
        ) {
            statement.setString(1, goodNo);
            statement.setInt(2, userId);
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
     * @param good 指定商品
     */
    public void deleteById(@NotNull Good good) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM good " +
                        "WHERE goodNo = ? AND userId = ?")
        ) {
            statement.setString(1, good.getGoodNo());
            statement.setInt(2, good.getUserId());
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
    public void updateGood(@NotNull Good good) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE good SET name = ?,maker = ?,price=?,discount=? ," +
                        "remain=?, introduction=?, remarks=? WHERE goodNo = ? AND userId = ? ")
        ) {
            statement.setString(1, good.getName());
            statement.setString(2, good.getMaker());
            statement.setBigDecimal(3, good.getPrice());
            statement.setBigDecimal(4, good.getDiscount());
            statement.setLong(5, good.getRemain());
            statement.setString(6, good.getIntroduction());
            statement.setString(7, good.getRemarks());
            statement.setString(8, good.getGoodNo());
            statement.setInt(9, good.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据会员卡号和会员名称和用户id获取数据
     *
     * @param searchGoodNo   需要查找的会员卡号
     * @param searchGoodName 需要查找的会员名称
     * @param userId         用户id
     * @return 获取到的商品数据
     */
    public List<Good> selectAllNormalGoodByNoAndName(String searchGoodNo, String searchGoodName, int userId) {
        List<Good> goodList = new ArrayList<>();
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT* FROM good " +
                        "WHERE goodNo LIKE ? AND name LIKE ? AND userId = ?" +
                        "ORDER BY length(goodNo)" +
                        "LIMIT 500 ")
        ) {
            statement.setString(1, "%" + searchGoodNo + "%");
            statement.setString(2, "%" + searchGoodName + "%");
            statement.setInt(3, userId);
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

    @NotNull
    private Good getGoodByResult(@NotNull ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("userId");
        String goodNo = resultSet.getString("goodNo");
        String name = resultSet.getString("name");
        String maker = resultSet.getString("maker");
        Date createTime = resultSet.getDate("createTime");
        BigDecimal price = resultSet.getBigDecimal("price");
        BigDecimal discount = resultSet.getBigDecimal("discount");
        long remain = resultSet.getLong("remain");
        String introduction = resultSet.getString("introduction");
        String remarks = resultSet.getString("remarks");
        return new Good(id, userId, goodNo, name, maker, createTime, price, discount, remain, introduction, remarks);
    }

    /**
     * 根据商品编号和用户的id获取商品
     *
     * @param goodNo 商品
     * @param userId 用户id
     * @return 获取到的商品
     */
    public Good selectGoodByNo(String goodNo, int userId) {
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM good WHERE goodNo = ?AND userId = ?")
        ) {
            statement.setString(1, goodNo);
            statement.setInt(2, userId);
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
}
