package kr.ac.jj.shared.domain.main.model.sys.menu.author;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 * 시스템 - 메뉴별 권한 TO 시스템 - 권한
 */
public class TbSysMenuAuthorToAuthor extends TbSysAuthor {

    private static final long serialVersionUID = -5567312591415328234L;

    private Boolean menuAuthorYn;

    public Boolean getMenuAuthorYn() {
        return this.menuAuthorYn;
    }

    public void setMenuAuthorYn(Boolean menuAuthorYn) {
        this.menuAuthorYn = menuAuthorYn;
    }

    public String getMenuAuthorYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.menuAuthorYn);
    }

}
