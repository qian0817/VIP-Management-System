package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Record;
import qianlei.utils.DaoUtil;

import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordDaoTest {
    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private RecordDao recordDao = new RecordDao();

    @Order(2)
    @Test
    void selectAllRecord() {
        Assertions.assertEquals(recordDao.selectAllRecord().size(), 1);
    }

    @Order(1)
    @Test
    void addRecord() {
        recordDao.addRecord(new Record(1, "1", "1", new Date()));
    }
}