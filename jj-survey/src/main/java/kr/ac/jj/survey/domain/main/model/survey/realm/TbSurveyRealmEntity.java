package kr.ac.jj.survey.domain.main.model.survey.realm;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_분야 Entity
 */
abstract class TbSurveyRealmEntity extends MainEntity {

    private static final long serialVersionUID = 3253735780834745569L;

    protected String surveyRealmId;
    protected String surveyRealmNm;
    protected String surveyRealmDc;

    public String getSurveyRealmId() {
        return this.surveyRealmId;
    }

    public void setSurveyRealmId(String surveyRealmId) {
        this.surveyRealmId = surveyRealmId;
    }

    public String getSurveyRealmNm() {
        return this.surveyRealmNm;
    }

    public void setSurveyRealmNm(String surveyRealmNm) {
        this.surveyRealmNm = surveyRealmNm;
    }

    public String getSurveyRealmDc() {
        return this.surveyRealmDc;
    }

    public void setSurveyRealmDc(String surveyRealmDc) {
        this.surveyRealmDc = surveyRealmDc;
    }

}
