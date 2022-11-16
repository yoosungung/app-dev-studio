package kr.ac.jj.shared.domain.main.model.sys.user.author;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 * 시스템 - 사용자별 권한 TO 시스템 - 권한
 */
public class TbSysUserAuthorToAuthor extends TbSysAuthor {

    private static final long serialVersionUID = 3792006528554900783L;

    private Boolean userAuthorYn;

    public Boolean getUserAuthorYn() {
        return this.userAuthorYn;
    }

    public void setUserAuthorYn(Boolean userAuthorYn) {
        this.userAuthorYn = userAuthorYn;
    }

    public String getUserAuthorYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.userAuthorYn);
    }

}
