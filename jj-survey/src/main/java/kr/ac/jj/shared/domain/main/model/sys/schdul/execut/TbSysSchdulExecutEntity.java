package kr.ac.jj.shared.domain.main.model.sys.schdul.execut;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - 스케줄 실행 Entity
 */
abstract class TbSysSchdulExecutEntity extends MainEntity {

    private static final long serialVersionUID = 3133937583062732994L;

    protected String schdulExecutId;
    protected String schdulId;
    protected Long executSn;
    protected Integer trigrNo;
    protected String trigrExprsn;
    protected Date planBeginDt;
    protected Date executBeginDt;
    protected Date executEndDt;
    protected Date nextBeginDt;
    protected Boolean succesYn;
    protected String errorMssage;
    protected String errorStack;

    public String getSchdulExecutId() {
        return this.schdulExecutId;
    }

    public void setSchdulExecutId(String schdulExecutId) {
        this.schdulExecutId = schdulExecutId;
    }

    public String getSchdulId() {
        return this.schdulId;
    }

    public void setSchdulId(String schdulId) {
        this.schdulId = schdulId;
    }

    public Long getExecutSn() {
        return this.executSn;
    }

    public void setExecutSn(Long executSn) {
        this.executSn = executSn;
    }

    public Integer getTrigrNo() {
        return this.trigrNo;
    }

    public void setTrigrNo(Integer trigrNo) {
        this.trigrNo = trigrNo;
    }

    public String getTrigrExprsn() {
        return this.trigrExprsn;
    }

    public void setTrigrExprsn(String trigrExprsn) {
        this.trigrExprsn = trigrExprsn;
    }

    public Date getPlanBeginDt() {
        return this.planBeginDt;
    }

    public void setPlanBeginDt(Date planBeginDt) {
        this.planBeginDt = planBeginDt;
    }

    public Date getExecutBeginDt() {
        return this.executBeginDt;
    }

    public void setExecutBeginDt(Date executBeginDt) {
        this.executBeginDt = executBeginDt;
    }

    public Date getExecutEndDt() {
        return this.executEndDt;
    }

    public void setExecutEndDt(Date executEndDt) {
        this.executEndDt = executEndDt;
    }

    public Date getNextBeginDt() {
        return this.nextBeginDt;
    }

    public void setNextBeginDt(Date nextBeginDt) {
        this.nextBeginDt = nextBeginDt;
    }

    public Boolean getSuccesYn() {
        return this.succesYn;
    }

    public void setSuccesYn(Boolean succesYn) {
        this.succesYn = succesYn;
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
