package qianlei.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qianlei.TestHelper;
import qianlei.entity.RecordDetail;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordDetailDaoTest {
    private RecordDetailDao recordDetailDao = new RecordDetailDao();

    @BeforeAll
    static void start() {
        DaoUtil.init("test");
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Test
    void addRecordDetail() {
        assertDoesNotThrow(() -> recordDetailDao.addRecordDetail(new RecordDetail("1", "1", "1", BigDecimal.ONE, 1)));
        assertDoesNotThrow(() -> recordDetailDao.addRecordDetail(new RecordDetail("2", "1", "1", BigDecimal.ONE, 1)));
    }

    @Test
    void getAllRecordDetailByRecordId() {
        assertEquals(recordDetailDao.getAllRecordDetailByRecordId("1").size(), 1);
    }
}