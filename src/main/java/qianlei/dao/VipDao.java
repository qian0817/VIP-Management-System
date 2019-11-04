package qianlei.dao;

import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;
import qianlei.utils.Log;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * vip的dao层
 *
 * @author qianlei
 */
public class VipDao {

    /**
     * 添加VIP用户
     *
     * @param vip 需要添加的VIP
     */
    public void addVip(Vip vip) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO vip(id, name, sex, phone, " +
                        "address, postcode, createTime, status) VALUES (?,?,?,?,?,?,?,?)")
        ) {
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
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 根据id选择VIP
     *
     * @param id id
     * @return 该id的VIP
     */
    public Vip selectVipById(String id) {
        ResultSet resultSet = null;
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,name, sex, phone, address, postcode, " +
                        "createtime, status FROM vip WHERE id = ? LIMIT 500")
        ) {
            statement.setString(1, id);
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
     * @param searchId 获取到的id
     * @param searchName 获取到的name
     * @param searchPhone 获取到的phone
     * @return 符合条件的vip
     */
    public List<Vip> selectAllNormalVipByIdAndNameAndPhone(String searchId, String searchName, String searchPhone) {
        ResultSet resultSet = null;
        List<Vip> vipList = new LinkedList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,name, sex, phone, address, " +
                        "postcode, createtime, status FROM vip " +
                        "WHERE id LIKE ? AND name LIKE ? AND phone LIKE ? AND status = ?" +
                        "ORDER BY length(id)" +
                        "LIMIT 500 ")
        ) {
            statement.setString(1, "%" + searchId + "%");
            statement.setString(2, "%" + searchName + "%");
            statement.setString(3, "%" + searchPhone + "%");
            statement.setInt(4, StatusEnum.NORMAL.getId());
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
    private Vip getVipByResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String name = resultSet.getString("name");
        String sex = resultSet.getString("sex");
        Date createTime = resultSet.getDate("createTime");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        int postcode = resultSet.getInt("postcode");
        StatusEnum status = StatusEnum.getById(resultSet.getInt("status"));
        return new Vip(id, name, sex, phone, address, postcode, createTime, status);
    }

    /**
     * 删除指定id的VIP
     *
     * @param id id
     */
    public void deleteById(String id) {
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE vip SET status = ? WHERE id = ?")
        ) {
            statement.setInt(1, StatusEnum.DELETED.getId());
            statement.setString(2, id);
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
                PreparedStatement statement = connection.prepareStatement("UPDATE vip SET name = ?,sex=?,phone=?,address=?,postcode=?WHERE id = ?")
        ) {
            statement.setString(1, vip.getName());
            statement.setString(2, vip.getSex());
            statement.setString(3, vip.getPhone());
            statement.setString(4, vip.getAddress());
            statement.setInt(5, vip.getPostcode());
            statement.setString(6, vip.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            Log.error(Thread.currentThread(), e);
        }
    }
}
