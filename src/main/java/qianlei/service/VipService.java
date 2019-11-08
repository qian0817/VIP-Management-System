package qianlei.service;

import qianlei.dao.VipDao;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;

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
        if (vipDao.selectVipById(vip.getId()) != null) {
            throw new WrongDataException("id" + vip.getId() + "已经被注册");
        }
        vip.setStatus(StatusEnum.NORMAL);
        vip.setCreateTime(new Date());
        vipDao.addVip(vip);
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
        vipDao.updateVip(vip);
    }
}
