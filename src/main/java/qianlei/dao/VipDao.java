package qianlei.dao;

import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.utils.DaoUtil;

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
            e.printStackTrace();
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
                PreparedStatement statement = connection.prepareStatement("SELECT name, sex, phone, address, postcode, " +
                        "createtime, status FROM vip WHERE id = ?")
        ) {
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
            DaoUtil.closeResultSet(resultSet);
        }
        return null;
    }

    /**
     * 获取所有VIP
     *
     * @return 所有VIP
     */
    public List<Vip> selectAll() {
        ResultSet resultSet = null;
        List<Vip> vipList = new LinkedList<>();
        try (
                Connection connection = DaoUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id,name, sex, phone, address, " +
                        "postcode, createtime, status FROM vip")
        ) {
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
            DaoUtil.closeResultSet(resultSet);
        }
        return vipList;
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
            statement.setInt(1, StatusEnum.Deleted.getId());
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
