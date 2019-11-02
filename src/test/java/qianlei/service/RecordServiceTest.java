package qianlei.service;

import org.junit.jupiter.api.*;
import qianlei.TestHelper;
import qianlei.entity.Good;
import qianlei.entity.Vip;
import qianlei.enums.StatusEnum;
import qianlei.exception.WrongDataException;
import qianlei.utils.DaoUtil;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordServiceTest {
    private RecordService recordService = new RecordService();

    @BeforeAll
    static void start() throws WrongDataException {
        DaoUtil.init("test.db");
        GoodService goodService = new GoodService();
        VipService vipService = new VipService();
        goodService.addGood(new Good("1", "1", "1", new Date(), new BigDecimal("123"), 123.45, 123, "", "", StatusEnum.NORMAL));
        vipService.addVip(new Vip("1", "1", "男", "1234567890", "123", 123456));
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addRecord() {
        assertDoesNotThrow(() -> recordService.addRecord("1", "1"));
        assertThrows(WrongDataException.class, () -> recordService.addRecord(null, "2"));
        assertThrows(WrongDataException.class, () -> recordService.addRecord("1", null));
    }

    @Order(2)
    @Test
    void getAllRecordByIdAndName() throws WrongDataException {
        assertEquals(recordService.getAllRecordByIdAndName("1", "1", "890").size(), 1);
    }

}