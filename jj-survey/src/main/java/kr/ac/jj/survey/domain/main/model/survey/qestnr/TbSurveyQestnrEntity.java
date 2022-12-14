package kr.ac.jj.survey.domain.main.model.survey.qestnr;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_설문지 Entity
 */
abstract class TbSurveyQestnrEntity extends MainEntity {

    private static final long serialVersionUID = -8195845998282934057L;

    protected String surveyQestnrId;
    protected Long surveyDefinitionId;
    protected String surveyRealmId;
    protected String cnrsYn;
    protected String qestnrGdcc;
    protected String qestnrEndc;
    protected String qestnrBcrnColor;
    protected String registPsnId;
    protected Date registDt;
    protected String registDeptId;
    protected Boolean sndngYn;
    protected String emailSurveyUrl;
    protected String emailShrtenUrl;
    protected String smsSurveyUrl;
    protected String smsShrtenUrl;
    protected Boolean emailSndngYn;
    protected Boolean smsSndngYn;

    public String getSurveyQestnrId() {
        return this.surveyQestnrId;
    }

    public void setSurveyQestnrId(String surveyQestnrId) {
        this.surveyQestnrId = surveyQestnrId;
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

    public String getCnrsYn() {
        return this.cnrsYn;
    }

    public void setCnrsYn(String cnrsYn) {
        this.cnrsYn = cnrsYn;
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

    public String getRegistDeptId() {
        return this.registDeptId;
    }

    public void setRegistDeptId(String registDeptId) {
        this.registDeptId = registDeptId;
    }

    public Boolean getSndngYn() {
        return this.sndngYn;
    }

    public void setSndngYn(Boolean sndngYn) {
        this.sndngYn = sndngYn;
    }

    public String getEmailSurveyUrl() {
        return this.emailSurveyUrl;
    }

    public void setEmailSurveyUrl(String emailSurveyUrl) {
        this.emailSurveyUrl = emailSurveyUrl;
    }

    public String getEmailShrtenUrl() {
        return this.emailShrtenUrl;
    }

    public void setEmailShrtenUrl(String emailShrtenUrl) {
        this.emailShrtenUrl = emailShrtenUrl;
    }

    public String getSmsSurveyUrl() {
        return this.smsSurveyUrl;
    }

    public void setSmsSurveyUrl(String smsSurveyUrl) {
        this.smsSurveyUrl = smsSurveyUrl;
    }

    public String getSmsShrtenUrl() {
        return this.smsShrtenUrl;
    }

    public void setSmsShrtenUrl(String smsShrtenUrl) {
        this.smsShrtenUrl = smsShrtenUrl;
    }

    public Boolean getEmailSndngYn() {
        return this.emailSndngYn;
    }

    public void setEmailSndngYn(Boolean emailSndngYn) {
        this.emailSndngYn = emailSndngYn;
    }

    public Boolean getSmsSndngYn() {
        return this.smsSndngYn;
    }

    public void setSmsSndngYn(Boolean smsSndngYn) {
        this.smsSndngYn = smsSndngYn;
    }

}
