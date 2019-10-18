package qianlei.service;

import qianlei.dao.VipDao;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class VipService {
    private VipDao vipDao = new VipDao();

    public void addVip(String id, String name, String sex, String phone, String address, String postcode) throws WrongDataException {
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        if (StringUtil.isBigInteger(postcode) && postcode.length() != 6) {
            throw new WrongDataException("邮编" + postcode + "只能包含数字且只有6位");
        }
        if (vipDao.selectVipById(id) != null) {
            throw new WrongDataException("id" + id + "已经被注册");
        }
        Vip vip = new Vip(id, name, sex, phone, address, Integer.parseInt(postcode), new Date(), StatusEnum.Normal);
        vipDao.addVip(vip);
    }

    public List<Vip> getAllNormalVipByIdAndNameAndPhone(String id, String name, String phone) {
        List<Vip> vipList = vipDao.selectAll();
        List<Vip> ans = new LinkedList<>();
        for (Vip vip : vipList) {
            if (vip.getStatus().getId() == StatusEnum.Normal.getId() && vip.getId().contains(id)
                    && vip.getName().contains(name) && vip.getPhone().contains(phone)) {
                ans.add(vip);
            }
        }
        return ans;
    }
}
