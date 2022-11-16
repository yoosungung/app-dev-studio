package kr.ac.jj.shared.domain.main.model.sys.code.value;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 코드 값
 */
public class TbSysCodeValue extends TbSysCodeValueEntity {

    private static final long serialVersionUID = -5228653368196029047L;

    public TbSysCodeValue newId() {
        this.setCodeValueId(IdGenerationUtil.createUid("TB_SYS_CODE_VALUE"));

        return this;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }

}
