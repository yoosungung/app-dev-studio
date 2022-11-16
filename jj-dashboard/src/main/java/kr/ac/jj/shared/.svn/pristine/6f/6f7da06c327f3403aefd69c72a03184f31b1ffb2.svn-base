package kr.ac.jj.shared.domain.main.model.com.dty.author;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 * 공통 - 직무별 권한 TO 시스템 - 권한
 */
public class TbComDtyAuthorToAuthor extends TbSysAuthor {

    private static final long serialVersionUID = 97125505739735530L;

    private String dtyId;
    private Boolean dtyAuthorYn;

    public String getDtyId() {
        return this.dtyId;
    }

    public void setDtyId(String dtyId) {
        this.dtyId = dtyId;
    }

    public Boolean getDtyAuthorYn() {
        return this.dtyAuthorYn;
    }

    public void setDtyAuthorYn(Boolean dtyAuthorYn) {
        this.dtyAuthorYn = dtyAuthorYn;
    }

    public String getDtyAuthorYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.dtyAuthorYn);
    }

}
