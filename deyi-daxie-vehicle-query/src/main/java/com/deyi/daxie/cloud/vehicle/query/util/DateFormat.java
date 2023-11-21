package com.deyi.daxie.cloud.vehicle.query.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: 时间转换工具
 *
 * @author Huang ShuYing
 * @date 2022/10/8
 */
@Slf4j
public class DateFormat {


    /**
     * Description: 时间转换字符串 yyyy-MM-dd HH:mm:ss
     *
     * @date 2022/10/8
     * @author Huang ShuYing
     */
    public static String timestampToDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * Description: 获取指定日期零点毫秒时间戳
     *
     * @param t 当天date=0,昨天date=1,以此类推
     * @date 2022/10/13
     * @author Huang ShuYing
     */
    public static long getTime(int t) {
        long time = 0;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -t);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd ").parse(yesterday);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * Description: 获取指定日期
     *
     * @param t 当天date=0,昨天date=1,以此类推
     * @date 2022/10/13
     * @author Huang ShuYing
     */
    public static String getDate(int t) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -t);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        return yesterday;
    }


    public static Long dateToTime(Date date) {
        long time = 0;
        String day = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date.getTime());
        try {
            Date dates = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(day.substring(0,day.length()-1)+"0");
            time = dates.getTime();
        } catch (ParseException e) {
            log.error("时间转换错误：{}",e.getMessage());
        }
        return time;
    }

    public static String timeToDate(Long date) {
        String day = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        return day;
    }


}
