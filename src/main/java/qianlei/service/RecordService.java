package qianlei.service;

import qianlei.dao.RecordDao;
import qianlei.entity.Record;
import qianlei.exception.WrongDataException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qianlei
 */
public class RecordService {
    private RecordDao recordDao = new RecordDao();

    public List<Record> getAllRecordByIdAndName(String id, String name, String phone) {
        List<Record> recordList = recordDao.selectAllRecord();
        List<Record> ans = new LinkedList<>();
        for (Record record : recordList) {
            if (record.getVip().getId().contains(id) && record.getVip().getName().contains(name) && record.getVip().getPhone().contains(phone)) {
                ans.add(record);
            }
        }
        return ans;
    }

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
        recordDao.addRecord(record);
    }
}
