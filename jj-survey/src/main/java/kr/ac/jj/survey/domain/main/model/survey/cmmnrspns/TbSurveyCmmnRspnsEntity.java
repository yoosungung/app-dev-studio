package kr.ac.jj.survey.domain.main.model.survey.cmmnrspns;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_공통문항 응답 Entity
 */
abstract class TbSurveyCmmnRspnsEntity extends MainEntity {

    private static final long serialVersionUID = 753462381837448145L;

    protected String cmmnRspnsId;
    protected Long surveyId;
    protected String personId;
    protected Integer rspnsOdr;
    protected String sexdstn;
    protected String area;
    protected String grade;
    protected String cghmy;
    protected String subjct;
    protected String stsfdg01;
    protected String stsfdg02;
    protected String stsfdg03;

    public String getCmmnRspnsId() {
        return this.cmmnRspnsId;
    }

    public void setCmmnRspnsId(String cmmnRspnsId) {
        this.cmmnRspnsId = cmmnRspnsId;
    }

    public Long getSurveyId() {
        return this.surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getRspnsOdr() {
        return this.rspnsOdr;
    }

    public void setRspnsOdr(Integer rspnsOdr) {
        this.rspnsOdr = rspnsOdr;
    }

    public String getSexdstn() {
        return this.sexdstn;
    }

    public void setSexdstn(String sexdstn) {
        this.sexdstn = sexdstn;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCghmy() {
        return this.cghmy;
    }

    public void setCghmy(String cghmy) {
        this.cghmy = cghmy;
    }

    public String getSubjct() {
        return this.subjct;
    }

    public void setSubjct(String subjct) {
        this.subjct = subjct;
    }

    public String getStsfdg01() {
        return this.stsfdg01;
    }

    public void setStsfdg01(String stsfdg01) {
        this.stsfdg01 = stsfdg01;
    }

    public String getStsfdg02() {
        return this.stsfdg02;
    }

    public void setStsfdg02(String stsfdg02) {
        this.stsfdg02 = stsfdg02;
    }

    public String getStsfdg03() {
        return this.stsfdg03;
    }

    public void setStsfdg03(String stsfdg03) {
        this.stsfdg03 = stsfdg03;
    }

}
