package qianlei.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilTest {

    @Test
    void isNotBigDecimal() {
        assertTrue(StringUtil.isBigDecimal("123"));
        assertTrue(StringUtil.isBigDecimal("123.45"));
        assertFalse(StringUtil.isBigDecimal("123。45"));
        assertFalse(StringUtil.isBigDecimal("123w"));
        assertFalse(StringUtil.isBigDecimal("12 3"));
    }

    @Test
    void containsBlankOrEmpty() {
        assertTrue(StringUtil.containsBlank(" "));
        assertTrue(StringUtil.containsBlank(""));
        assertFalse(StringUtil.containsBlank("abc"));
        assertTrue(StringUtil.containsBlank("a bc"));
    }

    @Test
    void isNotPassword() {
        assertTrue(StringUtil.isPassword("123456"));
        assertFalse(StringUtil.isPassword("12345"));
        assertTrue(StringUtil.isPassword("1234567"));
        assertTrue(StringUtil.isPassword("123456w"));
        assertTrue(StringUtil.isPassword("123456_"));
        assertFalse(StringUtil.isPassword("123456我"));
    }

    @Test
    void isBigInteger() {
        assertTrue(StringUtil.isBigInteger("123"));
        assertTrue(StringUtil.isBigInteger("12345678901322211"));
        assertFalse(StringUtil.isBigInteger(""));
        assertFalse(StringUtil.isBigInteger("1w23"));
        assertFalse(StringUtil.isBigInteger("12.3"));
    }
}