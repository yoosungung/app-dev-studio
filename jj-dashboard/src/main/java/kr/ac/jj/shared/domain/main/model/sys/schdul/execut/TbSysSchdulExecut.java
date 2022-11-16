package kr.ac.jj.shared.domain.main.model.sys.schdul.execut;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 스케줄 실행
 */
public class TbSysSchdulExecut extends TbSysSchdulExecutEntity {

    private static final long serialVersionUID = -3747543944611086493L;

    public TbSysSchdulExecut newId() {
        this.setSchdulExecutId(IdGenerationUtil.getUid());

        return this;
    }

}
