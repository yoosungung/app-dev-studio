package kr.ac.jj.shared.domain.main.model.sys.resrce;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 리소스
 */
public class TbSysResrce extends TbSysResrceEntity {

    private static final long serialVersionUID = 6990274603128101717L;

    public TbSysResrce newId() {
        this.setResrceId(IdGenerationUtil.createUid("TB_SYS_RESRCE"));

        return this;
    }

    public String getResrceTyNm() {
        return CodeDataUtil.getCodeName("/domain.main.tbSysResrce/resrceTy", this.getResrceTy());
    }

    public String getUseYnNm() {
        return CodeDataUtil.getCodeName("/common/useYn", this.getUseYn());
    }

}
