package kr.ac.jj.survey.domain.main.model.com.dept;

import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 부서
 */
public class TbComDept extends TbComDeptEntity {
    private static final long serialVersionUID = 113649357787975034L;

    public TbComDept newId() {
        this.setDeptId(IdGenerationUtil.createUid("TB_COM_DEPT"));

        return this;
    }
}
