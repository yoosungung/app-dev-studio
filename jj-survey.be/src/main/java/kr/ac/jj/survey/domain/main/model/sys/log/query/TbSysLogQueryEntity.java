package kr.ac.jj.survey.domain.main.model.sys.log.query;

import java.util.Date;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 시스템 - 쿼리 로그 Entity
 */
abstract class TbSysLogQueryEntity extends MainEntity {
    private static final long serialVersionUID = -1545973790104913133L;

    protected String queryLogId;
    protected String logId;
    protected Integer querySn;
    protected Date queryDt;
    protected String queryId;
    protected String querySntenc;
    protected String queryParamtr;
    protected String querySntencCmplt;

    public String getQueryLogId() {
        return this.queryLogId;
    }

    public void setQueryLogId(String queryLogId) {
        this.queryLogId = queryLogId;
    }

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Integer getQuerySn() {
        return this.querySn;
    }

    public void setQuerySn(Integer querySn) {
        this.querySn = querySn;
    }

    public Date getQueryDt() {
        return this.queryDt;
    }

    public void setQueryDt(Date queryDt) {
        this.queryDt = queryDt;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getQuerySntenc() {
        return this.querySntenc;
    }

    public void setQuerySntenc(String querySntenc) {
        this.querySntenc = querySntenc;
    }

    public String getQueryParamtr() {
        return this.queryParamtr;
    }

    public void setQueryParamtr(String queryParamtr) {
        this.queryParamtr = queryParamtr;
    }

    public String getQuerySntencCmplt() {
        return this.querySntencCmplt;
    }

    public void setQuerySntencCmplt(String querySntencCmplt) {
        this.querySntencCmplt = querySntencCmplt;
    }
}
