package qianlei.utils;

public class StringUtil {
    public static boolean isBigDecimal(String s) {
        if (s == null) {
            return false;
        }
        return s.matches("^\\d+\\.?\\d*$");
    }

    public static boolean containsBlank(String s) {
        if (s == null) {
            return true;
        }
        return !s.matches("\\S+");
    }

    public static boolean isNotPassword(String password) {
        if (password == null) {
            return true;
        }
        return !password.matches("^\\w{6,16}$");
    }

    public static boolean isBigInteger(String s) {
        if (s == null) {
            return false;
        }
        return s.matches("^\\d+$");
    }
}
