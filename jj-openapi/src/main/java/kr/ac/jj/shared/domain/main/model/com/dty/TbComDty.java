package kr.ac.jj.shared.domain.main.model.com.dty;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 직무
 */
public class TbComDty extends TbComDtyEntity {

    private static final long serialVersionUID = -4965438304737093084L;

    public TbComDty newId() {
        this.setDtyId(IdGenerationUtil.createUid("TB_COM_DTY"));

        return this;
    }

    public String getDtySeNm() {
        return CodeDataUtil.getCodeName("/domain.main.tbComDty/dtySe", this.getDtySe());
    }

    public String getUseYnNm() {
        return CodeDataUtil.getCodeName("/common/useYn", this.getUseYn());
    }

}
