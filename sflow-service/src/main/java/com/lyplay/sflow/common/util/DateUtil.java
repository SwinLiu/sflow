package com.lyplay.sflow.common.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/**
	 * millis in a second
	 */
	public static final long MILLIS_IN_A_SECOND = 1000;

	/**
	 * seconds in a minute
	 */
	public static final long SECONDS_IN_A_MINUTE = 60;

	/**
	 * minutes in an hour
	 */
	public static final long MINUTES_IN_AN_HOUR = 60;

	/**
	 * hours in a day
	 */
	public static final long HOURS_IN_A_DAY = 24;

	/**
	 * days in a week
	 */
	public static final int DAYS_IN_A_WEEK = 7;

	/**
	 * months in a year
	 */
	public static final int MONTHS_IN_A_YEAR = 12;
	
	/**
	 * Min Date: set 1900/01/01
	 */
	public static final java.util.Date MIN_DATE = date(1900, 1, 1);
	/**
	 * Max Date: set 9999/12/31
	 */
	public static final java.util.Date MAX_DATE = date(9999, 12, 31);
	
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String YYYY_MM_DD = "yyyy/MM/dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String DDMMMYYYY_BY_SPACE = "dd MMM yyyy";
	public static final String MMMMMYYYY_BY_SPACE = "MMMMM yyyy";
	
	private static SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat(DD_MM_YYYY, Locale.US);
	private static SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat(YYYYMMDD, Locale.US);

	/**
	 * transfer one date string ("dd/MM/yyyy") to "yyyy/MM/dd" dataformat string.
	 * @param dateStr : dd/MM/yyyy
	 * @return String : yyyy/MM/dd
	 * @throws ParseException
	 */
	public static String transfer(String dateStr) throws ParseException {
		return transfer(dateStr, DD_MM_YYYY, YYYY_MM_DD);
	}

	/**
	 * transfer date string from one format string to another format string
	 * @param dateStr
	 * @param srcFormat
	 * @param targetFormat
	 * @return targetFormat string
	 * @throws ParseException
	 */
	public static String transfer(String dateStr, String srcFormat,
			String targetFormat) throws ParseException {
		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat, Locale.US);
		SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat,
				Locale.US);
		Date date = srcSdf.parse(dateStr);
		return targetSdf.format(date);
	}

	/**
	 * date string (default  "dd/MM/yyyy" format string) to long type
	 * @param dateStr : dd/MM/yyyy
	 * @return
	 * @throws ParseException
	 */
	public static Long dateToLong(String dateStr) throws ParseException {
		return dateToLong(dateStr, DD_MM_YYYY);
	}

	/**
	 * date string to long type
	 * @param dateStr
	 * @param format : dateStr's format
	 * @return
	 * @throws ParseException
	 */
	public static Long dateToLong(String dateStr, String format)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		Date date = sdf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) * 10000L + (cal.get(Calendar.MONTH) + 1)
				* 100L + cal.get(Calendar.DATE);
	}

	/**
	 * date to string (dd/MM/yyyy)
	 * @param date
	 * @return
	 */
	public static String toDateStr(Date date) {
		if (null == date) {
			return null;
		}

		return sdfDDMMYYYY.format(date);
	}

	/**
	 * String to date. default format is dd/MM/yyyy
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateStr) throws ParseException {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		return sdfDDMMYYYY.parse(dateStr);
	}

	/**
	 * get date offset value.
	 * @param d
	 * @param offset : days
	 * @return
	 */
	public static Date getDateOffset(Date d, int offset) {
		return new Date(d.getTime() + offset * 24 * 60 * 60 * 1000L);
	}

	/**
	 * get date offset value by TimeUnit
	 * @param d
	 * @param offset
	 * @param unit
	 * @return
	 */
	public static Date getDateOffset(Date d, int offset, TimeUnit unit) {
		return new Date(d.getTime() + unit.toMillis(offset));
	}

	/**
	 * date to string with the format
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toDateStr(Date date, String format) {
		if (null == date) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(date);
	}

	/**
	 * String to date with the format
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date toDate(String dateStr, String format)
			throws ParseException {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.parse(dateStr);
	}

	/**
	 * get current date string with default format yyyyMMdd
	 * @return
	 */
	public static String getCurrDateStr() {
		return sdfYYYYMMDD.format(new Date());
	}
	
	/**
	 * create a date
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return java.util.Date
	 */
	public static java.util.Date date(final int year, final int month, final int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * the year count between the two different date (date1 < date2)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getYearDiff(final java.util.Date date1, final java.util.Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException("date1 and date2 cannot be null!");
		}
		if (date1.after(date2)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);
		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}
	
	/**
	 * the month count between the two different date (date1 < date2)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthDiff(final java.util.Date date1, final java.util.Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException("date1 and date2 cannot be null!");
		}
		if (date1.after(date2)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);
		int months = 0;
		if (day2 >= day1) {
			months = month2 - month1;
		} else {
			months = month2 - month1 - 1;
		}
		return (year2 - year1) * MONTHS_IN_A_YEAR + months;
	}
	
	/**
	 * the day count between the two different date (date1 < date2) . (include
	 * date1，not include date2)
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDayDiff(final java.util.Date date1, final java.util.Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException("date1 and date2 cannot be null!");
		}
		long day = 0;
		try {
			day = (date1.getTime() - date2.getTime()) / (HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND);
		} catch (Exception e) {
			return 0;
		}
		return day;

	}
	
	/**
	 * get the days of two date.
	 * 
	 * Ex: fDate : 2015-Desc-31 0Date : 2015-Desc-31 return 0
	 * 
	 * fDate : 2015-Desc-30 0Date : 2015-Desc-31 return -1
	 * 
	 * fDate : 2016-Jan-1 0Date : 2015-Desc-31 return 1
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar prev_cal = Calendar.getInstance();
		Calendar next_cal = Calendar.getInstance();

		int flag = 1;

		if (fDate.before(oDate)) {
			flag = -1;
			prev_cal.setTime(fDate);
			next_cal.setTime(oDate);
		} else {
			prev_cal.setTime(oDate);
			next_cal.setTime(fDate);
		}

		int diff_years = next_cal.get(Calendar.YEAR) - prev_cal.get(Calendar.YEAR);
		int diff_days = 0;

		for (int i = 1; i <= diff_years; i++) {
			diff_days += next_cal.get(Calendar.DAY_OF_YEAR);
			next_cal.set(Calendar.DAY_OF_YEAR, 1);
			next_cal.add(Calendar.DATE, -1);
		}

		diff_days += next_cal.get(Calendar.DAY_OF_YEAR) - prev_cal.get(Calendar.DAY_OF_YEAR);

		return diff_days * flag;

	}
	
	/**
	 * get Previous Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getPrevDay(final java.util.Date date) {
		return getDifferDate(date, Calendar.DATE, 1);
	}

	/**
	 * get Next Date
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getNextDay(final java.util.Date date) {
		return getDifferDate(date, Calendar.DATE, 1);
	}

	/**
	 * get Previous Month
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getPrevMonth(final java.util.Date date) {
		return getDifferDate(date, Calendar.MONTH, -1);
	}

	/**
	 * get Next Month
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getNextMonth(final java.util.Date date) {
		return getDifferDate(date, Calendar.MONTH, 1);
	}

	/**
	 * get Previous Year
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getPrevYear(final java.util.Date date) {
		return getDifferDate(date, Calendar.YEAR, -1);
	}

	/**
	 * get Next Year
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date getNextYear(final java.util.Date date) {
		return getDifferDate(date, Calendar.YEAR, 1);
	}

	/**
	 * get Date after(before) a count of (Day,Month,Year,...)
	 * 
	 * @param date
	 * @param field
	 *            eg:Calendar.MONTH Calendar.YEAR Calendar.DATE .....
	 * @param count
	 *            eg: 1 2 3 -1 -2 -3
	 * @return
	 */
	public static java.util.Date getDifferDate(final java.util.Date date, final int field, final int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, count);
		return calendar.getTime();
	}

	/**
	 * get the total days in the year
	 * 
	 * @param year
	 * @return total days
	 */
	public static int getDaysInYear(final int year) {
		Calendar aDay = Calendar.getInstance();
		aDay.set(year, 1, 1);
		return aDay.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	/**
	 * get the total days in the month
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysInMonth(final java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get the date's year value
	 * 
	 * @param date
	 * @return year
	 */
	public static int getYear(final java.util.Date date) {
		return getFieldValue(date, Calendar.YEAR);
	}

	/**
	 * get the date's month value
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(final java.util.Date date) {
		return getFieldValue(date, Calendar.MONTH) + 1;
	}

	/**
	 * get the day of the year
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(final java.util.Date date) {
		return getFieldValue(date, Calendar.DAY_OF_YEAR);
	}

	/**
	 * get the day of the month
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(final java.util.Date date) {
		return getFieldValue(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * get the day of the week
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(final java.util.Date date) {
		return getFieldValue(date, Calendar.DAY_OF_WEEK);
	}

	/**
	 * get the field value of the date
	 * 
	 * @param date
	 * @param field
	 * @return
	 */
	public static int getFieldValue(final java.util.Date date, final int field) {
		if (date == null) {
			throw new InvalidParameterException("date cannot be null!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	/**
	 * get current year
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * get current month
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * get current day of year
	 * 
	 * @return
	 */
	public static int getDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * get current day of month
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * get current day of week
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * get current week of the month
	 * 
	 * @return
	 */
	public static int getWeekOfMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * get current month max days
	 * 
	 * @return
	 */
	public static int getLastOfMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);

		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}
	
	
}
