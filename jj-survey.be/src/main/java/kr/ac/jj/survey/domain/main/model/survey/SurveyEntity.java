package kr.ac.jj.survey.domain.main.model.survey;

import java.util.Date;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 설문_응답 Entity
 */
abstract class SurveyEntity extends MainEntity {
    private static final long serialVersionUID = 6297754670320947720L;

    protected Integer id;
    protected Integer surveyDefinitionId;
    protected String ipAddress;
    protected String personId;
    protected String email;
    protected String koreanNm;
    protected String englNm;
    protected String chcrtNm;
    protected String status;
    protected Date creationDate;
    protected Date lastUpdateDate;
    protected Date submissionDate;
    protected String typeName;
    protected Integer version;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyDefinitionId() {
        return this.surveyDefinitionId;
    }

    public void setSurveyDefinitionId(Integer surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKoreanNm() {
        return this.koreanNm;
    }

    public void setKoreanNm(String koreanNm) {
        this.koreanNm = koreanNm;
    }

    public String getEnglNm() {
        return this.englNm;
    }

    public void setEnglNm(String englNm) {
        this.englNm = englNm;
    }

    public String getChcrtNm() {
        return this.chcrtNm;
    }

    public void setChcrtNm(String chcrtNm) {
        this.chcrtNm = chcrtNm;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getSubmissionDate() {
        return this.submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
