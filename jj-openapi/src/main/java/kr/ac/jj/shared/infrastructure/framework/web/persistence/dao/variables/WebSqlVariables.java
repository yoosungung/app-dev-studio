package kr.ac.jj.shared.infrastructure.framework.web.persistence.dao.variables;

import java.util.Locale;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.variables.SqlVariables;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;

public class WebSqlVariables extends SqlVariables {

    private Object anonymousUser;
    private Object systemUser;

    public Object getAnonymousUser() {
        return this.anonymousUser;
    }

    public void setAnonymousUser(Object anonymousUser) {
        this.anonymousUser = anonymousUser;
    }

    public Object getSystemUser() {
        return this.systemUser;
    }

    public void setSystemUser(Object systemUser) {
        this.systemUser = systemUser;
    }

    public Object getLoginUser() {
        Object loginUser = SessionContextUtil.getLoginUser();

        if (loginUser != null) {
            return loginUser;
        }

        if (RequestContextUtil.getRequest() != null || this.getSystemUser() == null) {
            return this.getAnonymousUser();
        }

        return this.getSystemUser();
    }

    public Locale getUserLocale() {
        return RequestContextUtil.getLocale();
    }

    public String getUserLocaleString() {
        return this.getUserLocale().toString();
    }

    public String getCodeData(String columnName, String codeDataName) {
        return super.getCodeData(columnName, codeDataName, null, this.getUserLocale());
    }

    public String getCodeData(String columnName, String codeDataPath, String defaultName) {
        return super.getCodeData(columnName, codeDataPath, defaultName, this.getUserLocale());
    }

}
