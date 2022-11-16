package kr.ac.jj.openapi.domain.main.model.api.svc;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 오픈API_서비스 Entity
 */
abstract class TbApiSvcEntity extends MainEntity {

    private static final long serialVersionUID = -8710941435907341711L;

    protected String svcId;
    protected String registPsnId;
    protected Date registDt;
    protected String svcNm;
    protected String url;
    protected String dataFrmat;
    protected Boolean othbcTy;
    protected Date othbcPdBegin;
    protected Date othbcPdEnd;
    protected Date validPdBegin;
    protected Date validPdEnd;
    protected String manageDeptNm;
    protected String manageDeptTlphonNo;
    protected String atchFileId;
    protected String rm;
    protected String personSe;

    public String getPersonSe() {
        return this.personSe;
    }

    public void setPersonSe(String personSe) {
        this.personSe = personSe;
    }

    public String getSvcId() {
        return this.svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
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

    public String getSvcNm() {
        return this.svcNm;
    }

    public void setSvcNm(String svcNm) {
        this.svcNm = svcNm;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataFrmat() {
        return this.dataFrmat;
    }

    public void setDataFrmat(String dataFrmat) {
        this.dataFrmat = dataFrmat;
    }

    public Boolean getOthbcTy() {
        return this.othbcTy;
    }

    public void setOthbcTy(Boolean othbcTy) {
        this.othbcTy = othbcTy;
    }

    public Date getOthbcPdBegin() {
        return this.othbcPdBegin;
    }

    public void setOthbcPdBegin(Date othbcPdBegin) {
        this.othbcPdBegin = othbcPdBegin;
    }

    public Date getOthbcPdEnd() {
        return this.othbcPdEnd;
    }

    public void setOthbcPdEnd(Date othbcPdEnd) {
        this.othbcPdEnd = othbcPdEnd;
    }

    public Date getValidPdBegin() {
        return this.validPdBegin;
    }

    public void setValidPdBegin(Date validPdBegin) {
        this.validPdBegin = validPdBegin;
    }

    public Date getValidPdEnd() {
        return this.validPdEnd;
    }

    public void setValidPdEnd(Date validPdEnd) {
        this.validPdEnd = validPdEnd;
    }

    public String getManageDeptNm() {
        return this.manageDeptNm;
    }

    public void setManageDeptNm(String manageDeptNm) {
        this.manageDeptNm = manageDeptNm;
    }

    public String getManageDeptTlphonNo() {
        return this.manageDeptTlphonNo;
    }

    public void setManageDeptTlphonNo(String manageDeptTlphonNo) {
        this.manageDeptTlphonNo = manageDeptTlphonNo;
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
