package kr.ac.jj.shared.domain.main.model.com.dept;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 - 부서 Entity
 */
abstract class TbComDeptEntity extends MainEntity {

    private static final long serialVersionUID = -8086727468924245653L;

    protected String deptId;
    protected String upperDeptId;
    protected Integer deptLevel;
    protected String deptCode;
    protected String deptNm;
    protected Boolean useYn;

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUpperDeptId() {
        return this.upperDeptId;
    }

    public void setUpperDeptId(String upperDeptId) {
        this.upperDeptId = upperDeptId;
    }

    public Integer getDeptLevel() {
        return this.deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptNm() {
        return this.deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public Boolean getUseYn() {
        return this.useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }

}
