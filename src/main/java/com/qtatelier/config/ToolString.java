package com.qtatelier.config;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-13 15:36
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
public class ToolString {
    public ToolString() {
    }

    public static boolean judgeLength(String str, int len) {
        boolean result = true;
        if (null == str) {
            return result;
        } else {
            return str.length() <= len ? result : false;
        }
    }

    public static String judgeLength(String str, int len, String strDesc) {
        return !judgeLength(str, len) ? strDesc + "的长度大于了" + len + "; " : "";
    }

    public static boolean isChina(char str) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(str);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static String join(String[] values, String joinChar) {
        StringBuilder result = new StringBuilder();
        if (values != null) {
            for(int i = 0; i < values.length; ++i) {
                if (values[i] != null && !values[i].trim().equals("")) {
                    result.append(values[i]);
                    if (i < values.length - 1) {
                        result.append(joinChar);
                    }
                }
            }
        }

        return result.toString();
    }

    public static int ChineseLength(String str) {
        Pattern p = Pattern.compile("[一-龥]+");
        Matcher m = p.matcher(str);

        int i;
        String temp;
        for(i = 0; m.find(); i += temp.length()) {
            temp = m.group(0);
        }

        return i;
    }

    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for(int i = 0; i < input.length; ++i) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output = output + temp[0];
                } else {
                    output = output + Character.toString(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination var6) {
            var6.printStackTrace();
        }

        return output;
    }

    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }

        return pybf.toString().replaceAll("\\W", "").trim();
    }

    public static String getFullSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < arr.length; ++i) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }

        return pybf.toString();
    }
}