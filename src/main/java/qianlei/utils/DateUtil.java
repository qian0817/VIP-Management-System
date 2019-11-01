package qianlei.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author qianlei
 */
public final class DateUtil {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date transferToDate(String s) throws ParseException {
        return DATE_FORMAT.parse(s);
    }

    public static String transferToString(Date date) {
        return DATE_FORMAT.format(date);
    }
}
