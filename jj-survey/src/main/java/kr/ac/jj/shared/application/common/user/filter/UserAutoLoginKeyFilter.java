package kr.ac.jj.shared.application.common.user.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.http.HttpServletRequestUtil;

public class UserAutoLoginKeyFilter implements Filter {

    private final Map<String, String> autoLoginKeys;

    public UserAutoLoginKeyFilter(Map<String, String> autoLoginKeys) {
        this.autoLoginKeys = autoLoginKeys;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (SessionContextUtil.getLoginUser(LoginUser.class) != null) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = HttpServletRequestUtil.getHttpServletRequest(request);
        String contextPath = httpRequest.getContextPath();
        String servletPath = httpRequest.getServletPath();

        String[] loginTypes = this.autoLoginKeys.keySet().toArray(new String[] {});
        String[] usernameKeys = this.autoLoginKeys.values().toArray(new String[] {});
        String[] usernameValues = new String[usernameKeys.length];

        for (int i = 0; i < usernameKeys.length; i++) {
            usernameValues[i] = httpRequest.getParameter(usernameKeys[i]);
        }

        String queryString = RequestContextUtil.getQueryStringRemovedParameters(usernameKeys);

        if (!StringUtils.isAllEmpty(usernameValues)) {
            HttpSession session = httpRequest.getSession(true);

            for (int i = 0; i < usernameKeys.length; i++) {
                if (StringUtils.isNotEmpty(usernameValues[i])) {
                    session.setAttribute("userLogin_loginTy", loginTypes[i]);
                    session.setAttribute("userLogin_username", usernameValues[i]);

                    break;
                }
            }

            HttpServletResponse httpResponse = (HttpServletResponse) response;

            if (StringUtils.isNotEmpty(queryString)) {
                httpResponse.sendRedirect(contextPath + servletPath + "?" + queryString);
            } else {
                httpResponse.sendRedirect(contextPath + servletPath);
            }
        } else {
            HttpSession session = httpRequest.getSession();

            if (session != null) {
                httpRequest.setAttribute("userLogin_loginTy", session.getAttribute("userLogin_loginTy"));
                httpRequest.setAttribute("userLogin_username", session.getAttribute("userLogin_username"));

                session.removeAttribute("userLogin_loginTy");
                session.removeAttribute("userLogin_username");
            }

            chain.doFilter(request, response);
        }
    }

}
