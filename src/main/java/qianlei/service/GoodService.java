package qianlei.service;

import qianlei.dao.GoodDao;
import qianlei.entity.Good;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;

import javax.swing.*;
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
        boolean isAdd = true;
        Good existGood = goodDao.selectGoodById(good.getId());
        if (existGood != null) {
            if (existGood.getStatus() == StatusEnum.NORMAL) {
                throw new WrongDataException("id 为" + good.getId() + "的商品已经录入");
            } else {
                int choose = JOptionPane.showConfirmDialog(null, "id 为" + good.getId() +
                        "的商品已经删除,是否覆盖该被删除数据(注:可能会导致商品消费记录查询到错误的结果)");
                if (choose != JOptionPane.YES_OPTION) {
                    throw new WrongDataException("未添加数据");
                } else {
                    isAdd = false;
                }
            }
        }
        good.setStatus(StatusEnum.NORMAL);
        if (isAdd) {
            goodDao.addGood(good);
        } else {
            goodDao.updateGood(good);
        }
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
