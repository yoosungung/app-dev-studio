package kr.ac.jj.openapi.domain.main.model.api.svc.key.hist;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 오픈API_서비스_이력 Entity
 */
abstract class TbApiSvcKeyHistEntity extends MainEntity {

    private static final long serialVersionUID = 2253935980158478080L;

    protected String svcHistId;
    protected String svcKeyId;
    protected String svcId;
    protected String callResult;
    protected String callUserId;
    protected Date callDt;

    public String getSvcHistId() {
        return this.svcHistId;
    }

    public void setSvcHistId(String svcHistId) {
        this.svcHistId = svcHistId;
    }

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

    public String getCallResult() {
        return this.callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult;
    }

    public String getCallUserId() {
        return this.callUserId;
    }

    public void setCallUserId(String callUserId) {
        this.callUserId = callUserId;
    }

    public Date getCallDt() {
        return this.callDt;
    }

    public void setCallDt(Date callDt) {
        this.callDt = callDt;
    }

}
