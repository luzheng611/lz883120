package com.quzhao.Util;

/**
 * Created by Administrator on 2016/12/17.
 */
public class NumUtils {
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
    public static String getNumStr(String num) {
        if (num == null || num.equals("")) {
            return "0";
        }
        double n = Double.valueOf(num);
        if (n < 10000) {
            return num;
        } else {
            if(n>=100000000){
                double d=Double.valueOf(num)/100000000;
                return subZeroAndDot(String .format("%.2f",d))+"亿";
            }else{
                double d = Double.valueOf(num) / 10000;
                return subZeroAndDot(String.format("%.2f", d))+"万";
            }

        }



    }
}
