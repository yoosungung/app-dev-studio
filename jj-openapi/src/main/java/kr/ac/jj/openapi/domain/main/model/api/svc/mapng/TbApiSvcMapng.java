package kr.ac.jj.openapi.domain.main.model.api.svc.mapng;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 오픈API_서비스_인자_매핑
 */
public class TbApiSvcMapng extends TbApiSvcMapngEntity {

    private static final long serialVersionUID = 2131048697363654736L;
    private String inputVal;

    public TbApiSvcMapng newId() {
        this.setSvcMapngId(IdGenerationUtil.createUid("TB_API_SVC_MAPNG"));

        return this;
    }

    public String getEssntlYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.getEssntlYn());
    }

    public String getInputVal() {
        return this.inputVal;
    }

    public void setInputVal(String inputVal) {
        this.inputVal = inputVal;
    }

}
