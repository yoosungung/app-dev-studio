package kr.ac.jj.survey.application.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.jj.survey.application.common.user.model.LoginUser;
import kr.ac.jj.survey.infrastructure.framework.web.context.session.SessionContextUtil;

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

    public static String getExcelCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        String value;

        switch (cell.getCellTypeEnum()) {
        case FORMULA:
            value = cell.getCellFormula();
            break;
        case NUMERIC:
            value = cell.getNumericCellValue() + "";
            break;
        case STRING:
            value = cell.getStringCellValue();
            break;
        case BOOLEAN:
            value = cell.getBooleanCellValue() + "";
            break;
        case ERROR:
            value = cell.getErrorCellValue() + "";
            break;
        case BLANK:
            value = "";
            break;
        default:
            value = cell.getStringCellValue();
            break;
        }

        if (value == null) {
            return null;
        }

        return value.trim();
    }
}
