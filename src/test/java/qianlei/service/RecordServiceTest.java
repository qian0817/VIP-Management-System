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
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordServiceTest {
    private RecordService recordService = new RecordService();
    private static Good good = new Good("1", "1", "1", new Date(), new BigDecimal("123"), new BigDecimal("0.1"), 1, "", "", StatusEnum.NORMAL);
    @BeforeAll
    static void start() throws WrongDataException {
        TestHelper.deleteTestDb();
        DaoUtil.init("test");
        GoodService goodService = new GoodService();
        VipService vipService = new VipService();
        goodService.addGood(good);
        vipService.addVip(new Vip("1", "1", "男", "1234567890", "123", "123456"));
    }

    @AfterAll
    static void end() {
        TestHelper.deleteTestDb();
    }

    @Order(1)
    @Test
    void addRecord() {
        Map<Good, Integer> shopListMap = new HashMap<>();
        shopListMap.put(good, 2);
        assertFalse(recordService.addRecord(shopListMap, "1", "1").isSuccess());
        shopListMap.put(good, 1);
        assertTrue(recordService.addRecord(shopListMap, "1", "1").isSuccess());
        assertFalse(recordService.addRecord(shopListMap, "1", "1").isSuccess());
    }

    @Order(2)
    @Test
    void getAllRecordByIdAndName() {
        assertEquals(recordService.getAllRecordByIdAndName("1", "1", "890").size(), 1);
    }

}