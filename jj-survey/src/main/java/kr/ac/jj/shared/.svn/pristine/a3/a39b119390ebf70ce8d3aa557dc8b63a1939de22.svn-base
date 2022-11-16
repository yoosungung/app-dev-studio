package kr.ac.jj.shared.domain.main.model.com.sms;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 문자메시지
 */
public class TbComSms extends TbComSmsEntity {

    private static final long serialVersionUID = 1972817060567158891L;

    public TbComSms newId() {
        this.setSmsId(IdGenerationUtil.createUid("TB_COM_SMS"));

        return this;
    }

    @Override
    public void setTranPhone(String tranPhone) {
        this.tranPhone = tranPhone;
    }

    @Override
    public void setTranCallback(String tranCallback) {
        this.tranCallback = tranCallback;
    }

    @Override
    public void setTranMsg(String tranMsg) {
        this.tranMsg = tranMsg;

        if (tranMsg != null && tranMsg.length() > 80) {
            this.setTranType(6);
        }
    }

    public String getTranStatusNm() {
        return CodeDataUtil.getCodeName("/domain.main.tbComSms/tranStatus", this.tranStatus, this.tranStatus);
    }

    public String getTranRsltNm() {
        return CodeDataUtil.getCodeName("/domain.main.tbComSms/tranRslt", this.tranRslt, this.tranRslt);
    }

    public String getTranTypeNm() {
        return CodeDataUtil.getCodeName("/domain.main.tbComSms/tranType", this.tranType + "", this.tranType + "");
    }

}
