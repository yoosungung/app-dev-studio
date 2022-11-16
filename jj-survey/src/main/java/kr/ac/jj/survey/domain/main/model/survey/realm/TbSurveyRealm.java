package kr.ac.jj.survey.domain.main.model.survey.realm;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 설문_분야
 */
public class TbSurveyRealm extends TbSurveyRealmEntity {

    private static final long serialVersionUID = 904209264086473782L;

    public TbSurveyRealm newId() {
        this.setSurveyRealmId(IdGenerationUtil.createUid("TB_SURVEY_REALM"));

        return this;
    }

}
