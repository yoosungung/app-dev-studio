package kr.ac.jj.survey.infrastructure.framework.web.context.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.AbstractLoginUser;
import kr.ac.jj.survey.infrastructure.framework.web.servlet.util.ServletUtil;

public class SessionContextUtil {
    public static HttpSession getSession() {
        return getSession(false);
    }

    public static HttpSession getSession(boolean create) {
        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request == null) {
            return null;
        }

        return request.getSession(create);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(String name) {
        HttpSession session = getSession();

        if (session == null) {
            return null;
        }

        return (T) session.getAttribute(name);
    }

    public static boolean setAttribute(String name, Object value) {
        HttpSession session = getSession(true);

        if (session == null) {
            return false;
        }

        session.setAttribute(name, value);

        return true;
    }

    public static boolean removeAttribute(String name) {
        HttpSession session = getSession();

        if (session == null) {
            return false;
        }

        session.removeAttribute(name);

        return true;
    }

    public static String getRealPath(String path) {
        HttpSession session = getSession();

        if (session == null) {
            return null;
        }

        ServletContext servletContext = session.getServletContext();

        if (servletContext == null) {
            return null;
        }

        return servletContext.getRealPath(path);
    }

    public static <T> T getLoginUser(Class<T> type) {
        return getLoginUser();
    }

    public static <T> T getLoginUser() {
        return ServletUtil.getAttribute().getLoginUser();
    }

    public static void setLoginUser(AbstractLoginUser loginUser) {
        setAttribute(AbstractLoginUser.getSessionKey(), loginUser);
    }

    public static void removeLoginUser() {
        removeAttribute(AbstractLoginUser.getSessionKey());
    }
}
