package qianlei.dao;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.Vip;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员的数据库层
 *
 * @author qianlei
 */
public class VipDao {

    /**
     * 添加VIP用户
     *
     * @param vip 需要添加的VIP
     */
    public void addVip(@NotNull Vip vip) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO vip(userid, vipno, name, sex, phone," +
                        " address, email, createtime) VALUES (?,?,?,?,?,?,?,?)")
        ) {
            statement.setInt(1, vip.getUserId());
            statement.setString(2, vip.getVipNo());
            statement.setString(3, vip.getName());
            statement.setString(4, vip.getSex());
            statement.setString(5, vip.getPhone());
            statement.setString(6, vip.getAddress());
            statement.setString(7, vip.getEmail());
            statement.setDate(8, new Date(vip.getCreateTime().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据id选择VIP
     *
     * @param id id
     * @return 该id的VIP
     */
    public Vip selectVipById(String id, int userId) {
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM vip WHERE vipNo = ? AND userId = ? LIMIT 500")
        ) {
            statement.setString(1, id);
            statement.setInt(2, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getVipByResultSet(resultSet);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return null;
    }

    /**
     * 根据id name和phone查找vip
     *
     * @param searchId    获取到的id
     * @param searchName  获取到的name
     * @param searchPhone 获取到的phone
     * @return 符合条件的vip
     */
    public List<Vip> selectAllNormalVipByIdAndNameAndPhone(String searchId, String searchName, String searchPhone, int userId) {
        ResultSet resultSet = null;
        List<Vip> vipList = new ArrayList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM vip " +
                        "WHERE vipNo LIKE ? AND name LIKE ? AND phone LIKE ?  AND userId = ?" +
                        "ORDER BY length(vipNo)" +
                        "LIMIT 500 ")
        ) {
            statement.setString(1, "%" + searchId + "%");
            statement.setString(2, "%" + searchName + "%");
            statement.setString(3, "%" + searchPhone + "%");
            statement.setInt(4, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Vip vip = getVipByResultSet(resultSet);
                vipList.add(vip);
            }
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        } finally {
            DaoUtil.closeResultSet(resultSet);
        }
        return vipList;
    }

    /**
     * 根据result获取vip
     *
     * @param resultSet resultSet
     * @return vip
     * @throws SQLException sql错误
     */
    @NotNull
    private Vip getVipByResultSet(@NotNull ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("userId");
        String vipNo = resultSet.getString("vipNo");
        String name = resultSet.getString("name");
        String sex = resultSet.getString("sex");
        Date createTime = resultSet.getDate("createTime");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        String email = resultSet.getString("email");
        return new Vip(id, userId, vipNo, name, sex, phone, address, email, createTime);
    }

    /**
     * 删除指定的VIP
     *
     * @param vip 需要删除的会员
     */
    public void deleteById(@NotNull Vip vip) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM vip WHERE vipNo = ? AND userId = ?")
        ) {
            statement.setString(1, vip.getVipNo());
            statement.setInt(2, vip.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 修改VIP信息
     *
     * @param vip 修改后的vip
     */
    public void updateVip(Vip vip) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE vip SET name = ?," +
                        "sex=?,phone=?,address=?,email=? WHERE vipNo = ? AND userId = ?")
        ) {
            statement.setString(1, vip.getName());
            statement.setString(2, vip.getSex());
            statement.setString(3, vip.getPhone());
            statement.setString(4, vip.getAddress());
            statement.setString(5, vip.getEmail());
            statement.setString(6, vip.getVipNo());
            statement.setInt(7, vip.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }
}
