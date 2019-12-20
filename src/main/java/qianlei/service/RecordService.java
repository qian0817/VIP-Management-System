package qianlei.service;

import org.jetbrains.annotations.NotNull;
import qianlei.dao.GoodDao;
import qianlei.dao.RecordDao;
import qianlei.dao.RecordDetailDao;
import qianlei.dao.VipDao;
import qianlei.entity.*;
import qianlei.utils.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 记录的service层
 *
 * @author qianlei
 */
public class RecordService {
    private RecordDao recordDao = new RecordDao();
    private GoodDao goodDao = new GoodDao();
    private RecordDetailDao recordDetailDao = new RecordDetailDao();
    private VipDao vipDao = new VipDao();

    /**
     * 根据会员卡号姓名和电话号码模糊查询记录
     *
     * @param id    卡号
     * @param name  姓名
     * @param phone 电话号码
     * @return 符合条件的用户
     */
    public List<Record> getAllRecordByIdAndName(String id, String name, String phone) {
        return recordDao.selectAllRecordByIdAndNameAndPhone(id, name, phone, UserService.getCurUser().getId());
    }

    /**
     * 根据订单号获取记录细节
     *
     * @param recordId 订单号
     * @return 记录细节
     */
    public List<RecordDetail> getAllRecordDetailByRecordId(String recordId) {
        return recordDetailDao.getAllRecordDetailByRecordId(recordId, UserService.getCurUser().getId());
    }

    /**
     * 添加商品记录
     *
     * @param shopListMap 记录购买的商品以及数量的映射 key为具体商品 value为购买数量
     * @param vipId       消费的会员卡号
     * @param recordId    记录号
     * @return 结果
     */
    public Result addRecord(@NotNull Map<Good, Integer> shopListMap, String vipId, String recordId) {
        if (shopListMap.size() == 0) {
            return new Result(false, "未选择商品");
        }
        if (recordId.length() == 0) {
            recordId = createRandomRecordId();
        }
        if (StringUtil.containsBlank(recordId)) {
            return new Result(false, "订单号不能有空格");
        }
        if (recordDetailDao.getAllRecordDetailByRecordId(recordId, UserService.getCurUser().getId()).size() != 0) {
            return new Result(false, "该订单号已被使用");
        }
        Vip vip = vipDao.selectVipById(vipId, UserService.getCurUser().getId());
        //判断是否有库存
        BigDecimal price = BigDecimal.ZERO;
        List<Good> goodList = new ArrayList<>();
        for (Map.Entry<Good, Integer> entry : shopListMap.entrySet()) {
            Good curGood = goodDao.selectGoodByGoodNo(entry.getKey().getGoodNo(), UserService.getCurUser().getId());
            if (curGood.getRemain() < entry.getValue()) {
                return new Result(false, "商品" + curGood.getName() + " 无剩余库存");
            }
            curGood.setRemain(curGood.getRemain() - entry.getValue());
            goodList.add(curGood);
            price = price.add(curGood.getPrice().multiply(curGood.getDiscount()).multiply(BigDecimal.valueOf(entry.getValue())));
            //保留两位小数
            price = price.setScale(2, RoundingMode.HALF_UP);
        }
        User user = UserService.getCurUser();
        Record record = new Record();
        record.setRecordNo(recordId);
        record.setVipId(vip.getVipNo());
        record.setVipName(vip.getName());
        record.setVipPhone(vip.getPhone());
        record.setCreateTime(new Date());
        record.setPrice(price);
        record.setUserId(user.getId());
        recordDao.addRecord(record);
        //修改库存
        for (Good good : goodList) {
            goodDao.updateGood(good);
        }
        for (Map.Entry<Good, Integer> entry : shopListMap.entrySet()) {
            RecordDetail recordDetail = new RecordDetail();
            recordDetail.setRecordNo(recordId);
            recordDetail.setGoodNo(entry.getKey().getGoodNo());
            recordDetail.setGoodName(entry.getKey().getName());
            recordDetail.setPrice(entry.getKey().getRealPrice());
            recordDetail.setNumber(entry.getValue());
            recordDetail.setUserId(user.getId());
            recordDetailDao.addRecordDetail(recordDetail);
        }
        return new Result(true, "订单号为" + recordId + "的订单添加成功");
    }

    /**
     * 生成随机的订单号
     *
     * @return 随机的订单号
     */
    public String createRandomRecordId() {
        Random random = new Random();
        List<Record> records;
        while (true) {
            String recordId = Long.toString(Math.abs(random.nextLong()));
            if (recordId.length() <= 5) {
                continue;
            }
            records = recordDao.selectAllRecordByRecordNo(recordId, UserService.getCurUser().getId());
            if (records.size() == 0) {
                return recordId;
            }
        }
    }
}
