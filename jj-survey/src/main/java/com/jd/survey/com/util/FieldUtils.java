package com.jd.survey.com.util;

import java.lang.reflect.Field;

/**
 * extends org.springframework.security.util.FieldUtils (final class)
 */
public class FieldUtils {

    public static Field getField(Class<?> clazz, String fieldName) {
        return org.springframework.security.util.FieldUtils.getField(clazz, fieldName);
    }

    public static Object getFieldValue(Object bean, String fieldName) {
        try {
            return org.springframework.security.util.FieldUtils.getFieldValue(bean, fieldName);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProtectedFieldValue(String protectedField, Object object) {
        return org.springframework.security.util.FieldUtils.getProtectedFieldValue(protectedField, object);
    }

    public static void setProtectedFieldValue(String protectedField, Object object, Object newValue) {
        setProtectedFieldValue(protectedField, object, newValue);
    }

}
