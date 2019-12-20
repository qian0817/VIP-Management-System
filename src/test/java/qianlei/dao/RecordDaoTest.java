package qianlei.dao;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Record;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordDaoTest {
    private RecordDao recordDao = new RecordDao();
    @BeforeAll
    static void start() {
        TestHelper.deleteTestDb();
        DaoUtil.init("test");
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addRecord() {
        assertDoesNotThrow(() -> recordDao.addRecord(new Record(1, "test", "test", "test", "1", new Date(), BigDecimal.valueOf(1L))));
    }

    @Order(2)
    @Test
    void selectAllRecord() {
        assertEquals(recordDao.selectAllRecordByIdAndNameAndPhone("test", "", "", 1).size(), 1);
    }

    @Order(3)
    @Test
    void selectAllRecordByRecordNo() {
        assertEquals(recordDao.selectAllRecordByRecordNo("test", 1).size(), 1);
    }
}