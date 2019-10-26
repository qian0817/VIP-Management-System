package qianlei.service;

import qianlei.dao.VipDao;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;

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
    private static final int POSTCODE_LENGTH = 6;
    /**
     * 添加VIP
     *
     * @param id       vip的id
     * @param name     vip名称
     * @param sex      vip性别
     * @param phone    vip电话号码
     * @param address  vip地址
     * @param postcode vip邮编
     * @throws WrongDataException 给入的格式错误
     */
    public void addVip(String id, String name, String sex, String phone, String address, String postcode) throws WrongDataException {
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        if (!StringUtil.isBigInteger(phone)) {
            throw new WrongDataException("电话号码" + phone + "只能包含数字");
        }
        if (!StringUtil.isBigInteger(postcode) || postcode.length() != POSTCODE_LENGTH) {
            throw new WrongDataException("邮编" + postcode + "只能包含数字且只有6位");
        }
        if (vipDao.selectVipById(id) != null) {
            throw new WrongDataException("id" + id + "已经被注册");
        }
        Vip vip = new Vip(id, name, sex, phone, address, Integer.parseInt(postcode), new Date(), StatusEnum.Normal);
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
        List<Vip> vipList = vipDao.selectAll();
        return vipList.stream().filter((vip -> vip.getStatus().getId() == StatusEnum.Normal.getId() && vip.getId().contains(id)
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
     * @param id       要修改的vip的id
     * @param name     修改后的名称
     * @param sex      修改后的性别
     * @param phone    修改后的电话号码
     * @param address  修改后的地址
     * @param postcode 修改后的邮编
     * @throws WrongDataException 输入的数据错误
     */
    public void updateVip(String id, String name, String sex, String phone, String address, String postcode) throws WrongDataException {
        if (StringUtil.isBigInteger(postcode) && postcode.length() != POSTCODE_LENGTH) {
            throw new WrongDataException("邮编" + postcode + "只能包含数字且只有6位");
        }
        Vip vip = new Vip(id, name, sex, phone, address, Integer.parseInt(postcode));
        vipDao.updateVip(vip);
    }
}
