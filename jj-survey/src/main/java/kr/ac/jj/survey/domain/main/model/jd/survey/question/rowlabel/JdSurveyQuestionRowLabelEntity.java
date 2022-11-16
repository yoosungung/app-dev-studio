package kr.ac.jj.survey.domain.main.model.jd.survey.question.rowlabel;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * Entity
 */
abstract class JdSurveyQuestionRowLabelEntity extends MainEntity {

    private static final long serialVersionUID = 4516638780652437923L;

    protected Long id;
    protected String label;
    protected Integer rowLabelOrder;
    protected Long questionId;
    protected Integer version;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getRowLabelOrder() {
        return this.rowLabelOrder;
    }

    public void setRowLabelOrder(Integer rowLabelOrder) {
        this.rowLabelOrder = rowLabelOrder;
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
