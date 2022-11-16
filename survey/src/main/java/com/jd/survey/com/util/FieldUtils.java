package com.jd.survey.com.util;

import java.lang.reflect.Field;

/**
 * extends org.springframework.security.util.FieldUtils (final class)
 */
public class FieldUtils {

	public static Field getField(Class<?> clazz, String fieldName) throws IllegalStateException {
		return org.springframework.security.util.FieldUtils.getField(clazz, fieldName);
	}
	
	public static Object getFieldValue(Object bean, String fieldName) throws IllegalAccessException {
		return org.springframework.security.util.FieldUtils.getFieldValue(bean, fieldName);
	}
	
	public static Object getProtectedFieldValue(String protectedField, Object object) {
		return org.springframework.security.util.FieldUtils.getProtectedFieldValue(protectedField, object);
	}
	
	public static void setProtectedFieldValue(String protectedField, Object object, Object newValue) {
		setProtectedFieldValue(protectedField, object, newValue);
	}
}
