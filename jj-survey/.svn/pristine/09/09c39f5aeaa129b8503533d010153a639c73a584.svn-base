package kr.ac.jj.survey.domain.main.model.survey.cmmnrspns;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 설문_공통문항 응답
 */
public class TbSurveyCmmnRspns extends TbSurveyCmmnRspnsEntity {

    private static final long serialVersionUID = 5681236440454580593L;

    public TbSurveyCmmnRspns newId() {
        this.setCmmnRspnsId(IdGenerationUtil.createUid("TB_SURVEY_CMMN_RSPNS"));

        return this;
    }

    public String getSexdstnNm() {
        return CodeDataUtil.getCodeName("[CMMN_SEXDSTN]", this.sexdstn);
    }

    public String getAreaNm() {
        return CodeDataUtil.getCodeName("[CMMN_AREA]", this.area);
    }

    public String getGradeNm() {
        return CodeDataUtil.getCodeName("[CMMN_GRADE]", this.grade);
    }

    public String getCghmyNm() {
        return CodeDataUtil.getCodeName("[CMMN_CGHMY]", this.cghmy);
    }

    public String getStsfdg01Nm() {
        return CodeDataUtil.getCodeName("[CMMN_STSFDG]", this.stsfdg01);
    }

    public String getStsfdg02Nm() {
        return CodeDataUtil.getCodeName("[CMMN_STSFDG]", this.stsfdg02);
    }

    public String getStsfdg03Nm() {
        return CodeDataUtil.getCodeName("[CMMN_STSFDG]", this.stsfdg03);
    }

}
