package com.zhou.gitproject.utils;

import android.text.TextUtils;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 对字符串按照常用规则进行验证的工具类
 * Created by zhou on 2016/1/18.
 */
public class Validators {
    //简体中文的正则表达式
    private static final String REGEX_SIMPLE_CHINESE = "^[\u4E00-\u9FA5]+$";
    //字母的正则表达式
    private static final String REGEX_ALPHABET = "[a-zA-Z]+";
    //字母数字的正则表达式
    private static final String REGEX_ALPHANUMERIC = "[a-zA-Z0-9]+";
    //数字汉字字母数字组合正则表达式
    private static final String REGEX_ALPNUMCHIERIC = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
    //整数或浮点数的正则表达式
    private static final String REGEX_NUMERIC = "(\\+|-){0,1}(\\d+)([.]?)(\\d*)";
    //身份证号码的正则表达式
    private static final String REGEX_ID_CARD = "(\\d{14}|\\d{17})(\\d|x|X)";
    //电子邮箱的正则表达式
    private static final String REGEX_EMAIL = ".+@.+\\.[a-z]+";
    //电话号码的正则表达式
    private static final String REGEX_PHONE_NUMBER = "(([\\(（]\\d+[\\)）])?|(\\d+[-－]?)*)\\d+";
    //移动手机号码的正则表达式
    private static final String REGEX_CHINA_MOBILE = "1(3[4-9]|4[7]|5[012789]|7[8]|8[23478])\\d{8}";
    //联通手机号码的正则表达式
    private static final String REGEX_CHINA_UNICOM = "1(3[0-2]|4[6]|5[56]|7[6]|8[56])\\d{8}";
    //电信手机号码的正则表达式
    private static final String REGEX_CHINA_TELECOM = "1(3[3]|5[3]|7[7]|8[019])\\d{8}";

    /**
     * 判断字符串是否只包含字母.
     *
     * @param str 字符串
     * @return 如果字符串只包含字母, 则返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public static boolean isAlphabet(String str) {
        return isRegexMatch(str, REGEX_ALPHABET);
    }

    /**
     * 判断字符串是否只包含字母和数字.
     *
     * @param str 字符串
     * @return 如果字符串只包含字母和数字, 则返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public static boolean isAlphanumeric(String str) {
        return isRegexMatch(str, REGEX_ALPHANUMERIC);
    }

    /**
     * 判断字符串是否只包含字母和数字，汉字.
     *
     * @param str 字符串
     * @return 如果字符串只包含字母和数字, 汉字, 则返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public static boolean isAlpNumChimeric(String str) {
        return isRegexMatch(str, REGEX_ALPNUMCHIERIC);
    }

    /**
     * 判断字符串是否是合法的电子邮箱地址.
     *
     * @param str 字符串
     * @return 是true，否则false
     */
    public static boolean isEmail(String str) {
        return isRegexMatch(str, REGEX_EMAIL);
    }

    /**
     * 当数组为<code>null</code>, 或者长度为0, 或者长度为1且元素的值为<code>null</code>时返回
     * <code>true</code>.
     *
     * @param args
     * @return true/false
     */
    public static boolean isEmpty(Object[] args) {
        return args == null || args.length == 0
                || (args.length == 1 && args[0] == null);
    }

    /**
     * 判断集合是否为空。
     *
     * @param <T>        集合泛型
     * @param collection 集合对象
     * @return 当集合对象为 <code>null</code> 或者长度为零时返回 <code>true</code>，否则返回
     * <code>false</code>。
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 是否为身份证号码
     *
     * @param str
     * @return
     */
    public static boolean isIdCardNumber(String str) {
        // 15位或18数字, 14数字加x(X)字符或17数字加x(X)字符才是合法的
        return isRegexMatch(str, REGEX_ID_CARD);
    }

