package kr.ac.jj.shared.application.common.user.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import kr.ac.jj.shared.application.common.user.service.UserLoginServiceImpl;
import kr.ac.jj.shared.application.common.user.service.UserLoginSuccessAfterService;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestTypes;

public class UserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(UserLoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserLoginServiceImpl userLoginService = ApplicationContextUtil.getBean(UserLoginServiceImpl.class);
        userLoginService.updateLoginErrorCountToZero(request.getParameter("loginNm"));
        userLoginService.createLoginLog(true);

        if ("SSO".equals(request.getHeader("loginTy"))) {
            PrintWriter writer = response.getWriter();
            writer.print("OK");
            writer.close();
        }

        if (RequestContextUtil.getRequestType() != RequestTypes.AJAX) {
            super.onAuthenticationSuccess(request, response, authentication);
        }

        HttpSession session = request.getSession();

        if (session == null) {
            return;
        }

        String intervalUnit = "minute";
        int intervalValue = 30;

        if ("hour".equalsIgnoreCase(intervalUnit)) {
            session.setMaxInactiveInterval(intervalValue * 60 * 60);
        } else if ("minute".equalsIgnoreCase(intervalUnit)) {
            session.setMaxInactiveInterval(intervalValue * 60);
        } else {
            session.setMaxInactiveInterval(intervalValue);
        }

        try {
            UserLoginSuccessAfterService userLoginSuccessAfterService = ApplicationContextUtil
                    .getBean(UserLoginSuccessAfterService.class);
            userLoginSuccessAfterService.execute();
        } catch (NoSuchBeanDefinitionException e) {
            log.trace(ExceptionUtils.getRootCauseMessage(e));
        }
    }

}
