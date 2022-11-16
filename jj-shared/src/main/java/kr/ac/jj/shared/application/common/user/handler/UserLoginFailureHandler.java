package kr.ac.jj.shared.application.common.user.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import kr.ac.jj.shared.application.common.user.exception.UsernameEmptyValueException;
import kr.ac.jj.shared.application.common.user.service.UserLoginServiceImpl;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestTypes;

public class UserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException) {
            UserLoginServiceImpl userLoginService = ApplicationContextUtil.getBean(UserLoginServiceImpl.class);
            userLoginService.updateLoginErrorCountAdd(request.getParameter("loginNm"));
            userLoginService.createLoginLog(false);
        }

        String result = null;

        if (exception instanceof UsernameEmptyValueException || exception instanceof UsernameNotFoundException) {
            result = exception.getClass().getSimpleName();
        }

        if (StringUtils.isEmpty(result)) {
            throw exception;
        }

        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.close();

        if (RequestContextUtil.getRequestType() != RequestTypes.AJAX) {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

}
