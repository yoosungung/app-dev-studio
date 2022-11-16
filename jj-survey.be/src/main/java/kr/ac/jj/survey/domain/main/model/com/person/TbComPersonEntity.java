package kr.ac.jj.survey.domain.main.model.com.person;

import java.util.Date;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 공통 - 사람 Entity
 */
abstract class TbComPersonEntity extends MainEntity {
    private static final long serialVersionUID = 2780103642930808376L;

    protected String personId;
    protected String emplNo;
    protected String emailAdres;
    protected String writngPsnId;
    protected Date writngDt;
    protected String changePsnId;
    protected Date changeDt;
    protected String koreanNm;
    protected String englNm;
    protected String chcrtNm;
    protected String deptId;
    protected String hffcSttus;
    protected Date ecnyDe;
    protected Date retireDe;
    protected String ofcpsNm;
    protected String clsfNm;
    protected String rspofcNm;
    protected String tlphonNo;
    protected String atchFileId;
    protected String rm;

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getEmplNo() {
        return this.emplNo;
    }

    public void setEmplNo(String emplNo) {
        this.emplNo = emplNo;
    }

    public String getEmailAdres() {
        return this.emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getWritngPsnId() {
        return this.writngPsnId;
    }

    public void setWritngPsnId(String writngPsnId) {
        this.writngPsnId = writngPsnId;
    }

    public Date getWritngDt() {
        return this.writngDt;
    }

    public void setWritngDt(Date writngDt) {
        this.writngDt = writngDt;
    }

    public String getChangePsnId() {
        return this.changePsnId;
    }

    public void setChangePsnId(String changePsnId) {
        this.changePsnId = changePsnId;
    }

    public Date getChangeDt() {
        return this.changeDt;
    }

    public void setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
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

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getHffcSttus() {
        return this.hffcSttus;
    }

    public void setHffcSttus(String hffcSttus) {
        this.hffcSttus = hffcSttus;
    }

    public Date getEcnyDe() {
        return this.ecnyDe;
    }

    public void setEcnyDe(Date ecnyDe) {
        this.ecnyDe = ecnyDe;
    }

    public Date getRetireDe() {
        return this.retireDe;
    }

    public void setRetireDe(Date retireDe) {
        this.retireDe = retireDe;
    }

    public String getOfcpsNm() {
        return this.ofcpsNm;
    }

    public void setOfcpsNm(String ofcpsNm) {
        this.ofcpsNm = ofcpsNm;
    }

    public String getClsfNm() {
        return this.clsfNm;
    }

    public void setClsfNm(String clsfNm) {
        this.clsfNm = clsfNm;
    }

    public String getRspofcNm() {
        return this.rspofcNm;
    }

    public void setRspofcNm(String rspofcNm) {
        this.rspofcNm = rspofcNm;
    }

    public String getTlphonNo() {
        return this.tlphonNo;
    }

    public void setTlphonNo(String tlphonNo) {
        this.tlphonNo = tlphonNo;
    }

    public String getAtchFileId() {
        return this.atchFileId;
    }

    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    public String getRm() {
        return this.rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }
}
