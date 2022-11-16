package kr.ac.jj.shared.domain.main.model.sys.author;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 권한
 */
public class TbSysAuthor extends TbSysAuthorEntity {

    private static final long serialVersionUID = 6021517453382809465L;

    public TbSysAuthor newId() {
        this.setAuthorId(IdGenerationUtil.createUid("TB_SYS_AUTHOR"));

        return this;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }

    public String getBassAuthorYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getBassAuthorYn());
    }

    public String getUserAuthorYnNm() {
        return super.getBooleanCodeName("/common/yesNo", this.getUserAuthorYn());
    }

}
