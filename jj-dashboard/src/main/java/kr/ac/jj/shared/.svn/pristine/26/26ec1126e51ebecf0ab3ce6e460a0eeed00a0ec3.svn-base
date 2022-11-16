package kr.ac.jj.shared.infrastructure.framework.core.support.context;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        return applicationContext.getBean(beanName, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        return applicationContext.getBean(requiredType);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConfigBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        T bean;

        try {
            bean = (T) applicationContext.getBean(beanName);
        } catch (NoSuchBeanDefinitionException e) {
            @SuppressWarnings("resource")
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            bean = (T) ctx.getBean(beanName);
        }

        return bean;
    }

    public static <T> T getConfigBean(String beanName, Class<T> requiredType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        T bean;

        try {
            bean = applicationContext.getBean(beanName, requiredType);
        } catch (NoSuchBeanDefinitionException e) {
            @SuppressWarnings("resource")
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(requiredType);
            bean = ctx.getBean(beanName, requiredType);
        }

        return bean;
    }

    public static <T> T getConfigBean(Class<T> requiredType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            return null;
        }

        T bean;

        try {
            bean = applicationContext.getBean(requiredType);
        } catch (NoSuchBeanDefinitionException e) {
            @SuppressWarnings("resource")
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(requiredType);
            bean = ctx.getBean(requiredType);
        }

        return bean;
    }

}
