package com.jd.survey.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 날짜를 패턴 형식의 문자로 변환
	 */
	public static String format(Date date, String pattern) {
		if(date == null || pattern == null) {
			throw new IllegalArgumentException("날짜와 패턴은 필수 항목입니다.");
		}
		
		SimpleDateFormat format = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));
		return format.format(date);
	}
	
	/**
	 * 날짜를 기본 패턴 형식의 문자로 변환
	 */
	public static String format(Date date) {
		return format(date, DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * 현재 날짜를 기본패턴 형식의 문자로 변환
	 */
	public static String format() {
		return format(new Date(), DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * 패턴 형식의 문자를 날짜로 변환
	 */
	public static Date parse(String dateStr, String pattern) {
		if(dateStr == null || pattern == null) {
			throw new IllegalArgumentException("날짜와 패턴은 필수 항목입니다.");
		}
		
		SimpleDateFormat parse = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));
		try {
			return parse.parse(dateStr);
		}
		catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 기본 패턴 형식의 문자를 날짜로 변환
	 */
	public static Date parse(String dateStr) {
		SimpleDateFormat parse = new SimpleDateFormat(DEFAULT_DATE_PATTERN, new Locale("ko", "KOREA"));
		try { return parse.parse(dateStr); } catch (ParseException e) {}
		
		parse = new SimpleDateFormat("yyyy.MM.dd", new Locale("ko", "KOREA"));
		try { return parse.parse(dateStr); } catch (ParseException e) {}
		
		parse = new SimpleDateFormat("yyyyMMdd", new Locale("ko", "KOREA"));
		try { return parse.parse(dateStr); } catch (ParseException e) { e.printStackTrace(); return null; }
	}
	
}
