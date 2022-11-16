package kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_설문지_공통문항 Entity
 */
abstract class TbSurveyQestnrCmmnEntity extends MainEntity {

    private static final long serialVersionUID = 5853518477864101238L;

    protected String surveyQestnrId;
    protected Boolean sexdstn;
    protected Boolean area;
    protected Boolean grade;
    protected Boolean cghmy;
    protected Boolean subjct;
    protected Boolean stsfdg01;
    protected Boolean stsfdg02;
    protected Boolean stsfdg03;

    public String getSurveyQestnrId() {
        return this.surveyQestnrId;
    }

    public void setSurveyQestnrId(String surveyQestnrId) {
        this.surveyQestnrId = surveyQestnrId;
    }

    public Boolean getSexdstn() {
        return this.sexdstn;
    }

    public void setSexdstn(Boolean sexdstn) {
        this.sexdstn = sexdstn;
    }

    public Boolean getArea() {
        return this.area;
    }

    public void setArea(Boolean area) {
        this.area = area;
    }

    public Boolean getGrade() {
        return this.grade;
    }

    public void setGrade(Boolean grade) {
        this.grade = grade;
    }

    public Boolean getCghmy() {
        return this.cghmy;
    }

    public void setCghmy(Boolean cghmy) {
        this.cghmy = cghmy;
    }

    public Boolean getSubjct() {
        return this.subjct;
    }

    public void setSubjct(Boolean subjct) {
        this.subjct = subjct;
    }

    public Boolean getStsfdg01() {
        return this.stsfdg01;
    }

    public void setStsfdg01(Boolean stsfdg01) {
        this.stsfdg01 = stsfdg01;
    }

    public Boolean getStsfdg02() {
        return this.stsfdg02;
    }

    public void setStsfdg02(Boolean stsfdg02) {
        this.stsfdg02 = stsfdg02;
    }

    public Boolean getStsfdg03() {
        return this.stsfdg03;
    }

    public void setStsfdg03(Boolean stsfdg03) {
        this.stsfdg03 = stsfdg03;
    }

}
