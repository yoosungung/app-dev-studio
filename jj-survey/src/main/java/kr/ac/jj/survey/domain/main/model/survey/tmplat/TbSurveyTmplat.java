package kr.ac.jj.survey.domain.main.model.survey.tmplat;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 설문_템플릿
 */
public class TbSurveyTmplat extends TbSurveyTmplatEntity {

    private static final long serialVersionUID = 4321722264034152362L;

    private String surveyRealmNm;

    public TbSurveyTmplat newId() {
        this.setSurveyTmplatId(IdGenerationUtil.createUid("TB_SURVEY_TMPLAT"));

        return this;
    }

    public String getSurveyRealmNm() {
        return this.surveyRealmNm;
    }

    public void setSurveyRealmNm(String surveyRealmNm) {
        this.surveyRealmNm = surveyRealmNm;
    }

    public String getTmplatSttusNm() {
        return CodeDataUtil.getCodeName("[TMPLAT_STTUS]", this.tmplatSttus);
    }

}
