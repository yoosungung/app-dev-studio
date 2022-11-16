package kr.ac.jj.survey.domain.main.model.com.dept.author;

import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;

/**
 * 공통 - 부서별 권한 TO 시스템 - 권한
 */
public class TbComDeptAuthorToAuthor extends TbSysAuthor {
    private static final long serialVersionUID = 3661381950272401575L;

    private String deptId;

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
