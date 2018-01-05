package com.quzhao.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/7.
 */
public class TimeUtils {
    private static final String TAG = "TimeUtils";
    /**
     * 时间戳转为年月日，时分秒
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 例如：cc_time=1291778220
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time ));

        return re_StrTime;
    }
///////////////////////////////////////
    public static String getStrTime_spe(SimpleDateFormat s,String cc_time,SimpleDateFormat formatter2) {

//        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy年HH月dd日");
        try {
            cc_time=formatter2.format(s.parse(cc_time));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return cc_time;
    }
    /////////////////////////////
    /**
     * 获取增加多少月的时间
     *
     * @return addMonth - 增加多少月
     */
    public static long getAddMonthDate(int addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, addMonth);
        return calendar.getTime().getTime();
    }

    /**
     * 获取增加多少天的时间
     *
     * @return addDay - 增加多少天
     */
    public static Date getAddDayDate(int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        return calendar.getTime();
    }
    /**
     * 获取增加多少小时的时间
     *
     * @return addDay - 增加多少消失
     */
    public static Date getAddHourDate(int addHour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, addHour);
        return calendar.getTime();
    }
    /**
     * 显示时间格式为 hh:mm
     *
     * @param context
     * @param when
     * @return String
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatTimeShort(Context context, long when) {
        String formatStr = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        String temp = sdf.format(when);
        if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0")) {
            temp = temp.substring(1);
        }
        return temp;
    }
    /**
     * 是否同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(long date1, long date2) {
        long days1 = date1 / (1000 * 60 * 60 * 24);
        long days2 = date2 / (1000 * 60 * 60 * 24);
        return days1 == days2;
    }
    /**
     * 显示时间格式为今天、昨天、yyyy/MM/dd hh:mm
     *
     * @param
     * @param when
     * @return String
     */
    public static String formatTimeString(long when) {
        Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();

        String formatStr;
        if (then.year != now.year) {
            formatStr = "yyyy年M月d日 H:mm:ss";
        } else if (then.yearDay != now.yearDay) {
            // If it is from a different day than today, show only the date.
            if(now.yearDay-then.yearDay==1){
                formatStr="昨天 H:mm";
            }else if(now.yearDay-then.yearDay==2){
                formatStr="前天 H:mm";
            }else {
                formatStr = "M月d日 H:mm";
            }
        } else {
            // Otherwise, if the message is from today, show the time.
            if(now.hour!=then.hour){
                formatStr=(now.hour-then.hour)+"小时前";
            }else if(now.minute!=then.minute){
                formatStr=(now.minute-then.minute)+"分钟前";
            }else {
                formatStr="刚刚";
            }
//            if(then.hour<6){
//                if(then.hour==0){
//                    formatStr="凌晨 HH:mm";
//                }else{
//                    formatStr="凌晨 H:mm";
//                }
//            }else if(then.hour>=6&&then.hour<12){
//                formatStr="上午 H:mm";
//            }else if(then.hour==12){
//                formatStr="中午 H:mm";
//            }else if(then.hour>=13&&then.hour<18){
//                formatStr="下午 H:mm";
//            }else {
//                formatStr="晚上 H:mm";
//            }
        }

//        if (then.year == now.year && then.yearDay == now.yearDay) {
//            return context.getString(R.string.date_today);
//        } else if ((then.year == now.year) && ((now.yearDay - then.yearDay) == 1)) {
//            return context.getString(R.string.date_yesterday);
//        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
            String temp = sdf.format(when);
            if (temp != null && temp.length() == 5 && temp.substring(0, 1).equals("0")) {
                temp = temp.substring(1);
            }
//        temp
            return temp;
//        }
    }
    /**
     * 掉此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    public static long dataOne(String time) {
        if(time==null||time.equals("")){
            return 0;
        }
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date;

        long l = 0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }
    public  static String getTrueTimeStr(String time,String simpleDateFormat,Locale locale){
        SimpleDateFormat sdr = new SimpleDateFormat(simpleDateFormat,
                locale);
        Date date;
//        String times = null;
        long l = 0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  formatTimeString(l);
    }
    public  static String getTrueTimeStr(String time){

        return  dataOne(time)!=0?TimeUtils.formatTimeString(dataOne(time)):"";
    }
}
