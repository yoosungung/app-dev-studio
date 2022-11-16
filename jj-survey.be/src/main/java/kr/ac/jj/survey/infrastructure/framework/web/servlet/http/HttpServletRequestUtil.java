package kr.ac.jj.survey.infrastructure.framework.web.servlet.http;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;

public class HttpServletRequestUtil {
    public static HttpServletRequest getHttpServletRequest(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if ("application/json".equals(request.getContentType())) {
            return new HttpServletRequestRereadable(httpRequest);
        }

        return httpRequest;
    }

    public static String getRequestBody(HttpServletRequest request) {
        if (!"application/json".equals(request.getContentType())) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new BaseException(e);
        }

        return sb.toString();
    }
}
