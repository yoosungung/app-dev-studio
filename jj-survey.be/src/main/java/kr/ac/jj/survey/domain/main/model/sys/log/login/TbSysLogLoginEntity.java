package kr.ac.jj.survey.domain.main.model.sys.log.login;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 로그인 로그 Entity
 */
abstract class TbSysLogLoginEntity extends MainEntity {
    private static final long serialVersionUID = 6466615412247690730L;

    protected String loginLogId;
    protected String logId;
    protected String loginNm;
    protected String loginTy;
    protected Boolean succesYn;

    public String getLoginLogId() {
        return this.loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getLogId() {
        return this.logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLoginNm() {
        return this.loginNm;
    }

    public void setLoginNm(String loginNm) {
        this.loginNm = loginNm;
    }

    public String getLoginTy() {
        return this.loginTy;
    }

    public void setLoginTy(String loginTy) {
        this.loginTy = loginTy;
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
