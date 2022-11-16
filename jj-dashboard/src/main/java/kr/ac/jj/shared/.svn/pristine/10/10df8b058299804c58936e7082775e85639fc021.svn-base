package kr.ac.jj.shared.domain.main.model.sys.log;

import javax.servlet.http.HttpServletResponse;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 로그
 */
public class TbSysLog extends TbSysLogEntity {

    private static final long serialVersionUID = 7481603066954089219L;

    public TbSysLog newId() {
        this.setLogId(IdGenerationUtil.createUid("TB_SYS_LOG"));

        return this;
    }

    @Override
    public void setRspnsSttusCode(Integer rspnsSttusCode) {
        this.rspnsSttusCode = rspnsSttusCode;

        this.setSuccesYn(this.rspnsSttusCode < HttpServletResponse.SC_BAD_REQUEST);
    }

    public String getSuccesYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getSuccesYn());
    }

}
