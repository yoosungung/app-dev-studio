package kr.ac.jj.openapi.domain.main.model.api.svc.result;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 오픈API_서비스_인자_매핑
 */
public class TbApiSvcResult extends TbApiSvcResultEntity {

    private static final long serialVersionUID = 2131048697363654736L;

    public TbApiSvcResult newId() {
        this.setSvcResultId(IdGenerationUtil.createUid("TB_API_SVC_RESULT"));
        return this;
    }
}
