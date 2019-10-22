package qianlei.utils;

/**
 * 字符串工具类
 *
 * @author qianlei
 */
public class StringUtil {
    /**
     * 判断字符串是否是数字
     *
     * @param s 需要判断的字符串
     * @return 是否是数字
     */
    public static boolean isNotBigDecimal(String s) {
        if (s == null) {
            return true;
        }
        return !s.matches("^\\d+\\.?\\d*$");
    }

    /**
     * 判断字符串是否有空格或为空
     *
     * @param s 需要判断的字符串
     * @return 是否有空格或为空
     */
    public static boolean containsBlank(String s) {
        if (s == null) {
            return true;
        }
        return !s.matches("\\S+");
    }

    /**
     * 判断字符串是否符合密码格式
     *
     * @param password 需要判断的字符串
     * @return 是否符合密码格式
     */
    public static boolean isNotPassword(String password) {
        if (password == null) {
            return true;
        }
        return !password.matches("^\\w{6,16}$");
    }

    /**
     * 判断字符串是否为正整数
     * @param s 需要判断的字符串
     * @return 是否为正整数
     */
    public static boolean isBigInteger(String s) {
        if (s == null) {
            return false;
        }
        return s.matches("^\\d+$");
    }
}
