package kr.ac.jj.shared.application.common.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestTypes;

public class UserAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        if (accessDeniedException instanceof MissingCsrfTokenException
                || accessDeniedException instanceof InvalidCsrfTokenException) {
            if (RequestContextUtil.getRequestType() == RequestTypes.AJAX) {
                if (request.getSession() != null) {
                    CsrfToken csrfToken = new HttpSessionCsrfTokenRepository().loadToken(request);

                    if (csrfToken != null) {
                        response.setHeader("X-Invalid-Token", Boolean.toString(true));

                        if (!BooleanUtils.toBoolean(request.getHeader(csrfToken.getHeaderName() + "-Retry"))) {
                            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
                        }
                    }
                }
            }
        }

        super.handle(request, response, accessDeniedException);
    }

}
