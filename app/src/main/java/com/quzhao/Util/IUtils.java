package com.quzhao.Util;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/26.
 */

public class IUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
    /**
     * 使用java正则表达式验证邮箱地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    /**
     * 使用java正则表达式验证手机号码
     *
     * @return
     */
    public static boolean isPhone(String email) {
        String str = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * <p>shang</p>
     * <p>判断字符串是否是浮点类型</p>
     */
    public static boolean isPositiveDecimal(String email) {
        String str = "\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    /**
     * <p>用户昵称 判断</p>
     *
     * @return true 标示 包含的有非法字符
     * false 标示 没有包含非法字符
     */
    public static boolean checkStrContainIllegal(String str) {//& 这个符号为啥 在手机上 检测不到呢
        String regEx = "[^`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，？¥＆]*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return !m.matches();
    }


    /**
     * <P> 只允许数字，英文，中文，下划线  </P>
     *
     * @return true 标示 包含的有非法字符
     * false 标示 没有包含非法字符
     */
    public static boolean checkWifiIllegal(String str) {
        String regEx = "^[\u4E00-\u9FA5a-zA-Z0-9_]*$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return !m.matches();
    }


    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$";

    /**
     * 正则表达式：验证港澳台证
     */
//    public static final String REGEX_ID_HKMT = "/(^[HMhm]{1}([0-9]{10}|[0-9]{8})$)|(^[0-9]{10}$)/";
    public static final String REGEX_ID_HKMT = "/([A-Z]{1,2}[0-9]{6}([0-9A]))|(^[1|5|7][0-9]{6}\\([0-9Aa]\\))|([A-Z][0-9]{9})/";
    /**
     * 正则表达式：验证护照
     */
//    public static final String REGEX_ID_PASSPORT = "/^[a-zA-Z0-9]{5,17}$/";
    public static final String REGEX_ID_PASSPORT = " /^1[45][0-9]{7}|([P|p|S|s]\\d{7})|([S|s|G|g]\\d{8})|([Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8})|([H|h|M|m]\\d{8,10})$/";
    /**
     * 正则表达式：验证军官证
     */
//    public static final String REGEX_ID_COO = " /^[a-zA-Z0-9]{7,21}$/";
    public static final String REGEX_ID_COO = " /[\\u4e00-\\u9fa5](字第){1}(\\d{4,8})(号?)$/";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    //************************************网上找的，一般难用到***********************************
    /**
     * 检查字符串中是否还有HTML标签
     */
    public static final String HTMLTAGHAS = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";
    /**
     * 检查IP是否合法
     */
    public static final String IPADRESS = "\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}";
    /**
     * 检查QQ号是否合法
     */
    public static final String QQCODE = "[1-9][0-9]{4,13}";
    /**
     * 检查邮编是否合法
     */
    public static final String POSTCODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 正整数
     */
    public static final String POSITIVE_INTEGER = "^[0-9]\\d*$";
    /**
     * 正浮点数
     */
    public static final String POSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[0-9]\\d*$";
    /**
     * 整数或小数
     */
    public static final String POSITIVE_DOUBLE = "^[0-9]+(\\.[0-9]+)?$";
    /**
     * 年月日 2012-1-1,2012/1/1,2012.1.1
     */
    public static final String DATE_YMD = "^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}$";
}
