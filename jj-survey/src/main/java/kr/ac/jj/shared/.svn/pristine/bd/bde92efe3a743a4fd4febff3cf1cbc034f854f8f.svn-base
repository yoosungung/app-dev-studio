package kr.ac.jj.shared.domain.main.model.com.dept.author;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 * 공통 - 부서별 권한 TO 시스템 - 권한
 */
public class TbComDeptAuthorToAuthor extends TbSysAuthor {

    private static final long serialVersionUID = 3661381950272401575L;

    private Boolean deptAuthorYn;

    public Boolean getDeptAuthorYn() {
        return this.deptAuthorYn;
    }

    public void setDeptAuthorYn(Boolean deptAuthorYn) {
        this.deptAuthorYn = deptAuthorYn;
    }

    public String getDeptAuthorYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.deptAuthorYn);
    }

}
