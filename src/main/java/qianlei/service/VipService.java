package qianlei.service;

import qianlei.dao.VipDao;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * vip的service层
 *
 * @author qianlei
 */
public class VipService {
    private VipDao vipDao = new VipDao();
    /**
     * 添加VIP
     *
     * @param vip 需要添加的vip
     */
    public void addVip(Vip vip) throws WrongDataException {
        boolean isAdd = true;
        Vip existVip = vipDao.selectVipById(vip.getId());
        if (existVip != null) {
            if (existVip.getStatus() == StatusEnum.NORMAL) {
                throw new WrongDataException("id 为" + vip.getId() + "的 VIP 已经录入");
            } else {
                int choose = JOptionPane.showConfirmDialog(null, "id 为" + vip.getId() +
                        "的 VIP 已经删除,是否覆盖该被删除数据(注:可能会导致商品消费记录查询到错误的结果)");
                if (choose != JOptionPane.YES_OPTION) {
                    throw new WrongDataException("未添加数据");
                } else {
                    isAdd = false;
                }
            }
        }
        vip.setStatus(StatusEnum.NORMAL);
        vip.setCreateTime(new Date());
        if (isAdd) {
            vipDao.addVip(vip);
        } else {
            vipDao.updateVip(vip);
        }
    }

    /**
     * 根据id name和phone迷糊查询正常状态的vip
     *
     * @param id    id
     * @param name  名称
     * @param phone 电话号码
     * @return 符合条件的vip
     */
    public List<Vip> getAllNormalVipByIdAndNameAndPhone(String id, String name, String phone) {
        List<Vip> vipList = vipDao.selectAllNormalVipByIdAndNameAndPhone(id, name, phone);
        return vipList.stream().filter((vip -> vip.getStatus().getId() == StatusEnum.NORMAL.getId() && vip.getId().contains(id)
                && vip.getName().contains(name) && vip.getPhone().contains(phone))).collect(Collectors.toList());
    }

    /**
     * 根据id选择vip
     *
     * @param id id
     * @return 该id的vip
     */
    public Vip getVipById(String id) {
        return vipDao.selectVipById(id);
    }

    /**
     * 根据id删除VIP
     *
     * @param id id
     */
    public void deleteVipById(String id) {
        vipDao.deleteById(id);
    }

    /**
     * 修改VIP
     *
     * @param vip 修改后的vip
     */
    public void updateVip(Vip vip) {
        vip.setStatus(StatusEnum.NORMAL);
        vipDao.updateVip(vip);
    }
}
