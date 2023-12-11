package com.deyi.daxie.cloud.operation.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SpiltDateUtil {
    private static SimpleDateFormat monthDateFormat = new SimpleDateFormat("MM");
    //将时间段按星期分割
    public static final List<Range> splitToWeeks(Date start, Date end) {
        List<Range> result = new ArrayList<>();
        result.add(Range.create(start));
        Date from = new Date(start.getTime() + 7L * 24 * 3600 * 1000);
        Date weekEnd = cn.hutool.core.date.DateUtil.endOfWeek(end);
        while (from.compareTo(weekEnd) <= 0) {
            Date dt = cn.hutool.core.date.DateUtil.beginOfWeek(from);
            CollectionUtil.getLast(result).end(new Date(dt.getTime() - 24L * 3600 * 1000));
            CollectionUtil.getLast(result).setMonth(monthDateFormat.format(new Date(dt.getTime() - 24L * 3600 * 1000)));
            result.add(Range.create(dt));
            from.setTime(from.getTime() + 7L * 24 * 3600 * 1000);
        }
        CollectionUtil.getLast(result).end(end);
        CollectionUtil.getLast(result).setMonth(monthDateFormat.format(end));
        return result;
    }

    //将时间段按照月分割
    public static final List<Range> splitToMonths(Date start, Date end) {
        List<Range> result = new ArrayList<>();
        result.add(Range.create(start));
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MONTH, 1);
        Date monthEnd = cn.hutool.core.date.DateUtil.endOfMonth(end);
        while (cal.getTimeInMillis() <= monthEnd.getTime()) {
            Date dt = cn.hutool.core.date.DateUtil.beginOfMonth(cal.getTime());
            CollectionUtil.getLast(result).end(new Date(dt.getTime() - 24L * 3600 * 1000));
            CollectionUtil.getLast(result).setMonth(monthDateFormat.format(new Date(dt.getTime() - 24L * 3600 * 1000)));
            result.add(Range.create(dt));
            cal.add(Calendar.MONTH, 1);
        }
        CollectionUtil.getLast(result).end(end);
        CollectionUtil.getLast(result).setMonth(monthDateFormat.format(end));
        return result;
    }

    //将时间段按照季度分割
    public static final List<Range> splitToQuarts(Date start, Date end) {
        List<Range> result = new ArrayList<>();
        result.add(Range.create(start));
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MONTH, 3);
        Date quartEnd = cn.hutool.core.date.DateUtil.endOfQuarter(end);
        while (cal.getTimeInMillis() <= quartEnd.getTime()) {
            Date dt = cn.hutool.core.date.DateUtil.beginOfQuarter(cal.getTime());
            CollectionUtil.getLast(result).end(new Date(dt.getTime() - 24L * 3600 * 1000));
            System.out.println(cal.get(Calendar.MONTH));
            if (cal.get(Calendar.MONTH) >= 1 && cal.get(Calendar.MONTH) <= 3) {
                CollectionUtil.getLast(result).setMonth("4");
            } else if (cal.get(Calendar.MONTH) >= 4 && cal.get(Calendar.MONTH) <= 6) {
                CollectionUtil.getLast(result).setMonth("1");
            } else if (cal.get(Calendar.MONTH) >= 7 && cal.get(Calendar.MONTH) <= 9) {
                CollectionUtil.getLast(result).setMonth("2");
            } else if (cal.get(Calendar.MONTH) >= 10 && cal.get(Calendar.MONTH) <= 12) {
                CollectionUtil.getLast(result).setMonth("3");
            }
            result.add(Range.create(dt));
            cal.add(Calendar.MONTH, 3);
        }
        CollectionUtil.getLast(result).end(end);
        System.out.println(cal.get(Calendar.MONTH));
        if (cal.get(Calendar.MONTH) >= 1 && cal.get(Calendar.MONTH) <= 3) {
            CollectionUtil.getLast(result).setMonth("4");
        } else if (cal.get(Calendar.MONTH) >= 4 && cal.get(Calendar.MONTH) <= 6) {
            CollectionUtil.getLast(result).setMonth("1");
        } else if (cal.get(Calendar.MONTH) >= 7 && cal.get(Calendar.MONTH) <= 9) {
            CollectionUtil.getLast(result).setMonth("2");
        } else if (cal.get(Calendar.MONTH) >= 10 && cal.get(Calendar.MONTH) <= 12) {
            CollectionUtil.getLast(result).setMonth("3");
        }
        return result;
    }

    //将时间段按照半年分割
    public static final List<Range> splitToHalfOfYears(Date start, Date end) {
        List<Range> result = new ArrayList<>();
        result.add(Range.create(start));
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MONTH, 6);
        Calendar calOne = Calendar.getInstance();
        calOne.setTime(end);
        int mon = cal.get(Calendar.MONTH);
