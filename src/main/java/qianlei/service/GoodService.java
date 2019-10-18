package qianlei.service;

import qianlei.dao.GoodDao;
import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * s
 *
 * @author qianlei
 */
public class GoodService {
    private GoodDao goodDao = new GoodDao();

    public void addGood(String id, String name, String maker, String price, Double discount, String remain, String introduction, String remark) throws WrongDataException {
        if (!StringUtil.isBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        if (goodDao.selectGoodById(id) != null) {
            throw new WrongDataException("id" + id + "已被注册");
        }
        Good good = new Good(id, name, maker, new Date(), new BigDecimal(price), discount, Long.parseLong(remain), introduction, remark, StatusEnum.Normal);
        goodDao.addGood(good);
    }

    public List<Good> getAllNormalGoodByIdAndName(String id, String name) {
        List<Good> goodList = goodDao.selectAll();
        List<Good> ans = new LinkedList<>();
        for (Good good : goodList) {
            if (good.getStatus().getId() == StatusEnum.Normal.getId() && good.getId().contains(id) && good.getName().contains(name)) {
                ans.add(good);
            }
        }
        return ans;
    }
}
