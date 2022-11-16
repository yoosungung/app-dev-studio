package kr.ac.jj.shared.infrastructure.framework.web.context.request;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class RequestContextUtil {

    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes == null || !(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }

        return (ServletRequestAttributes) requestAttributes;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();

        if (servletRequestAttributes == null) {
            return null;
        }

        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();

        if (servletRequestAttributes == null) {
            return null;
        }

        return servletRequestAttributes.getResponse();
    }

    public static Locale getLocale() {
        HttpServletRequest request = getRequest();

        if (request != null) {
            return RequestContextUtils.getLocale(request);
        }

        return Locale.getDefault();
    }

    public static boolean setLocale(String locale) {
        return setLocale(org.springframework.util.StringUtils.parseLocaleString(locale));
    }

    public static boolean setLocale(Locale locale) {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return false;
        }

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if (localeResolver == null) {
            return false;
        }

        localeResolver.setLocale(request, null, locale);

        return true;
    }

    public static String getRealPath(String path) {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        ServletContext servletContext = request.getServletContext();

        if (servletContext == null) {
            return null;
        }

        return servletContext.getRealPath(path);
    }

    public static RequestTypes getRequestType() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        if ("HttpURLConnection".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            return RequestTypes.URL_CONNECTION;
        }

        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            return RequestTypes.AJAX;
        }

        return RequestTypes.ACTION;
    }

    /**
     * Cookie의 값 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String userName = RequestContextUtil.getCookie("USER_NAME");
     * </pre>
     *
     * @param name 얻으려고하는 Cookie 이름
     * @return
     */
    public static String getCookie(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (int i = 0, ii = cookies.length; i < ii; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i].getValue();
            }
        }

        return null;
    }

    /**
     * 서비스 중인 서버의 URL 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String serverURL = RequestContextUtil.getServerURL();
     * </pre>
     *
     * @return
     */
    public static String getServerURL() {
        return getServerURL(null);
    }

    /**
     * 서비스 중인 서버의 URL 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String serverURL = RequestContextUtil.getServerURL();
     * </pre>
     *
     * @param serverName 서버 이름
     * @return
     */
    public static String getServerURL(String serverName) {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        String scheme = request.getScheme();
        int serverPort = request.getServerPort();

        if (StringUtils.isEmpty(serverName)) {
            serverName = request.getServerName();
        }

        if (("http".equalsIgnoreCase(scheme) && serverPort == 80)
                || ("https".equalsIgnoreCase(scheme) && serverPort == 443)) {
            return scheme + "://" + serverName;
        }

        return scheme + "://" + serverName + ":" + serverPort;
    }

    /**
     * 서비스 중인 서버의 ContextURL 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String contextURL = RequestContextUtil.getContextURL();
     * </pre>
     *
     * @return
     */
    public static String getContextURL() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        return getServerURL() + request.getContextPath();
    }

    /**
     * 요청 페이지의 URL 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String requestURL = NCommonUtil.getRequestURL();
     * </pre>
     *
     * @return
     */
    public static String getRequestURL() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        return request.getRequestURL().toString();
    }

    /**
     * 요청 페이지의 URL 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String requestURL = NCommonUtil.getRequestURL(true);
     * </pre>
     *
     * @param withQueryString QueryString을 포함할지 여부
     * @return
     */
    public static String getRequestURL(boolean withQueryString) {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        StringBuffer requestURL = request.getRequestURL();

        if (withQueryString) {
            String queryString = request.getQueryString();

            if (StringUtils.isNotEmpty(queryString)) {
                requestURL.append("?").append(queryString);
            }
        }

        return requestURL.toString();
    }

    /**
     * 접속한 클라이언트 IP 반환.
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) String clientIP = RequestContextUtil.getClientIpAddress();
     * </pre>
     *
     * @return
     */
    public static String getClientIpAddress() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return null;
        }

        String clientIp;

        clientIp = request.getHeader("WL-Proxy-Client-IP");

        if (StringUtils.isNotEmpty(clientIp)) {
            return clientIp;
        }

        clientIp = request.getHeader("Proxy-Client-IP");

        if (StringUtils.isNotEmpty(clientIp)) {
            return clientIp;
        }

        clientIp = request.getHeader("X-Forwarded-For");

        if (StringUtils.isNotEmpty(clientIp)) {
            return clientIp;
        }

        return request.getRemoteAddr();
    }

}
