package cn.stylefeng.guns.utils;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 4/15/20
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class TimeUtils {

    public static void main(String[] a){
        List<Long> dateListByWeeks = getDateListByWeeks(new int[]{1, 4, 5}, 10);
        for ( Long dateListByWeek : dateListByWeeks ) {
            System.out.println(new DateTime(dateListByWeek).toString("yyyy-MM-dd HH:mm:ss EE"));
        }
    }

    /**
     * 获取从今天开始 接下星期 weekIndex 的 nums条数据
     * weekIndex 1~7 周一 ~ 周日
     */
    public static List<Long> getDateListByWeek(int weekIndex, int nums) {
        DateTime now = new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),
                DateTime.now().getDayOfMonth(), 0, 0, 0);
        DateTime aimDay = null;
        //获取今日是本周的第几天
        int todayOfWeek = now.getDayOfWeek();
        //计算偏移量
        int offset = todayOfWeek - weekIndex;
        if ( offset > 0 ) {
            //选定的星期是在今天之后的
            aimDay = now.plusDays(7-offset);
        } else if ( offset == 0 ) {
            //选定的星期是今天
            aimDay = now;
        } else {
            //选定的星期是在今天之前的
            aimDay = now.minusDays(offset);
        }
        List<Long> daysList = new ArrayList<>();
        if ( nums != 0 ) {
            for ( int i = 0; i < nums; i++ ) {
                daysList.add(aimDay.plusWeeks(i).getMillis());
            }
        }
        return daysList;
    }

    /**
     * 获取从今天开始 接下星期 weekIndexs(比如要查询周一 周三 就是 new int[]{1,3}) 的 nums条数据
     * @param weekIndexs
     * @param nums
     * @return 返回的是 nuns * weekIndexs.length 条数据
     */
    public static List<Long> getDateListByWeeks(int[] weekIndexs, int nums) {
        int weekIndex = 0;
        if ( weekIndexs.length != 0 ) {
            weekIndex = weekIndexs[0];
        }
        DateTime now = new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),
                DateTime.now().getDayOfMonth(), 0, 0, 0);
        DateTime aimDay = null;
        //获取今日是本周的第几天
        int todayOfWeek = now.getDayOfWeek();
        //计算偏移量
        int offset = todayOfWeek - weekIndex;
        if ( offset < 0 ) {
            //选定的星期是在今天之后的
            aimDay = now.plusDays(offset);
        } else if ( offset == 0 ) {
            //选定的星期是今天
            aimDay = now;
        } else {
            //选定的星期是在今天之前的
            aimDay = now.minusDays(offset);
        }
        List<Long> daysList = new ArrayList<>();
        if ( nums != 0 ) {
            for ( int i = 0; i < nums; i++ ) {
                if ( weekIndexs.length != 0 ) {
                    int first = weekIndexs[0];
                    daysList.add(aimDay.plusWeeks(i).getMillis());
                    for ( int j = 1; j < weekIndexs.length; j++ ) {
                        daysList.add(aimDay.plusWeeks(i).plusDays(weekIndexs[j] - first).getMillis());
                    }
                }
            }
        }
        return daysList;
    }

    /**
     * 获取截止 endTimes 的 所有 接下星期 weekIndex 的数据列表
     *
     * @param weekIndex
     * @param endTimes
     * @return
     */
    public static List<Long> getDateListByWeek(int weekIndex, long endTimes) {
        if ( endTimes < DateTime.now().getMillis() ) {
            return new ArrayList<>();
        }

        DateTime now = new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),
                DateTime.now().getDayOfMonth(), 0, 0, 0);
        DateTime aimDay = null;
        //获取今日是本周的第几天
        int todayOfWeek = now.getDayOfWeek();
        //计算偏移量
        int offset = todayOfWeek - weekIndex;
        if ( offset < 0 ) {
            //选定的星期是在今天之后的
            aimDay = now.plusDays(offset);
        } else if ( offset == 0 ) {
            //选定的星期是今天
            aimDay = now;
        } else {
            //选定的星期是在今天之前的
            aimDay = now.minusDays(offset);
        }

        List<Long> daysList = new ArrayList<>();
        //计算出时间差
        long timeTemp = endTimes - aimDay.getMillis();
        long weekTimes = timeTemp / (24 * 7 * 3600 * 1000) + 1;
        for ( int i = 0; i < weekTimes; i++ ) {
            daysList.add(aimDay.plusWeeks(i).getMillis());
        }

        return daysList;
    }
}
