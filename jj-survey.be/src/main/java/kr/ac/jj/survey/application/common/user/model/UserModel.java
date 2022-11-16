package kr.ac.jj.survey.application.common.user.model;

import java.util.Locale;

import org.springframework.security.web.csrf.CsrfToken;

import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.session.SessionContextUtil;

public class UserModel {
    private LoginUser loginUser;
    private Locale userLocale;
    private CsrfToken csrfToken;

    public UserModel() {
        this.setLoginUser(SessionContextUtil.getLoginUser());
        this.setUserLocale(RequestContextUtil.getLocale());
    }

    public LoginUser getLoginUser() {
        return this.loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public Locale getUserLocale() {
        return this.userLocale;
    }

    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }

    public CsrfToken getCsrfToken() {
        return this.csrfToken;
    }

    public void setCsrfToken(CsrfToken csrfToken) {
        this.csrfToken = csrfToken;
    }
}
