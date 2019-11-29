package qianlei.service;

import qianlei.dao.GoodDao;
import qianlei.dao.RecordDao;
import qianlei.dao.RecordDetailDao;
import qianlei.entity.Good;
import qianlei.entity.Record;
import qianlei.entity.RecordDetail;
import qianlei.entity.Result;
import qianlei.utils.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 记录的service层
 *
 * @author qianlei
 */
public class RecordService {
    private RecordDao recordDao = new RecordDao();
    private GoodDao goodDao = new GoodDao();
    private RecordDetailDao recordDetailDao = new RecordDetailDao();
    /**
     * 根据用户id姓名和电话号码模糊查询记录
     *
     * @param id    id
     * @param name  姓名
     * @param phone 电话号码
     * @return 符合条件的用户
     */
    public List<Record> getAllRecordByIdAndName(String id, String name, String phone) {
        return recordDao.selectAllRecordByIdAndNameAndPhone(id, name, phone);
    }

    public List<RecordDetail> getAllRecordDetailByRecordId(String recordId) {
        return recordDetailDao.getAllRecordDetailByRecordId(recordId);
    }
    /**
     * 添加记录
     */
    public Result addRecord(Map<Good, Integer> shopListMap, String vipId, String recordId) {
        if (shopListMap.size() == 0) {
            return new Result(false, "未选择商品");
        }
        if (recordId.length() == 0) {
            return new Result(false, "订单号不能为空");
        }
        if (StringUtil.containsBlank(recordId)) {
            return new Result(false, "订单号不能有空格");
        }
        if (recordDetailDao.getAllRecordDetailByRecordId(recordId).size() != 0) {
            return new Result(false, "该订单号已被使用");
        }
        //判断是否有库存
        BigDecimal price = BigDecimal.ZERO;
        List<Good> goodList = new LinkedList<>();
        for (Map.Entry<Good, Integer> entry : shopListMap.entrySet()) {
            Good curGood = goodDao.selectGoodById(entry.getKey().getId());
            if (curGood.getRemain() < entry.getValue()) {
                return new Result(false, "商品" + curGood.getName() + " 无剩余库存");
            }
            curGood.setRemain(curGood.getRemain() - entry.getValue());
            goodList.add(curGood);
            price = price.add(curGood.getPrice().multiply(curGood.getDiscount()).multiply(BigDecimal.valueOf(entry.getValue())));
            //保留两位小数
            price = price.setScale(2, RoundingMode.HALF_UP);
        }
        Record record = new Record();
        record.setId(recordId);
        record.setVipId(vipId);
        record.setCreateTime(new Date());
        record.setPrice(price);
        recordDao.addRecord(record);
        //修改库存
        for (Good good : goodList) {
            goodDao.updateGood(good);
        }
        for (Map.Entry<Good, Integer> entry : shopListMap.entrySet()) {
            RecordDetail recordDetail = new RecordDetail();
            recordDetail.setRecordId(recordId);
            recordDetail.setGoodId(entry.getKey().getId());
            recordDetail.setGoodName(entry.getKey().getName());
            recordDetail.setPrice(entry.getKey().getRealPrice());
            recordDetail.setNumber(entry.getValue());
            recordDetailDao.addRecordDetail(recordDetail);
        }
        return new Result(true, "添加成功");
    }
}
