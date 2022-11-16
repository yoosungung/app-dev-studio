package kr.ac.jj.shared.domain.main.model.intrfc.user;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 인터페이스 - 사용자 Entity
 */
abstract class TbIntrfcUserEntity extends MainEntity {

    private static final long serialVersionUID = 6226403399080191573L;

    protected String userId;
    protected String userPassword;
    protected String emailAdres;
    protected String deptCode;
    protected String userSe;
    protected String ecnyDe;
    protected String userNm;
    protected String tlphonNo;
    protected String univCode;
    protected String univNm;
    protected String undegCode;
    protected String undegNm;
    protected String subjctCode;
    protected String subjctNm;
    protected String ofcpsNm;
    protected String deleteYn;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmailAdres() {
        return this.emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getUserSe() {
        return this.userSe;
    }

    public void setUserSe(String userSe) {
        this.userSe = userSe;
    }

    public String getEcnyDe() {
        return this.ecnyDe;
    }

    public void setEcnyDe(String ecnyDe) {
        this.ecnyDe = ecnyDe;
    }

    public String getUserNm() {
        return this.userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getTlphonNo() {
        return this.tlphonNo;
    }

    public void setTlphonNo(String tlphonNo) {
        this.tlphonNo = tlphonNo;
    }

    public String getUnivCode() {
        return this.univCode;
    }

    public void setUnivCode(String univCode) {
        this.univCode = univCode;
    }

    public String getUnivNm() {
        return this.univNm;
    }

    public void setUnivNm(String univNm) {
        this.univNm = univNm;
    }

    public String getUndegCode() {
        return this.undegCode;
    }

    public void setUndegCode(String undegCode) {
        this.undegCode = undegCode;
    }

    public String getUndegNm() {
        return this.undegNm;
    }

    public void setUndegNm(String undegNm) {
        this.undegNm = undegNm;
    }

    public String getSubjctCode() {
        return this.subjctCode;
    }

    public void setSubjctCode(String subjctCode) {
        this.subjctCode = subjctCode;
    }

    public String getSubjctNm() {
        return this.subjctNm;
    }

    public void setSubjctNm(String subjctNm) {
        this.subjctNm = subjctNm;
    }

    public String getOfcpsNm() {
        return this.ofcpsNm;
    }

    public void setOfcpsNm(String ofcpsNm) {
        this.ofcpsNm = ofcpsNm;
    }

    public String getDeleteYn() {
        return this.deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

}
