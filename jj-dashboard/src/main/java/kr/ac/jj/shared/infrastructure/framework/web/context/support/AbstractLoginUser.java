package kr.ac.jj.shared.infrastructure.framework.web.context.support;

import java.io.Serializable;

public abstract class AbstractLoginUser implements Serializable {

    private static final long serialVersionUID = 6934162074394096201L;

    private static String sessionKey = AbstractLoginUser.class.getName();

    public static final String getSessionKey() {
        return AbstractLoginUser.sessionKey;
    }

    public static final void setSessionKey(String sessionKey) {
        AbstractLoginUser.sessionKey = sessionKey;
    }

}
