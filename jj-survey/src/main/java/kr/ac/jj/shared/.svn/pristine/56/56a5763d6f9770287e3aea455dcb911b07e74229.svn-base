package kr.ac.jj.shared.infrastructure.logging.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import kr.ac.jj.shared.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.http.HttpServletRequestUtil;
import kr.ac.jj.shared.infrastructure.logging.service.LoggingServiceImpl;
import kr.ac.jj.shared.infrastructure.user.AnonymousUser;
import kr.ac.jj.shared.infrastructure.user.BaseLoginUser;

/**
 * 로깅 Filter
 */
public class LoggingFilter implements Filter {

    @SuppressWarnings("unused")
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Object loginUser = SessionContextUtil.getLoginUser();
        String userId;

        if (loginUser == null) {
            userId = AnonymousUser.USER_ID;
        } else if (loginUser instanceof BaseLoginUser) {
            BaseLoginUser baseLoginUser = (BaseLoginUser) loginUser;
            userId = baseLoginUser.getUserId();
        } else {
            userId = "UNKNOWN";
        }

        MDC.put("userInfo", userId + "-" + RequestContextUtil.getClientIpAddress());

        if (!this.checkUrlPattern((HttpServletRequest) request)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = HttpServletRequestUtil.getHttpServletRequest(request);

        LoggingServiceImpl loggingService = ApplicationContextUtil.getBean(LoggingServiceImpl.class);
        TbSysLog tbSysLog = loggingService.create(HttpServletRequestUtil.getRequestBody(httpRequest));

        chain.doFilter(httpRequest, response);

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (tbSysLog.getRspnsSttusCode() == null) {
            loggingService.updateRspnsSttusCode(httpResponse.getStatus());
        }
    }

    @Override
    public void destroy() {
    }

    private boolean checkUrlPattern(HttpServletRequest request) {
        String servletPath = request.getServletPath();

        if (StringUtils.isEmpty(servletPath)) {
            return false;
        }

        if (servletPath.equals("/error") //
                || servletPath.equals("/infrastructure/progress/ServletProgress/read.do") //
                || servletPath.equals("/infrastructure/message/Message/read.do") //
                || servletPath.equals("/infrastructure/codedata/CodeData/read") //
                || servletPath.equals("/common/ui/Ui/viewInitialize.do") //
                || servletPath.equals("/common/menu/MenuLoad/readList") //
        ) {
            return false;
        }

        if (servletPath.endsWith(".do")) {
            return true;
        }

        if (servletPath.contains(".")) {
            return false;
        }

        return true;
    }

}
