package com.fbs.widgetdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * Created by Administrator on 2017/5/31 0031.
 */

public class RegularUtil {

    //是否为 有效身份证件
    public static boolean checkIdCardRule(String decimals) {
        String regex = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
        return Pattern.matches(regex, decimals);
    }

    //是否为整数 ^-?\d+$
    public static boolean checkInteger(String decimals) {
        String regex = "^-?\\d+$";
        return Pattern.matches(regex, decimals);
    }

    //是否为非负整数数 ^\d+$
    public static boolean checkNonnegative(String decimals) {
        String regex = "^\\d+$";
        return Pattern.matches(regex, decimals);
    }

    //是否为 字母 数字 下划线
    public static boolean checkNameRule(String decimals) {
        String regex = "^[a-zA-Z\\d_\\s]*$";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 电话号码判断
     *
     * @param phoneNum
     * @return
     */
    public static boolean checkPhoneNum(String phoneNum) {
        Pattern patternPhone = Pattern.compile("^1(3[0-9]|4[57]|5[^4]|7[0135678]|8[0-9])\\d{8}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcherPhone = patternPhone.matcher(phoneNum);
        return matcherPhone.matches();
    }

}
