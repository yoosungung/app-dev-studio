package kr.ac.jj.survey.domain.main.model.com.dept.author;

import kr.ac.jj.survey.domain.main.model.com.dept.TbComDept;

/**
 * 공통 - 부서별 권한 TO 공통 - 부서
 */
public class TbComDeptAuthorToDept extends TbComDept {
    private static final long serialVersionUID = 4759240982516303166L;

    private String authorId;

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
