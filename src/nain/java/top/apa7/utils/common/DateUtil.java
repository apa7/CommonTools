package cn.lidd.util;


import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期函数
 *
 */
public class DateUtil {

    /**
     * 日期格式（年月日）
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式（年/月/日）
     */
    public static final String YYYYMMDD_SLASH = "yyyy/MM/dd";

    /**
     * 日期格式（年/月）
     */
    public static final String YYYYMM_SLASH = "yyyy/MM";

    /**
     * 日期格式（年-月-日）
     */
    public static final String YYYYMMDD_MID_LINE = "yyyy-MM-dd";

    /**
     * 日期格式（年-月）
     */
    public static final String YYYYMM_MID_LINE = "yyyy-MM";

    /**
     * 日期格式（年月日时分秒）
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式（年-月-日 时:分:秒）
     */
    public static final String YYYYMMDDHHMMSS_MID_LINE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式（年/月/日 时:分:秒）
     */
    public static final String YYYYMMDDHHMMSS_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
     * 构造函数
     */
    private DateUtil() {

    }

    /**
     * 取得当前系统时间
     *
     * @return 系统时间
     */
    public static Date getSystemDate() {
        return new Date();
    }

    /**
     * 取得Timestamp类型的当前系统时间
     *
     * @return Timestamp类型的系统时间
     */
    public static Timestamp getTimestamp() {
        return toTimestamp(getSystemDate());
    }

    /**
     * 取得指定格式的系统时间.
     * 例如：
     * (2008年3月22日15时30分　→　2008-03-22 (format = "yyyy-MM-dd"))
     * @param format
     * 			指定格式
     * @return 系统时间
     */
    public static String getCurrentTime(String format) {
        return formatDateToStr(getSystemDate(), format);
    }

    /**
     * 取得当前月
     *
     * @return 当前月
     */
    public static String getCurentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(getSystemDate());
    }

    /**
     * 当月之后的N个月后的月初第一天
     *
     * @param n
     * 		N个月后
     * @return N个月后的月初第一天
     */
    public static Calendar getMonthFirstDay(int n) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, n);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    /**
     * 当月之后的N个月后的月末最后一天
     *
     * @param n
     * 		N个月后
     * @return N个月后的月末最后一天
     */
    public static Calendar getMonthlastDay(int n) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, n + 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal;
    }

    /**
     * 获得指定日期+N天后的日期
     *
     * @param date
     * 			指定日期
     * @param addCount
     * 			N天
     * @return 指定日期+N天后的日期
     */
    public static Date addDay(Date date, int addCount) {
       if (date == null) {
           return null;
       }
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);
       calendar.add(Calendar.DATE, addCount);
       return calendar.getTime();
     }

    /**
     * 获得指定日期+N月的日期
     *
     * @param date
     * 			指定日期
     * @param addCount
     * 			N月
     * @return 指定日期+N月的日期
     */
    public static Date addMonth(Date date, int addCount) {
       if (date == null) {
           return null;
       }
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date);
       calendar.add(Calendar.MONTH, addCount);
       return calendar.getTime();
     }

    /**
     * Date类型转换成Timestamp
     *
     * @param date
     *            Date类型日期
     * @return Timestamp日期
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 对日期进行格式化.
     * @param date
     *            Date日期
     * @param dateFormat
     *            Date格式化类型
     * @return 格式化后的日期
     */
    public static String formatDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		return getFormatter(dateFormat).format(date);
	}

    /**
	 * 将某个日期格式字符串转换成另一指定格式日期字符串 例：传入"2009/1/15 16:58:00","yyyy/MM/dd
	 * hh:mm:ss","yyyy年M月d日 a h时m分s秒" 则返回 “2009年1月15日 下午 4时58分0秒”
	 *
	 * @param dateStr
	 *            指定日期
	 * @param inDateFormat
	 *            传入日期字符串的日期格式
	 * @param outDateFormat
	 *            传出日期字符串的日期格式
	 * @return 返回指定格式的日期字符串
	 */
    public static String formatDateToStr(String dateStr, String inDateFormat, String outDateFormat) {
        SimpleDateFormat simpleDateFormat = null;
		String str = "";
		Date date = null;
		try {
			simpleDateFormat = getFormatter(inDateFormat);
			date = simpleDateFormat.parse(dateStr);
			simpleDateFormat.applyPattern(outDateFormat);
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			return str;
		}
	}

    /**
	 * 将字符串转化为格式为formmat 的日期格式返回
	 *
	 * @param date
	 *            待转换的日期
	 * @param formmat
	 *            转换的format
	 * @return 转换后的日期
	 */
    public static Date stringToFormatDate(String date, String formmat) {
        try{
            return getFormatter(formmat).parse(date);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 日期是否可以转成规定的格式的正确性检查
     *
     * @param
     *      dateStr 日期
     * @param
     *      format 日期格式
     * @return 正确：true 错误：false
     */
    public static boolean isValidDate (String dateStr, String format) {
        try {
        	getFormatter(format).parse(dateStr);
        	return true;
        } catch (Exception e) {
        	return false;
        }
    }

    /**
     * 是否是时间的有效性检查
     *
     * @param dateStr
     *          时间
     * @return 有效：true 无效：false
     */
    public static boolean isValidTime (String dateStr) {
        boolean reval = false;
        if (!StringUtils.isBlank(dateStr)) {
            if (dateStr.length() == 4) {
                try {
                    Integer.parseInt(dateStr);
                    int hour = Integer.parseInt(dateStr.substring(0, 2));
                    int min = Integer.parseInt(dateStr.substring(2, 4));
                    if (hour <= 23 && hour >= 0 && min <= 59 && min >= 0) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        return reval;
    }

    /**
     * 根据输入的日期计算年龄
     *
     * @param birthday 出生日期 格式：yyyy-MM-dd
     * @return String 年龄（周岁年龄）
     * */
    public static String getAge(String birthday){
        if (StringUtils.isBlank(birthday)) {
            return "0";
        }
        Calendar birthdays = Calendar.getInstance();
        try {
            birthdays.setTime(getFormatter(YYYYMMDD_MID_LINE).parse(birthday));
        } catch (ParseException e) {
            return "0";
        }
        Calendar today = new GregorianCalendar();
        int age = today.get(Calendar.YEAR) - birthdays.get(Calendar.YEAR);
        birthdays.add(Calendar.YEAR, age);
        if (today.before(birthdays)) {
            age--;
        }
        return String.valueOf(age);
    }

    /**
     * 计算两个日期的差
     *@param date1 日期1
     *@param date2 日期2
     * @return int  日期1-日期2的值
     */
    public static int getDateDiffDays(Date date1 ,Date date2) {
        int retValue = 0;
        if (date1 != null && date2 != null) {
            long dateDiff = date1.getTime() - date2.getTime();
            retValue = (int)(dateDiff/(24 * 60 * 60 * 1000));
        }
        return retValue;
    }

    /**
     * 得到具体某个月的天数
     * @throws ParseException
     */
    public static int getDaysOfMonth(String year, String month) throws ParseException{
        String datestr = year + "/" + month;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = getFormatter(YYYYMM_SLASH);
        cal.setTime(dateFormat.parse(datestr));
        int daysOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysOfMonth;
    }

    /**
     * 获取一个简单的日期格式化对象
     * @param format
     * @return
     */
    private static SimpleDateFormat getFormatter(String format) {
    	return new SimpleDateFormat(format);
    }
}
