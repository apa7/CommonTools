package cn.lidd.util;


import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ���ں���
 *
 */
public class DateUtil {

    /**
     * ���ڸ�ʽ�������գ�
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ���ڸ�ʽ����/��/�գ�
     */
    public static final String YYYYMMDD_SLASH = "yyyy/MM/dd";

    /**
     * ���ڸ�ʽ����/�£�
     */
    public static final String YYYYMM_SLASH = "yyyy/MM";

    /**
     * ���ڸ�ʽ����-��-�գ�
     */
    public static final String YYYYMMDD_MID_LINE = "yyyy-MM-dd";

    /**
     * ���ڸ�ʽ����-�£�
     */
    public static final String YYYYMM_MID_LINE = "yyyy-MM";

    /**
     * ���ڸ�ʽ��������ʱ���룩
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * ���ڸ�ʽ����-��-�� ʱ:��:�룩
     */
    public static final String YYYYMMDDHHMMSS_MID_LINE = "yyyy-MM-dd HH:mm:ss";

    /**
     * ���ڸ�ʽ����/��/�� ʱ:��:�룩
     */
    public static final String YYYYMMDDHHMMSS_SLASH = "yyyy/MM/dd HH:mm:ss";

    /**
     * ���캯��
     */
    private DateUtil() {

    }

    /**
     * ȡ�õ�ǰϵͳʱ��
     *
     * @return ϵͳʱ��
     */
    public static Date getSystemDate() {
        return new Date();
    }

    /**
     * ȡ��Timestamp���͵ĵ�ǰϵͳʱ��
     *
     * @return Timestamp���͵�ϵͳʱ��
     */
    public static Timestamp getTimestamp() {
        return toTimestamp(getSystemDate());
    }

    /**
     * ȡ��ָ����ʽ��ϵͳʱ��.
     * ���磺
     * (2008��3��22��15ʱ30�֡�����2008-03-22 (format = "yyyy-MM-dd"))
     * @param format
     * 			ָ����ʽ
     * @return ϵͳʱ��
     */
    public static String getCurrentTime(String format) {
        return formatDateToStr(getSystemDate(), format);
    }

    /**
     * ȡ�õ�ǰ��
     *
     * @return ��ǰ��
     */
    public static String getCurentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(getSystemDate());
    }

    /**
     * ����֮���N���º���³���һ��
     *
     * @param n
     * 		N���º�
     * @return N���º���³���һ��
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
     * ����֮���N���º����ĩ���һ��
     *
     * @param n
     * 		N���º�
     * @return N���º����ĩ���һ��
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
     * ���ָ������+N��������
     *
     * @param date
     * 			ָ������
     * @param addCount
     * 			N��
     * @return ָ������+N��������
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
     * ���ָ������+N�µ�����
     *
     * @param date
     * 			ָ������
     * @param addCount
     * 			N��
     * @return ָ������+N�µ�����
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
     * Date����ת����Timestamp
     *
     * @param date
     *            Date��������
     * @return Timestamp����
     */
    public static Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * �����ڽ��и�ʽ��.
     * @param date
     *            Date����
     * @param dateFormat
     *            Date��ʽ������
     * @return ��ʽ���������
     */
    public static String formatDateToStr(Date date, String dateFormat) {
		if (date == null) {
			return "";
		}
		return getFormatter(dateFormat).format(date);
	}

    /**
	 * ��ĳ�����ڸ�ʽ�ַ���ת������һָ����ʽ�����ַ��� ��������"2009/1/15 16:58:00","yyyy/MM/dd
	 * hh:mm:ss","yyyy��M��d�� a hʱm��s��" �򷵻� ��2009��1��15�� ���� 4ʱ58��0�롱
	 *
	 * @param dateStr
	 *            ָ������
	 * @param inDateFormat
	 *            ���������ַ��������ڸ�ʽ
	 * @param outDateFormat
	 *            ���������ַ��������ڸ�ʽ
	 * @return ����ָ����ʽ�������ַ���
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
	 * ���ַ���ת��Ϊ��ʽΪformmat �����ڸ�ʽ����
	 *
	 * @param date
	 *            ��ת��������
	 * @param formmat
	 *            ת����format
	 * @return ת���������
	 */
    public static Date stringToFormatDate(String date, String formmat) {
        try{
            return getFormatter(formmat).parse(date);
        }catch(Exception e){
            return null;
        }
    }

    /**
     * �����Ƿ����ת�ɹ涨�ĸ�ʽ����ȷ�Լ��
     *
     * @param
     *      dateStr ����
     * @param
     *      format ���ڸ�ʽ
     * @return ��ȷ��true ����false
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
     * �Ƿ���ʱ�����Ч�Լ��
     *
     * @param dateStr
     *          ʱ��
     * @return ��Ч��true ��Ч��false
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
     * ������������ڼ�������
     *
     * @param birthday �������� ��ʽ��yyyy-MM-dd
     * @return String ���䣨�������䣩
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
     * �����������ڵĲ�
     *@param date1 ����1
     *@param date2 ����2
     * @return int  ����1-����2��ֵ
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
     * �õ�����ĳ���µ�����
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
     * ��ȡһ���򵥵����ڸ�ʽ������
     * @param format
     * @return
     */
    private static SimpleDateFormat getFormatter(String format) {
    	return new SimpleDateFormat(format);
    }
}
