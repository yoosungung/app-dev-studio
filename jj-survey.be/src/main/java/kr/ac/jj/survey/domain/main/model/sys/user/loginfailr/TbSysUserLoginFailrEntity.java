package kr.ac.jj.survey.domain.main.model.sys.user.loginfailr;

import java.util.Date;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 시스템 - 사용자 로그인 실패 Entity
 */
abstract class TbSysUserLoginFailrEntity extends MainEntity {
    private static final long serialVersionUID = 943775915776984333L;

    protected String loginNm;
    protected Integer failrCo;
    protected Date lastFailrDt;

    public String getLoginNm() {
        return this.loginNm;
    }

    public void setLoginNm(String loginNm) {
        this.loginNm = loginNm;
    }

    public Integer getFailrCo() {
        return this.failrCo;
    }

    public void setFailrCo(Integer failrCo) {
        this.failrCo = failrCo;
    }

    public Date getLastFailrDt() {
        return this.lastFailrDt;
    }

    public void setLastFailrDt(Date lastFailrDt) {
        this.lastFailrDt = lastFailrDt;
    }
}
