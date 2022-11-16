package kr.ac.jj.survey.domain.main.model.sys.log.login;

import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 로그인 로그
 */
public class TbSysLogLogin extends TbSysLogLoginEntity {
    private static final long serialVersionUID = 7223780037969399536L;

    public TbSysLogLogin newId() {
        this.setLoginLogId(IdGenerationUtil.createUid("TB_SYS_LOG_LOGIN"));

        return this;
    }

    public String getSuccesYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getSuccesYn());
    }
}
