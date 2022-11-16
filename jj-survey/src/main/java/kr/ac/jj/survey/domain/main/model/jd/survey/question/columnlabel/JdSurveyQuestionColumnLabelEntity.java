package kr.ac.jj.survey.domain.main.model.jd.survey.question.columnlabel;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * Entity
 */
abstract class JdSurveyQuestionColumnLabelEntity extends MainEntity {

    private static final long serialVersionUID = -2798995699946061899L;

    protected Long id;
    protected String type;
    protected String label;
    protected Integer columnLabelOrder;
    protected Long questionId;
    protected Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getColumnLabelOrder() {
        return this.columnLabelOrder;
    }

    public void setColumnLabelOrder(Integer columnLabelOrder) {
        this.columnLabelOrder = columnLabelOrder;
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
