package kr.ac.jj.shared.domain.main.model.bbs.gnrl;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 게시판 - 일반(답변형)
 */
public class TbBbsGnrl extends TbBbsGnrlEntity {

    private static final long serialVersionUID = 2912393490246628109L;

    public TbBbsGnrl newId() {
        this.setBbscttId(IdGenerationUtil.createUid("TB_BBS_GNRL"));

        return this;
    }

}
