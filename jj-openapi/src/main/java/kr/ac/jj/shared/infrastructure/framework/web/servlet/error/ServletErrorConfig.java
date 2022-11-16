package kr.ac.jj.shared.infrastructure.framework.web.servlet.error;

import java.util.HashMap;
import java.util.Map;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.SysException;

public class ServletErrorConfig {

    private final Map<Class<?>, ServletErrorInfo> servletErrorInfoMap;

    private final ServletErrorInfo visableBizErrorInfo;

    public ServletErrorConfig() {
        this.servletErrorInfoMap = new HashMap<Class<?>, ServletErrorInfo>();
        this.visableBizErrorInfo = new ServletErrorInfo(true, "biz");

        this.setServletErrorInfo(BaseException.class, false);
        this.setServletErrorInfo(SysException.class, true, "sys");
        this.setServletErrorInfo(BizException.class, true, "biz");
    }

    public ServletErrorInfo getVisableBizErrorInfo() {
        return this.visableBizErrorInfo;
    }

    public void setServletErrorInfo(Class<?> throwableClass, boolean visible, String type, int number) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(visible, type, number));
    }

    public void setServletErrorInfo(Class<?> throwableClass, boolean visible, String type) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(visible, type));
    }

    public void setServletErrorInfo(Class<?> throwableClass, boolean visible, int number) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(visible, number));
    }

    public void setServletErrorInfo(Class<?> throwableClass, String type, int number) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(type, number));
    }

    public void setServletErrorInfo(Class<?> throwableClass, boolean visible) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(visible));
    }

    public void setServletErrorInfo(Class<?> throwableClass, String type) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(type));
    }

    public void setServletErrorInfo(Class<?> throwableClass, int number) {
        this.setServletErrorInfo(throwableClass, new ServletErrorInfo(number));
    }

    public void setServletErrorInfo(Class<?> throwableClass, ServletErrorInfo servletErrorInfo) {
        this.servletErrorInfoMap.put(throwableClass, servletErrorInfo);
    }

    public ServletErrorInfo getServletErrorInfo(Class<?> throwableClass) {
        if (throwableClass == null) {
            return null;
        }

        if (this.servletErrorInfoMap.containsKey(throwableClass)) {
            return this.servletErrorInfoMap.get(throwableClass);
        }

        return this.getServletErrorInfo(throwableClass.getSuperclass());
    }

}
