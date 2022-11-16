package kr.ac.jj.survey.domain.main.model.survey.tmplat;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_템플릿 Entity
 */
abstract class TbSurveyTmplatEntity extends MainEntity {

    private static final long serialVersionUID = -7145802233097863761L;

    protected String surveyTmplatId;
    protected Long surveyDefinitionId;
    protected String surveyRealmId;
    protected String tmplatSttus;
    protected String qestnrGdcc;
    protected String qestnrEndc;
    protected String qestnrBcrnColor;
    protected String registPsnId;
    protected Date registDt;

    public String getSurveyTmplatId() {
        return this.surveyTmplatId;
    }

    public void setSurveyTmplatId(String surveyTmplatId) {
        this.surveyTmplatId = surveyTmplatId;
    }

    public Long getSurveyDefinitionId() {
        return this.surveyDefinitionId;
    }

    public void setSurveyDefinitionId(Long surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public String getSurveyRealmId() {
        return this.surveyRealmId;
    }

    public void setSurveyRealmId(String surveyRealmId) {
        this.surveyRealmId = surveyRealmId;
    }

    public String getTmplatSttus() {
        return this.tmplatSttus;
    }

    public void setTmplatSttus(String tmplatSttus) {
        this.tmplatSttus = tmplatSttus;
    }

    public String getQestnrGdcc() {
        return this.qestnrGdcc;
    }

    public void setQestnrGdcc(String qestnrGdcc) {
        this.qestnrGdcc = qestnrGdcc;
    }

    public String getQestnrEndc() {
        return this.qestnrEndc;
    }

    public void setQestnrEndc(String qestnrEndc) {
        this.qestnrEndc = qestnrEndc;
    }

    public String getQestnrBcrnColor() {
        return this.qestnrBcrnColor;
    }

    public void setQestnrBcrnColor(String qestnrBcrnColor) {
        this.qestnrBcrnColor = qestnrBcrnColor;
    }

    public String getRegistPsnId() {
        return this.registPsnId;
    }

    public void setRegistPsnId(String registPsnId) {
        this.registPsnId = registPsnId;
    }

    public Date getRegistDt() {
        return this.registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

}
