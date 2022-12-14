package kr.ac.jj.openapi.domain.main.model.api.svc.key;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 오픈API_서비스_키
 */
public class TbApiSvcKey extends TbApiSvcKeyEntity {

    private static final long serialVersionUID = 6785187503689299552L;

    public TbApiSvcKey newId() {
        this.setSvcKeyId(IdGenerationUtil.createUid("TB_API_SVC_KEY"));

        return this;
    }

    public String getSttusNm() {
        return CodeDataUtil.getCodeName("[KEY_STTUS]", this.getSttus(), this.getSttus());
    }

}
