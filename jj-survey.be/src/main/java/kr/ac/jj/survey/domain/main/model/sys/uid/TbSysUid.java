package kr.ac.jj.survey.domain.main.model.sys.uid;

import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - UID
 */
public class TbSysUid extends TbSysUidEntity {
    private static final long serialVersionUID = -8091674878992665511L;

    public TbSysUid newId() {
        this.setUidValue(IdGenerationUtil.createUid("TB_SYS_UID"));

        return this;
    }
}
