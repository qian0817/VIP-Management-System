package qianlei.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    @Test
    void transferToDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);
        Date date = calendar.getTime();
        assertEquals(DateUtil.transferToString(date), "2019-12-31");
    }

    @Test
    void transferToString() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        assertEquals(DateUtil.transferToDate("2019-12-31"), date);
    }
}