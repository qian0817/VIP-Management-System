package qianlei.service;

import org.jetbrains.annotations.NotNull;
import qianlei.dao.VipDao;
import qianlei.entity.Result;
import qianlei.entity.Vip;

import java.util.Date;
import java.util.List;

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
    public Result addVip(@NotNull Vip vip) {
        Vip existVip = vipDao.selectVipById(vip.getVipNo(), UserService.getCurUser().getId());
        if (existVip != null) {
            return new Result(false, "卡号为" + vip.getVipNo() + "的会员已经录入");
        }
        vip.setUserId(UserService.getCurUser().getId());
        vip.setCreateTime(new Date());
        vipDao.addVip(vip);
        return new Result(true, "添加会员成功");
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
        return vipDao.selectAllNormalVipByIdAndNameAndPhone(id, name, phone, UserService.getCurUser().getId());
    }

    /**
     * 根据id选择vip
     *
     * @param id id
     * @return 该id的vip
     */
    public Vip getVipById(String id) {
        return vipDao.selectVipById(id, UserService.getCurUser().getId());
    }

    /**
     * 删除VIP
     *
     * @param vip vip
     */
    public void deleteVipById(@NotNull Vip vip) {
        vip.setUserId(UserService.getCurUser().getId());
        vipDao.deleteById(vip);
    }

    /**
     * 修改VIP
     *
     * @param vip 修改后的vip
     */
    public Result updateVip(@NotNull Vip vip) {
        vip.setUserId(UserService.getCurUser().getId());
        vipDao.updateVip(vip);
        return new Result(true, "修改成功");
    }
}
