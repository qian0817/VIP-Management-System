package qianlei.service;

import org.jetbrains.annotations.NotNull;
import qianlei.dao.GoodDao;
import qianlei.entity.Good;
import qianlei.entity.Result;

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
     * @return 添加商品的结果
     */
    public Result addGood(@NotNull Good good) {
        Good existGood = goodDao.selectGoodByNo(good.getGoodNo(), UserService.getCurUser().getId());
        if (existGood != null) {
            return new Result(false, "编号为" + good.getGoodNo() + "的商品已经录入");
        }
        good.setUserId(UserService.getCurUser().getId());
        goodDao.addGood(good);
        return new Result(true, "添加商品成功");
    }

    /**
     * 根据id和name模糊查询商品
     *
     * @param id   商品id
     * @param name 商品名称
     * @return 符合条件的商品
     */
    public List<Good> selectAllNormalGoodByNoAndName(String id, String name) {
        return goodDao.selectAllNormalGoodByNoAndName(id, name, UserService.getCurUser().getId());
    }

    /**
     * 根据商品编号选择商品
     *
     * @param goodNo 商品编号
     * @return 该id的商品
     */
    public Good getGoodByGoodNo(String goodNo) {
        return goodDao.selectGoodByGoodNo(goodNo, UserService.getCurUser().getId());
    }

    /**
     * 删除指定的商品
     *
     * @param good 需要删除的商品
     */
    public Result deleteGood(@NotNull Good good) {
        good.setUserId(UserService.getCurUser().getId());
        goodDao.deleteById(good);
        return new Result(true, "删除商品成功");
    }

    /**
     * 修改商品信息
     *
     * @param good 需要修改的商品
     */
    public Result updateGood(@NotNull Good good) {
        good.setUserId(UserService.getCurUser().getId());
        goodDao.updateGood(good);
        return new Result(true, "修改商品信息成功");
    }
}
