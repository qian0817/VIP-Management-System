package qianlei.service;

import qianlei.dao.GoodDao;
import qianlei.dao.RecordDao;
import qianlei.entity.Good;
import qianlei.entity.Record;
import qianlei.exception.WrongDataException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 记录的service层
 *
 * @author qianlei
 */
public class RecordService {
    private RecordDao recordDao = new RecordDao();
    private GoodDao goodDao = new GoodDao();

    /**
     * 根据用户id姓名和电话号码模糊查询记录
     *
     * @param id    id
     * @param name  姓名
     * @param phone 电话号码
     * @return 符合条件的用户
     */
    public List<Record> getAllRecordByIdAndName(String id, String name, String phone) throws WrongDataException {
        return recordDao.selectAllRecordByIdAndNameAndPhone(id, name, phone);
    }

    /**
     * 添加记录
     *
     * @param goodId 消费的商品
     * @param vipId  消费的vip
     * @throws WrongDataException 给出的数据错误
     */
    public void addRecord(String goodId, String vipId) throws WrongDataException {
        if (goodId == null) {
            throw new WrongDataException("请选择商品");
        }
        if (vipId == null) {
            throw new WrongDataException("请选择vip");
        }
        Record record = new Record();
        record.setVipId(vipId);
        record.setGoodId(goodId);
        record.setCreateTime(new Date());
        Good good = goodDao.selectGoodById(goodId);
        record.setPrice(new BigDecimal(String.format("%.2f", good.getDiscount() * good.getPrice().doubleValue())));
        recordDao.addRecord(record);
    }
}
