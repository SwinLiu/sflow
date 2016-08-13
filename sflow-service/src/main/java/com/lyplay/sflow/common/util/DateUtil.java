package com.lyplay.sflow.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String YYYY_MM_DD = "yyyy/MM/dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String DDMMMYYYY_BY_SPACE = "dd MMM yyyy";
	public static final String MMMMMYYYY_BY_SPACE = "MMMMM yyyy";
	public static final String DDMMMYYYYHHMMA_BY_SPACE = "dd MMM yyyy/ HH:mm a";
	
	private static SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat(DD_MM_YYYY, Locale.US);
	private static SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat(YYYYMMDD, Locale.US);
	private static SimpleDateFormat sdfMMMMMYYYY = new SimpleDateFormat(MMMMMYYYY_BY_SPACE, Locale.US);

	public static String transfer(String dateStr) throws ParseException {
		return transfer(dateStr, DD_MM_YYYY, YYYY_MM_DD);
	}

	public static String transfer(String dateStr, String srcFormat,
			String targetFormat) throws ParseException {
		SimpleDateFormat srcSdf = new SimpleDateFormat(srcFormat, Locale.US);
		SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat,
				Locale.US);
		Date date = srcSdf.parse(dateStr);
		return targetSdf.format(date);
	}

	public static Long dateToLong(String dateStr) throws ParseException {
		return dateToLong(dateStr, DD_MM_YYYY);
	}

	public static Long dateToLong(String dateStr, String format)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		Date date = sdf.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) * 10000L + (cal.get(Calendar.MONTH) + 1)
				* 100L + cal.get(Calendar.DATE);
	}

	public static String toDateStr(Date date) {
		if (null == date) {
			return null;
		}

		return sdfDDMMYYYY.format(date);
	}

	public static Date toDate(String dateStr) throws ParseException {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		return sdfDDMMYYYY.parse(dateStr);
	}

	public static Date getDateOffset(Date d, int offset) {
		return new Date(d.getTime() + offset * 24 * 60 * 60 * 1000L);
	}

	public static Date getDateOffset(Date d, int offset, TimeUnit unit) {
		return new Date(d.getTime() + unit.toMillis(offset));
	}

	public static String toDateStr(Date date, String format) {
		if (null == date) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(date);
	}

	public static Date toDate(String dateStr, String format)
			throws ParseException {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.parse(dateStr);
	}

	public static String getCurrDateStr() {
		return sdfYYYYMMDD.format(new Date());
	}

	public static String getFormattedDateStr(Integer payyear, Integer paymonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, payyear);
		cal.set(Calendar.MONTH, paymonth - 1);
		return sdfMMMMMYYYY.format(cal.getTime());
	}
}
