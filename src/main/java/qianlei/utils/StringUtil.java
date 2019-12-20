package qianlei.utils;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author qianlei
 */
public final class StringUtil {
    private static final Pattern BIG_DECIMAL_PATTERN = Pattern.compile("^\\d+\\.?\\d*$");
    private static final Pattern CONTAINS_BLANK_PATTERN = Pattern.compile("\\S+");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^\\w{6,16}$");
    private static final Pattern BIG_INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+@\\w+\\.\\w+");

    private StringUtil() {
    }

    /**
     * 判断字符串是否是数字
     *
     * @param s 需要判断的字符串
     * @return 是否是数字
     */
    public static boolean isBigDecimal(String s) {
        if (s == null) {
            return true;
        }
        return BIG_DECIMAL_PATTERN.matcher(s).matches();
    }

    /**
     * 判断字符串是否有空格
     *
     * @param s 需要判断的字符串
     * @return 是否有空格
     */
    public static boolean containsBlank(String s) {
        if (s == null) {
            return false;
        }
        return !CONTAINS_BLANK_PATTERN.matcher(s).matches();
    }

    /**
     * 判断字符串是否符合密码格式
     *
     * @param password 需要判断的字符串
     * @return 是否符合密码格式
     */
    public static boolean isPassword(String password) {
        if (password == null) {
            return true;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * 判断字符串是否为正整数
     *
     * @param s 需要判断的字符串
     * @return 是否为正整数
     */
    public static boolean isBigInteger(String s) {
        if (s == null) {
            return false;
        }
        return BIG_INTEGER_PATTERN.matcher(s).matches();
    }

    /**
     * 判断字符串是否为邮箱格式
     *
     * @param s 需要判断的字符串
     * @return 是否符合邮箱格式
     */
    public static boolean isEmailAddress(String s) {
        if (s == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(s).matches();
    }
}
