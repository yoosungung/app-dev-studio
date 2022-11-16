package kr.ac.jj.shared.domain.main.model.com.person.dept;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 - 사람별 부서 Entity
 */
abstract class TbComPersonDeptEntity extends MainEntity {

    private static final long serialVersionUID = 4271910368790933792L;

    protected String personId;
    protected String deptId;

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

}
