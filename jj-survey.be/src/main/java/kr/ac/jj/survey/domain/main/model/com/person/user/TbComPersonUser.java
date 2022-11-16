package kr.ac.jj.survey.domain.main.model.com.person.user;

import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 - 사람 사용자
 */
public class TbComPersonUser extends TbComPersonUserEntity {
    private static final long serialVersionUID = 5354603679943949254L;

    public TbComPersonUser newId() {
        this.setPersonUserId(IdGenerationUtil.createUid("TB_COM_PERSON_USER"));

        return this;
    }
}
