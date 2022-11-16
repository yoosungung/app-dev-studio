package kr.ac.jj.shared.application.common.user.model;

import java.util.Locale;

import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;

public class UserModel {

    private LoginUser loginUser;
    private Locale userLocale;

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

}
