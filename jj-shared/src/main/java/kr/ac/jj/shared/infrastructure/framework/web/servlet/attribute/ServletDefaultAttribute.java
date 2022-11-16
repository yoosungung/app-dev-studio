package kr.ac.jj.shared.infrastructure.framework.web.servlet.attribute;

import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.AbstractLoginUser;

public class ServletDefaultAttribute implements ServletAttribute {

    @Override
    public String getTokenValue() {
        return null;
    }

    @Override
    public <T> T getLoginUser() {
        @SuppressWarnings("unchecked")
        T loginUser = (T) SessionContextUtil.getAttribute(AbstractLoginUser.getSessionKey());

        return loginUser;
    }

}
