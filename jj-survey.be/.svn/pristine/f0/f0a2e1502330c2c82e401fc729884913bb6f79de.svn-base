package kr.ac.jj.survey.domain.main.model.sys.log;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 로그 Entity
 */
abstract class TbSysLogEntity extends MainEntity {
    private static final long serialVersionUID = 7719689375826982047L;

    protected String logId;
    protected String userId;
    protected Date logDt;
    protected String serverIp;
    protected String requstIp;
    protected String requstTy;
    protected String requstHost;
    protected String requstPath;
    protected String requstHder;
    protected String requstParamtr;
    protected String requstBody;
    protected Integer rspnsSttusCode;
    protected Boolean succesYn;

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLogDt() {
        return this.logDt;
    }

    public void setLogDt(Date logDt) {
        this.logDt = logDt;
    }

    public String getServerIp() {
        return this.serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getRequstIp() {
        return this.requstIp;
    }

    public void setRequstIp(String requstIp) {
        this.requstIp = requstIp;
    }

    public String getRequstTy() {
        return this.requstTy;
    }

    public void setRequstTy(String requstTy) {
        this.requstTy = requstTy;
    }

    public String getRequstHost() {
        return this.requstHost;
    }

    public void setRequstHost(String requstHost) {
        this.requstHost = requstHost;
    }

    public String getRequstPath() {
        return this.requstPath;
    }

    public void setRequstPath(String requstPath) {
        this.requstPath = requstPath;
    }

    public String getRequstHder() {
        return this.requstHder;
    }

    public void setRequstHder(String requstHder) {
        this.requstHder = requstHder;
    }

    public String getRequstParamtr() {
        return this.requstParamtr;
    }

    public void setRequstParamtr(String requstParamtr) {
        this.requstParamtr = requstParamtr;
    }

    public String getRequstBody() {
        return this.requstBody;
    }

    public void setRequstBody(String requstBody) {
        this.requstBody = requstBody;
    }

    public Integer getRspnsSttusCode() {
        return this.rspnsSttusCode;
    }

    public void setRspnsSttusCode(Integer rspnsSttusCode) {
        this.rspnsSttusCode = rspnsSttusCode;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getSuccesYn() {
        return this.succesYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setSuccesYn(Boolean succesYn) {
        this.succesYn = succesYn;
    }
}
