package kr.ac.jj.survey.infrastructure.util;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.jj.survey.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;

/**
 * 기본 Util
 */
public class BaseUtil {
    private static final Logger log = LoggerFactory.getLogger(BaseUtil.class);

    /**
     * 진행상태 키 값 반환
     *
     * @return
     */
    public static String getProgressKey() {
        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request == null) {
            return null;
        }

        return request.getParameter("_SERVICE_PROGRESS_KEY_");
    }

    /**
     * 임시 디렉토리 반환
     *
     * @return
     */
    public static File getTempDirectory() {
        File tempDir = FileUtil.getResourceFile("classpath:temp");

        tempDir.mkdirs();

        return tempDir;
    }

    /**
     * 임시 파일 반환
     *
     * @param type
     * @param ext
     * @return
     */
    public static File getTempFile(String type, String ext) {
        String fileName = DateFormatUtils.format(new Date(), "yyyyMMdd-HHmmssSSS") + "-" + UUID.randomUUID().toString();
        String child = (StringUtils.isEmpty(type) ? "" : type + ".") + fileName
                + (StringUtils.isEmpty(ext) ? "" : "." + ext);
        File file = new File(BaseUtil.getTempDirectory(), child);

        file.getParentFile().mkdirs();

        FileUtil.deleteFile(file);

        log.info("임시파일 생성 => " + file.getAbsolutePath());

        return file;
    }

    /**
     * 임시 파일 반환
     *
     * @return
     */
    public static File getTempFile() {
        return getTempFile("", "");
    }

    /**
     * 요청 헤더 값들 반환
     *
     * @param request
     * @return
     */
    public static String getHeaderValues(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            sb.append(name + " : " + request.getHeader(name) + "\r\n");
        }

        return sb.toString();
    }

    /**
     * 요청 파라메터 값들 반환
     *
     * @param request
     * @return
     */
    public static String getParameterValues(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();

        Enumeration<String> names = request.getParameterNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            sb.append(name + " : " + request.getParameter(name) + "\r\n");
        }

        return sb.toString();
    }
}