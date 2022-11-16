package kr.ac.jj.openapi.domain.main.model.api.svc.key.hist;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 오픈API_서비스_이력
 */
public class TbApiSvcKeyHist extends TbApiSvcKeyHistEntity {

    private static final long serialVersionUID = -8039243029194244666L;

    public TbApiSvcKeyHist newId() {
        this.setSvcHistId(IdGenerationUtil.createUid("TB_API_SVC_KEY_HIST"));

        return this;
    }

}
