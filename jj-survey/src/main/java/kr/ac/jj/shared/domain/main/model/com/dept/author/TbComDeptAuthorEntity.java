package kr.ac.jj.shared.domain.main.model.com.dept.author;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 - 부서별 권한 Entity
 */
abstract class TbComDeptAuthorEntity extends MainEntity {

    private static final long serialVersionUID = 4164327062412213228L;

    protected String deptId;
    protected String authorId;

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}
