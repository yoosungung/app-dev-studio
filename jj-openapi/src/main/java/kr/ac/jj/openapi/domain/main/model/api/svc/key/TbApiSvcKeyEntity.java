package kr.ac.jj.openapi.domain.main.model.api.svc.key;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 오픈API_서비스_키 Entity
 */
abstract class TbApiSvcKeyEntity extends MainEntity {

    private static final long serialVersionUID = 7809740231670482056L;

    protected String svcKeyId;
    protected String svcId;
    protected String apiKey;
    protected String applcntId;
    protected Date rqstdt;
    protected Integer callCoPday;
    protected Date keyUsePdBegin;
    protected Date keyUsePdEnd;
    protected String sttus;
    protected String requstMatter;
    protected String exmntPsnId;
    protected Date exmntDt;
    protected String exmntResult;

    public String getSvcKeyId() {
        return this.svcKeyId;
    }

    public void setSvcKeyId(String svcKeyId) {
        this.svcKeyId = svcKeyId;
    }

    public String getSvcId() {
        return this.svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApplcntId() {
        return this.applcntId;
    }

    public void setApplcntId(String applcntId) {
        this.applcntId = applcntId;
    }

    public Date getRqstdt() {
        return this.rqstdt;
    }

    public void setRqstdt(Date rqstdt) {
        this.rqstdt = rqstdt;
    }

    public Integer getCallCoPday() {
        return this.callCoPday;
    }

    public void setCallCoPday(Integer callCoPday) {
        this.callCoPday = callCoPday;
    }

    public Date getKeyUsePdBegin() {
        return this.keyUsePdBegin;
    }

    public void setKeyUsePdBegin(Date keyUsePdBegin) {
        this.keyUsePdBegin = keyUsePdBegin;
    }

    public Date getKeyUsePdEnd() {
        return this.keyUsePdEnd;
    }

    public void setKeyUsePdEnd(Date keyUsePdEnd) {
        this.keyUsePdEnd = keyUsePdEnd;
    }

    public String getSttus() {
        return this.sttus;
    }

    public void setSttus(String sttus) {
        this.sttus = sttus;
    }

    public String getRequstMatter() {
        return this.requstMatter;
    }

    public void setRequstMatter(String requstMatter) {
        this.requstMatter = requstMatter;
    }

    public String getExmntPsnId() {
        return this.exmntPsnId;
    }

    public void setExmntPsnId(String exmntPsnId) {
        this.exmntPsnId = exmntPsnId;
    }

    public Date getExmntDt() {
        return this.exmntDt;
    }

    public void setExmntDt(Date exmntDt) {
        this.exmntDt = exmntDt;
    }

    public String getExmntResult() {
        return this.exmntResult;
    }

    public void setExmntResult(String exmntResult) {
        this.exmntResult = exmntResult;
    }

}
