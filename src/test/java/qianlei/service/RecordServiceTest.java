package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.utils.DaoUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordServiceTest {
    static {
        TestHelper.deleteTestDb();
        DaoUtil.init("test.db");
    }

    private RecordService recordService = new RecordService();

    @Order(2)
    @Test
    void getAllRecordByIdAndName() {

    }

    @Order(1)
    @Test
    void addRecord() {
        Assertions.assertDoesNotThrow(() -> recordService.addRecord("test1", "test1"));
        Assertions.assertDoesNotThrow(() -> recordService.addRecord("test2", "2"));
        Assertions.assertDoesNotThrow(() -> recordService.addRecord("1", "1"));
    }
}