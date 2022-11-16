package kr.ac.jj.survey.domain.main.model.sys.user.setup;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 시스템 - 사용자별 환경설정 Entity
 */
abstract class TbSysUserSetupEntity extends MainEntity {
    private static final long serialVersionUID = -3737214839671542L;

    protected String userId;
    protected String setupCode;
    protected String setupValue;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSetupCode() {
        return this.setupCode;
    }

    public void setSetupCode(String setupCode) {
        this.setupCode = setupCode;
    }

    public String getSetupValue() {
        return this.setupValue;
    }

    public void setSetupValue(String setupValue) {
        this.setupValue = setupValue;
    }
}
