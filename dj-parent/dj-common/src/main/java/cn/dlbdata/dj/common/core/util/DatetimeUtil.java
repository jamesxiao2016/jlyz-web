package cn.dlbdata.dj.common.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class DatetimeUtil {

	// 日志器
	private static final Logger logger = Logger.getLogger(DatetimeUtil.class);

	// public static SimpleDateFormat SHORT_SDF = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss");

	/**
	 * 将字符串转换成日期格式（包含时分秒）
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getLongDateByStr(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = LONG_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	/**
	 * 将字符串转换成日期格式（不包含时分秒）
	 * 
	 * @param str
	 *            要转换的字符串
	 * @return
	 */
	public static Date getShortDateByStr(String str) {
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = SHORT_SDF.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	public static String getLongDateByStr(Date date) {
		if (date == null) {
			return null;
		}
		String result = "";
		SimpleDateFormat LONG_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = LONG_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static String getShortDateByStr(Date date) {
		if (date == null) {
			return null;
		}
		String result = "";
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = SHORT_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	/**
	 * 根据指定的格式格式化日期
	 * 
	 * @param date
	 *            要转换的日期
	 * @param pattern
	 *            格式化的字符串
	 * @return
	 */
	public static String getDateStrByPattern(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		String result = "";
		SimpleDateFormat SHORT_SDF = new SimpleDateFormat(pattern);
		try {
			result = SHORT_SDF.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static int getWorkingDay(Date begin, Date end) {
		int result = 0;
		if (begin == null || end == null) {
			result = 0;
		}
		if (begin.after(end)) { // swap dates so that d1 is start and d2 is end
			Date swap = begin;
			begin = end;
			end = swap;
		}
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(begin);
		int beginYear = beginCalendar.get(Calendar.YEAR);
		int beginWeek = beginCalendar.get(Calendar.WEEK_OF_YEAR);
		boolean isFirstSunday = (beginCalendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		int beginDayOfWeek = beginCalendar.get(Calendar.DAY_OF_WEEK);
		if (isFirstSunday) {
			beginDayOfWeek = beginDayOfWeek - 1;
			if (beginDayOfWeek == 0) {
				beginDayOfWeek = 7;
				beginWeek -= 1;
			}
		}
		if (beginDayOfWeek >= 6) {
			beginDayOfWeek = 6;
		}

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		int endYear = endCalendar.get(Calendar.YEAR);
		int endWeek = endCalendar.get(Calendar.WEEK_OF_YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH);
		int endDayOfWeek = endCalendar.get(Calendar.DAY_OF_WEEK);
		if (endMonth >= 11 && endWeek <= 1) {
			endWeek += 52;
		}
		boolean isFirstSundayEnd = (beginCalendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		if (isFirstSundayEnd) {
			endDayOfWeek = endDayOfWeek - 1;
			if (endDayOfWeek == 0) {
				endDayOfWeek = 7;
				endWeek -= 1;
			}
		}
		if (endDayOfWeek >= 6) {
			endDayOfWeek = 5;
		}

		if (beginYear == endYear && beginWeek == endWeek) {// 同一周
			result = endDayOfWeek - beginDayOfWeek + 1;
		} else {
			result = (endWeek - beginWeek - 1) * 5 + (6 - beginDayOfWeek) + endDayOfWeek;
		}
		return result;
	}

	/**
	 * 获取某一个月份的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthFirstDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 0);
		// 将秒至0
		calendar.set(Calendar.SECOND, 0);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取某一个月份的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthLastDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		// calendar.add(Calendar.DATE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至0
		calendar.set(Calendar.MINUTE, 59);
		// 将秒至0
		calendar.set(Calendar.SECOND, 59);
		// 将毫秒至0
		calendar.set(Calendar.MILLISECOND, 999);
		// System.out.println(calendar.getTime());
		return calendar.getTime();
	}

	public static Date getISODateByStr(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		str = str.replaceFirst("T", " ").replaceFirst("Z", " UTC");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date result = null;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		try {
			result = sdf.parse(str);
		} catch (Exception e) {
			logger.error("日期转换失败" + str, e);
		}

		return result;
	}

	public static String getISODateString(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ'Z'");
		String result = null;
		try {
			result = sdf.format(date);
		} catch (Exception e) {
			logger.error("日期转换失败" + date, e);
		}

		return result;
	}

	public static void main(String[] args) {
		// Date date = new Date();
		// date.setTime(1481587200000l);
		// System.out.println(date.toString());

		// Calendar d1 = Calendar.getInstance();
		// d1.set(2017, 0, 1);
		// Calendar d2 = Calendar.getInstance();
		// d2.set(2017, 1, 28);
		// System.out.println(getWorkingDay(d1.getTime(), d2.getTime()));
		// System.out.println(getLongDateByStr(getMonthFirstDay(2017, 07)));
		// System.out.println(getLongDateByStr(getMonthLastDay(2017, 07)));
//		Date d = getISODateByStr("2017-12-18T02:04:41Z");
//		System.out.println(d);
//		System.out.println(getISODateString(d));

		int s = (int)(new Date().getTime()/1000);
		System.out.println(s);
		System.out.println(new Date().getTime());
	}

}
