package kr.ac.jj.shared.domain.main.model.sys.resrce.author;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 * 시스템 - 리소스별 권한 TO 시스템 - 권한
 */
public class TbSysResrceAuthorToAuthor extends TbSysAuthor {

    private static final long serialVersionUID = 5062246819228370041L;

    private Boolean resrceAuthorYn;

    public Boolean getResrceAuthorYn() {
        return this.resrceAuthorYn;
    }

    public void setResrceAuthorYn(Boolean resrceAuthorYn) {
        this.resrceAuthorYn = resrceAuthorYn;
    }

    public String getResrceAuthorYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.resrceAuthorYn);
    }

}
