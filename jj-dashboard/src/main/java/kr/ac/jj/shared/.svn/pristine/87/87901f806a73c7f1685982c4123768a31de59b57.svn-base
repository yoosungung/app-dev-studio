package kr.ac.jj.shared.domain.main.model.com.email;

import java.util.Date;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 메일
 */
public class TbComEmail extends TbComEmailEntity {

    private static final long serialVersionUID = 1758358543833616544L;

    public TbComEmail() {
        this.setWritngDt(new Date());
    }

    public TbComEmail newId() {
        this.setEmailId(IdGenerationUtil.createUid("TB_COM_EMAIL"));

        return this;
    }

}
