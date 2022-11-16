package kr.ac.jj.shared.domain.main.model.sys.log.error;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - 에러 로그 Entity
 */
abstract class TbSysLogErrorEntity extends MainEntity {

    private static final long serialVersionUID = -6029661762207953112L;

    protected String errorLogId;
    protected String logId;
    protected String errorCode;
    protected Date errorDt;
    protected String errorClass;
    protected String errorMssage;
    protected String errorStack;

    public String getErrorLogId() {
        return this.errorLogId;
    }

    public void setErrorLogId(String errorLogId) {
        this.errorLogId = errorLogId;
    }

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getErrorDt() {
        return this.errorDt;
    }

    public void setErrorDt(Date errorDt) {
        this.errorDt = errorDt;
    }

    public String getErrorClass() {
        return this.errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    public String getErrorMssage() {
        return this.errorMssage;
    }

    public void setErrorMssage(String errorMssage) {
        this.errorMssage = errorMssage;
    }

    public String getErrorStack() {
        return this.errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

}
