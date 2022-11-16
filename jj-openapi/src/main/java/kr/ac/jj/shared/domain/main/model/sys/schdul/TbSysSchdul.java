package kr.ac.jj.shared.domain.main.model.sys.schdul;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 스케줄
 */
public class TbSysSchdul extends TbSysSchdulEntity {

    private static final long serialVersionUID = -8094810841264188423L;

    public TbSysSchdul newId() {
        this.setSchdulId(IdGenerationUtil.getUid());

        return this;
    }

}
