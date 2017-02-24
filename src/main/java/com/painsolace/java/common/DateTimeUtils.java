package com.painsolace.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	private static Date date = new Date();

	public final static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm");
	public final static SimpleDateFormat HHMMSS = new SimpleDateFormat(
			"HH:mm:ss");

	public final static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public final static SimpleDateFormat dateTimeFormatNotSecond = new SimpleDateFormat(
			"yyyy-MM-dd HHmm");

	public final static SimpleDateFormat dateTimeFormatNotSecond_2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public final static SimpleDateFormat timeFormatHHmm = new SimpleDateFormat(
			"HHmm");

	public final static SimpleDateFormat timeFormatHH = new SimpleDateFormat(
			"HH");

	public final static SimpleDateFormat mmdd = new SimpleDateFormat("MMdd");

	public final static String fomatDate(Object obj, SimpleDateFormat format) {
		try {
			if (obj == null)
				return null;

			if (format == null)
				format = dateTimeFormat;

			if (obj instanceof String) {
				obj = dateTimeFormat.parse(obj.toString());
				return format.format(obj);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public static String formatDateToString(Object date) {
		if (date == null)
			return "";
		return timeFormatHHmm.format((Date) date);
	}

	public static String formatString(Date date) {
		if (date != null) {
			return timeFormatHHmm.format(date);
		}
		return null;
	}

	public static int getNextDayOfweek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(date.getTime() + 24 * 60 * 60
				* 1000));
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
			return 7;
		else
			return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static String getNextDay() throws ParseException {
		String operationtime = dateFormat.format(new Date(date
				.getTime()
				+ 24 * 60 * 60 * 1000));
		return operationtime;
	}

	public static String getToday() {
		String operationtime = dateFormat.format(new Date(date
				.getTime()));
		return operationtime;
	}

	public static String getTodayNotSecond() {
		return dateTimeFormatNotSecond.format(date);
	}

	public static Date getNowTime() {
		return new Date();
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static String validateDate(Date date) {
		return date == null ? null : DateTimeUtils.dateFormat.format(date);
	}

	public static String validateTime(Object date) {
		// return date == null ? null : DateTimeUtils.timeFormat.format(date);
		Date temp = null;
		if (date == null)
			return null;
		if (date instanceof Date)
			temp = (Date) date;
		return DateTimeUtils.timeFormat.format(temp);
	}

	public static String validateDateTime(Object date) {
		Date temp = null;
		if (date == null)
			return null;
		if (date instanceof Date)
			temp = (Date) date;
		return DateTimeUtils.dateTimeFormat.format(temp);

	}

	public static String validateDateTimeNotSecond(Date date) {
		return date == null ? null : DateTimeUtils.dateTimeFormatNotSecond
				.format(date);
	}

	public static Date validateDate(String dateStr) {
		try {
			return dateStr == null || dateStr.equals("") ? null
					: DateTimeUtils.dateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String validateDate(Object date) {
		if (date == null)
			return null;

		Date temp = null;
		try {
			if (date instanceof String) {
				temp = dateFormat.parse(date.toString());
			} else if (date instanceof Date) {
				temp = (Date) date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateTimeUtils.dateFormat.format(temp);
	}

	public static Date validateTime(String dateStr) {
		try {
			return dateStr == null || dateStr.equals("") ? null
					: DateTimeUtils.timeFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date validateDateTime(String dateStr) {
		try {
			return dateStr == null || dateStr.equals("") ? null
					: DateTimeUtils.dateTimeFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date validateDateTimeNotSecond(String dateStr) {
		try {
			return date == null ? null : DateTimeUtils.dateTimeFormatNotSecond
					.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日期相间，返回相差天数
	 * @param subtrahend 被减日期
	 * @param minuend  减数日期
	 * @return (+x)格式的字符串
	 */
	public static String minusDay(Date subtrahend,Date minuend)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String re = "";
		if(subtrahend == null || minuend == null)
		{
			return re;
		}
		try {
			subtrahend = sdf.parse(sdf.format(subtrahend));
			minuend = sdf.parse(sdf.format(minuend));
			Calendar cal = Calendar.getInstance();
			cal.setTime(minuend);
			long minuendl = cal.getTimeInMillis();
			cal.setTime(subtrahend);
			long subtrahendl = cal.getTimeInMillis();
			int result = Integer.parseInt(String.valueOf((subtrahendl - minuendl) / (1000*3600*24)));
			if(result != 0)
			{
				re+="(";
				if(result > 0)
				{
					re+="+";
				}
				re+=result+")";
			}
		}
		catch (Exception ex){
			System.out.println("日期转换错误: {date1 : "+subtrahend+"  , date2: "+minuend+"}");
		}
		return re;
	}
}