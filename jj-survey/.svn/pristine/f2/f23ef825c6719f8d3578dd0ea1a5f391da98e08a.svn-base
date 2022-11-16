package kr.ac.jj.survey.domain.main.model.survey.group;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 설문_그룹
 */
public class TbSurveyGroup extends TbSurveyGroupEntity {

    private static final long serialVersionUID = -6671692863304214382L;

    private String registDeptNm;

    public TbSurveyGroup newId() {
        this.setSurveyGroupId(IdGenerationUtil.createUid("TB_SURVEY_GROUP"));

        return this;
    }

    public String getRegistDeptNm() {
        return this.registDeptNm;
    }

    public void setRegistDeptNm(String registDeptNm) {
        this.registDeptNm = registDeptNm;
    }

    public String getCnrsYnNm() {
        return CodeDataUtil.getCodeName("[CNRS_YN]", this.cnrsYn);
    }

}
