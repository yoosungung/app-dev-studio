package kr.ac.jj.shared.infrastructure.framework.web.security.attribute;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;

import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.attribute.ServletAttribute;

public class ServletSecurityAttribute implements ServletAttribute {

    @Override
    public String getTokenValue() {
        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request == null) {
            return null;
        }

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        if (csrfToken == null) {
            return null;
        }

        return csrfToken.getToken();
    }

    @Override
    public <T> T getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            SecurityContext context = SessionContextUtil
                    .getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

            if (context != null) {
                authentication = context.getAuthentication();
            }
        }

        if (authentication == null) {
            return null;
        }

        @SuppressWarnings("unchecked")
        T principal = (T) authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return principal;
        }

        return null;
    }

}