//        Calendar tmp = Calendar.getInstance();
//        if (mon < 6) {
//            tmp.setTimeInMillis(cal.getTimeInMillis());
//            tmp.set(Calendar.MONTH, 5);
//        } else {
//            tmp.setTimeInMillis(cal.getTimeInMillis());
//            tmp.set(Calendar.MONTH, 11);
//        }
        Calendar halfYearEnd = cn.hutool.core.date.DateUtil.endOfMonth(calOne);
        while (cal.getTimeInMillis() <= halfYearEnd.getTimeInMillis()) {
            mon = cal.get(Calendar.MONTH);
            Calendar cal1 = Calendar.getInstance();
            if (mon < 6) {
                cal1.setTimeInMillis(cal.getTimeInMillis());
                cal1.set(Calendar.MONTH, 0);
            } else {
                cal1.setTimeInMillis(cal.getTimeInMillis());
                cal1.set(Calendar.MONTH, 6);
            }
            Date dt = cn.hutool.core.date.DateUtil.beginOfMonth(cal1.getTime());
            CollectionUtil.getLast(result).end(new Date(dt.getTime() - 24L * 3600 * 1000));
            result.add(Range.create(dt));
            cal.add(Calendar.MONTH, 6);
        }
        CollectionUtil.getLast(result).end(end);
        return result;
    }

    //将时间段按照年分割
    public static final List<Range> splitToYears(Date start, Date end) {
        List<Range> result = new ArrayList<>();
        result.add(Range.create(start));
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.YEAR, 1);
        Date yearEnd = cn.hutool.core.date.DateUtil.endOfYear(end);
        while (cal.getTimeInMillis() <= yearEnd.getTime()) {
            Date dt = cn.hutool.core.date.DateUtil.beginOfYear(cal.getTime());
            CollectionUtil.getLast(result).end(new Date(dt.getTime() - 24L * 3600 * 1000));
            result.add(Range.create(dt));
            cal.add(Calendar.YEAR, 1);
        }
        CollectionUtil.getLast(result).end(end);
        return result;
    }

    //日期区间
    public static class Range {
        Date start;
        Date end;
        String Month;

        public Range() {

        }

        private Range(Date start) {
            this.start = start;
        }

        public static Range create(Date start) {
            return new Range(start);
        }

        public Range end(Date end) {
            this.end = end;
            return this;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }

        public String getMonth() {
            return Month;
        }

        public void setMonth(String month) {
            Month = month;
        }

        @Override
        public String toString() {
            return "[" + cn.hutool.core.date.DateUtil.format(start, "yyyy-MM-dd") + "," + cn.hutool.core.date.DateUtil.format(end, "yyyy-MM-dd")
                    + "]";
        }
    }

    //根据年，月，第几周获取时间范围
    public static Map getWeekDateLimits(String year, String month, String week) {
        Map result = new HashMap();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, Integer.parseInt(year));
        c1.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        c1.set(Calendar.DAY_OF_WEEK, 2);
        c1.set(Calendar.WEEK_OF_MONTH, Integer.parseInt(week));
        Date time1 = c1.getTime();
        String begin = dft.format(time1);
        c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 6);
        Date time2 = c1.getTime();
        String end = dft.format(time2);
        result.put("begin", begin);
        result.put("end", end);
        return result;
    }
}

