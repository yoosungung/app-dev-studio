package kr.ac.jj.shared.domain.main.model.sys.schdul.trigr;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 스케줄 트리거
 */
public class TbSysSchdulTrigr extends TbSysSchdulTrigrEntity {

    private static final long serialVersionUID = -7688492852399464749L;

    public TbSysSchdulTrigr newId() {
        this.setSchdulTrigrId(IdGenerationUtil.getUid());

        return this;
    }

}
