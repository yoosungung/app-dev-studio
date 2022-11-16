package kr.ac.jj.survey.domain.main.model.jd.survey.question.option;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * Entity
 */
abstract class JdSurveyQuestionOptionEntity extends MainEntity {

    private static final long serialVersionUID = 6285730450917701970L;

    protected Long id;
    protected String optionText;
    protected String optionValue;
    protected Integer optionOrder;
    protected Long questionId;
    protected Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return this.optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionValue() {
        return this.optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public Integer getOptionOrder() {
        return this.optionOrder;
    }

    public void setOptionOrder(Integer optionOrder) {
        this.optionOrder = optionOrder;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
