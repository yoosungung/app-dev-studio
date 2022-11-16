package kr.ac.jj.shared.domain.main.model.com.dept;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 부서
 */
public class TbComDept extends TbComDeptEntity {

    private static final long serialVersionUID = 113649357787975034L;

    public enum DeptSeEnums {
        EMPLOYEE, STUDENT
    }

    private String upperDeptCode;
    private String upperDeptNm;

    public TbComDept newId() {
        this.setDeptId(IdGenerationUtil.createUid("TB_COM_DEPT"));

        return this;
    }

    @JsonIgnore
    public DeptSeEnums getDeptSeEnum() {
        return DeptSeEnums.valueOf(this.deptSe);
    }

    @JsonIgnore
    public void setDeptSeEnum(DeptSeEnums deptSe) {
        this.deptSe = deptSe.name();
    }

    public String getUpperDeptCode() {
        return this.upperDeptCode;
    }

    public void setUpperDeptCode(String upperDeptCode) {
        this.upperDeptCode = upperDeptCode;
    }

    public String getUpperDeptNm() {
        return this.upperDeptNm;
    }

    public void setUpperDeptNm(String upperDeptNm) {
        this.upperDeptNm = upperDeptNm;
    }

    public String getUseYnNm() {
        return CodeDataUtil.getCodeName("/common/useYn", this.getUseYn());
    }

}
