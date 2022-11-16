package kr.ac.jj.shared.application.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.config.props.SharedConfigProperties.Login.Admin;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;

/**
 * 공통 Util
 */
public class CommonUtil {

    /**
     * 로그인 사용자 반환
     *
     * @return
     */
    public static LoginUser getLoginUser() {
        return SessionContextUtil.getLoginUser(LoginUser.class);
    }

    /**
     * 로그인 사용자 사람 ID 체크
     *
     * @return
     */
    public static boolean checkLoginPersonId(String personId) {
        return StringUtils.isNotEmpty(personId) && personId.equals(getLoginUser().getPersonId());
    }

    /**
     * 로그인 사용자 ID 체크
     *
     * @return
     */
    public static boolean checkLoginUserId(String userId) {
        return StringUtils.isNotEmpty(userId) && userId.equals(getLoginUser().getUserId());
    }

    /**
     * 로그인 화면 파라미터 값들 반환
     *
     * @param request
     * @param response
     * @param name
     * @return
     */
    public static String[] getLoginParameters(HttpServletRequest request, HttpServletResponse response, String name) {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            return savedRequest.getParameterValues(name);
        }

        if (request.getParameter(name) != null) {
            return new String[] { request.getParameter(name) };
        }

        return null;
    }

    /**
     * 로그인 화면 파라미터 값 반환
     *
     * @param request
     * @param response
     * @param name
     * @return
     */
    public static String getLoginParameter(HttpServletRequest request, HttpServletResponse response, String name) {
        String[] values = getLoginParameters(request, response, name);

        if (values != null && values.length > 0) {
            return values[0];
        }

        return null;
    }

    /**
     * 관리자 로그인 가능 여부 반환
     *
     * @return
     */
    public static boolean isAdminLoginAvail() {
        return isAdminLoginAvail(null);
    }

    /**
     * 관리자 로그인 가능 여부 반환
     *
     * @param adminLoginKey
     * @return
     */
    public static boolean isAdminLoginAvail(String adminLoginKey) {
        SharedConfigProperties sharedConfig = ApplicationContextUtil.getBean(SharedConfigProperties.class);

        if (sharedConfig.getLogin() == null) {
            return false;
        }

        Admin admin = sharedConfig.getLogin().getAdmin();

        if (admin == null) {
            return false;
        }

        if (!admin.isEnable()) {
            return false;
        }

        if (adminLoginKey != null && !StringUtils.equals(adminLoginKey, admin.getKey())) {
            return false;
        }

        String clientIpAddress = RequestContextUtil.getClientIpAddress();

        for (String ipAddress : admin.getIpList()) {
            if (StringUtils.equals(ipAddress, "*")) {
                return true;
            }

            if (StringUtils.equals(ipAddress, clientIpAddress)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 서버 IP 주소 반환
     *
     * @return
     */
    public static String getServerIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Object JSON 변환 문자열 반환
     *
     * @param value
     * @return
     */
    public static String getObjectMapperAsString(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SQL 문장 체크
     *
     * @param sqlSentence
     */
    public static void checkSqlSentence(String sqlSentence) {
        if (StringUtils.isEmpty(sqlSentence)) {
            return;
        }

        if (StringUtils.contains(sqlSentence, ";")) {
            try {
                throw new RuntimeException("올바르지 않은 SQL 문장입니다.");
            } catch (RuntimeException e) {
                throw e;
            }
        }

        if (!StringUtils.startsWithIgnoreCase(sqlSentence.trim(), "SELECT")) {
            try {
                throw new RuntimeException("올바르지 않은 SQL 문장입니다.");
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }

    /**
     * URL에 "?"가 포함되어 있는지에 따라, 파라미터 문자열을 추가하여 반환
     *
     * @param url
     * @param parameter
     * @return
     */
    public static String addUrlParameter(String url, String parameter) {
        return url + (StringUtils.contains(url, "?") ? "&" : "?") + parameter;
    }

    /**
     * 특정 날짜의 요일 반환
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekName(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayNum) {
        case 1:
            return "일";
        case 2:
            return "월";
        case 3:
            return "화";
        case 4:
            return "수";
        case 5:
            return "목";
        case 6:
            return "금";
        case 7:
            return "토";
        default:
            return null;
        }
    }

    /**
     * 접속 디바이스 반환
     *
     * @return
     */
    public static String getConnectionDevice() {
        return getConnectionDevice(RequestContextUtil.getRequest());
    }

    /**
     * 접속 디바이스 반환
     *
     * @param request
     * @return
     */
    public static String getConnectionDevice(HttpServletRequest request) {
        Device currentDevice = DeviceUtils.getCurrentDevice(request);

        if (currentDevice.isTablet()) {
            return "TABLET";
        }

        if (currentDevice.isMobile()) {
            return "MOBILE";
        }

        return "NORMAL";
    }

}
