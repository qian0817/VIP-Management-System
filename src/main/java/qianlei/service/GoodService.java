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
 * 商品的service层
 *
 * @author qianlei
 */
public class GoodService {
    private GoodDao goodDao = new GoodDao();

    /**
     * 添加商品
     *
     * @param id           商品id
     * @param name         商品名称
     * @param maker        商品制造商
     * @param price        商品价格
     * @param discount     商品折扣
     * @param remain       商品库存
     * @param introduction 商品介绍
     * @param remark       商品备注
     * @throws WrongDataException 输入的数据格式错误
     */
    public void addGood(String id, String name, String maker, Date createTime, String price, Double discount, String remain, String introduction, String remark) throws WrongDataException {
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        if (StringUtil.isNotBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        if (goodDao.selectGoodById(id) != null) {
            throw new WrongDataException("id" + id + "已被注册");
        }
        Good good = new Good(id, name, maker, createTime, new BigDecimal(price), discount, Long.parseLong(remain), introduction, remark, StatusEnum.Normal);
        goodDao.addGood(good);
    }

    /**
     * 根据id和name模糊查询商品
     *
     * @param id   商品id
     * @param name 商品名称
     * @return 符合条件的商品
     */
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

    /**
     * 根据id选择商品
     *
     * @param id id
     * @return 该id的商品
     */
    public Good getGoodById(String id) {
        return goodDao.selectGoodById(id);
    }

    /**
     * 删除指定id的商品
     *
     * @param id id
     */
    public void deleteGoodById(String id) {
        goodDao.deleteById(id);
    }

    /**
     * 修改商品信息
     *
     * @param id           商品id
     * @param name         商品名称
     * @param maker        商品制造商
     * @param price        商品价格
     * @param discount     商品折扣
     * @param remain       商品库存
     * @param introduction 商品介绍
     * @param remark       商品备注
     * @throws WrongDataException 输入的数据格式错误
     */
    public void updateGood(String id, String name, String maker, String price, Double discount, String remain, String introduction, String remark) throws WrongDataException {
        if (StringUtil.isNotBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        Good good = new Good(id, name, maker, new Date(), new BigDecimal(price), discount, Long.parseLong(remain), introduction, remark, StatusEnum.Normal);
        goodDao.updateGood(good);
    }
}
