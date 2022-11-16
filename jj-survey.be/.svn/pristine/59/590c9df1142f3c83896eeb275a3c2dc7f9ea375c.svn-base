package kr.ac.jj.survey.infrastructure.logging.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.MDC;

import kr.ac.jj.survey.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;

/**
 * 로깅 Util
 */
public class LoggingUtil {
    /**
     * 로깅 타입 반환
     *
     * @return
     */
    public static String getLoggingType() {
        return MDC.get("logging-type");
    }

    /**
     * 로깅 타입 설정
     *
     * @param value
     */
    public static void setLoggingType(String value) {
        MDC.put("logging-type", value);
    }

    /**
     * 로깅 타입 제거
     */
    public static void removeLoggingType() {
        MDC.remove("logging-type");
    }

    /**
     * 시스템 로그 객체 반환
     *
     * @return
     */
    public static TbSysLog getTbSysLog() {
        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request != null) {
            try {
                return (TbSysLog) request.getAttribute(TbSysLog.class.getName());
            } catch (ClassCastException e) {
                return null;
            }
        }

        String tbSysLogString = MDC.get(TbSysLog.class.getName());

        if (tbSysLogString == null) {
            return null;
        }

        byte[] tbSysLogByte = Base64.decodeBase64(tbSysLogString);
        Object tbSysLog = SerializationUtils.deserialize(tbSysLogByte);

        return (TbSysLog) tbSysLog;
    }

    /**
     * 시스템 로그 객체 저장
     *
     * @param tbSysLog
     */
    public static void setTbSysLog(TbSysLog tbSysLog) {
        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request != null) {
            request.setAttribute(TbSysLog.class.getName(), tbSysLog);
            return;
        }

        byte[] tbSysLogByte = SerializationUtils.serialize(tbSysLog);
        String tbSysLogString = Base64.encodeBase64String(tbSysLogByte);

        MDC.put(TbSysLog.class.getName(), tbSysLogString);
    }
}
