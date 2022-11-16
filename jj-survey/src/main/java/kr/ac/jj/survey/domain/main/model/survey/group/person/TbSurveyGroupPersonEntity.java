package kr.ac.jj.survey.domain.main.model.survey.group.person;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_그룹_사람 Entity
 */
abstract class TbSurveyGroupPersonEntity extends MainEntity {

    private static final long serialVersionUID = 5316595603633600871L;

    protected String surveyGroupId;
    protected String personId;

    public String getSurveyGroupId() {
        return this.surveyGroupId;
    }

    public void setSurveyGroupId(String surveyGroupId) {
        this.surveyGroupId = surveyGroupId;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

}