    /**
     * 是否为中国移动手机号码。
     *
     * @param str 字符串
     * @return 如果是移动号码，返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static boolean isChinaMobile(String str) {
        return isRegexMatch(str, REGEX_CHINA_MOBILE);
    }

    /**
     * 是否为中国联通手机号码。
     *
     * @param str 字符串
     * @return 如果是联通号码，返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static boolean isChinaUnicom(String str) {
        return isRegexMatch(str, REGEX_CHINA_UNICOM);
    }

    /**
     * 是否为中国电信手机号码。
     *
     * @param str 字符串
     * @return 如果是联通号码，返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static boolean isChinaTelecom(String str) {
        return isRegexMatch(str, REGEX_CHINA_TELECOM);
    }

    /**
     * 是否为手机号码, 包括移动, 联通等手机号码.
     *
     * @param str 字符串
     * @return 若是合法的手机号码返回 <code>true</code>, 否则返回 <code>false</code>.
     */
    public static boolean isMobile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isChinaMobile(str) || isChinaUnicom(str) || isChinaTelecom(str);
    }

    /**
     * 是否为数字的字符串。
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是固定范围内的数字的字符串
     *
     * @param str
     * @param min
     * @param max
     * @return true/false
     */
    public static boolean isNumber(String str, int min, int max) {
        if (!isNumber(str)) {
            return false;
        }

        int number = Integer.parseInt(str);
        return number >= min && number <= max;
    }

    /**
     * 判断字符是否为整数或浮点数. <br>
     *
     * @param str 字符串
     * @return 若为整数或浮点数则返回 <code>true</code>, 否则返回 <code>false</code>
     */
    public static boolean isNumeric(String str) {
        return isRegexMatch(str, REGEX_NUMERIC);
    }

    /**
     * 判断字符是否为符合精度要求的整数或浮点数。
     *
     * @param str         字符串
     * @param fractionNum 小数部分的最多允许的位数
     * @return 若为整数或浮点数则返回 <code>true</code>, 否则返回 <code>false</code>
     */
    public static boolean isNumeric(String str, int fractionNum) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        // 整数或浮点数
        String regex = "(\\+|-){0,1}(\\d+)([.]?)(\\d{0," + fractionNum + "})";
        return Pattern.matches(regex, str);
    }

    /**
     * 是否是电话号码
     * <p/>
     * e.g.<br>
     * 78674585 --> 合法</li><br>
     * 6872-4585 --> 合法<br>
     * (6872)4585 --> 合法<br>
     * 0086-10-6872-4585 --> 合法<br>
     * 0086-(10)-6872-4585 --> 合法<br>
     * 0086(10)68724585 --> 合法
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        return isRegexMatch(str, REGEX_PHONE_NUMBER);
    }

    /**
     * 判断是否是合法的邮编
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isPostcode(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }

        if (str.length() != 6 || !Validators.isNumber(str)) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否是固定长度范围内的字符串
     *
     * @param str
     * @param minLength
     * @param maxLength
     * @return true/false
     */
    public static boolean isString(String str, int minLength, int maxLength) {
        if (str == null) {
            return false;
        }

        if (minLength < 0) {
            return str.length() <= maxLength;
        } else if (maxLength < 0) {
            return str.length() >= minLength;
        } else {
            return str.length() >= minLength && str.length() <= maxLength;
        }
    }

    /**
     * 判断是否是合法的时间字符串。
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isTime(String str) {
        if (TextUtils.isEmpty(str) || str.length() > 8) {
            return false;
        }

        String[] items = str.split(":");

        if (items.length != 2 && items.length != 3) {
            return false;
        }

        for (int i = 0; i < items.length; i++) {
            if (items[i].length() != 2 && items[i].length() != 1) {
                return false;
            }
        }

        return !(!isNumber(items[0], 0, 23) || !isNumber(items[1], 0, 59) || (items.length == 3 && !isNumber(
                items[2], 0, 59)));
    }

    /**
     * 是否是简体中文字符串。
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isSimpleChinese(String str) {
        return isRegexMatch(str, REGEX_SIMPLE_CHINESE);
    }

    /**
     * 判断字符串是否匹配了正则表达式。
     *
     * @param str   字符串
     * @param regex 正则表达式
     * @return true/false
     */
    public static boolean isRegexMatch(String str, String regex) {
        return str != null && str.matches(regex);
    }
}
