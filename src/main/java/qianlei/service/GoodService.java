package qianlei.service;

import qianlei.dao.GoodDao;
import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;

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
     * @param good 商品
     */
    public void addGood(Good good) throws WrongDataException {
        if (goodDao.selectGoodById(good.getId()) != null) {
            throw new WrongDataException("id" + good.getId() + "已被注册");
        }
        good.setStatus(StatusEnum.NORMAL);
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
        return goodDao.selectAllNormalGoodByIdAndName(id, name);
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
     * @param good 需要修改的商品
     */
    public void updateGood(Good good) {
        good.setStatus(StatusEnum.NORMAL);
        goodDao.updateGood(good);
    }
}
