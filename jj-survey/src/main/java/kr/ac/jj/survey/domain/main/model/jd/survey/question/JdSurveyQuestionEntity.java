package kr.ac.jj.survey.domain.main.model.jd.survey.question;

import java.math.BigDecimal;
import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * Entity
 */
abstract class JdSurveyQuestionEntity extends MainEntity {

    private static final long serialVersionUID = 830732323051969271L;

    protected Long id;
    protected String type;
    protected Integer questionOrder;
    protected String questionText;
    protected String tip;
    protected Integer required;
    protected Integer duplicate;
    protected Long dataSetId;
    protected Integer allowOtherTextBox;
    protected String direction;
    protected Integer width;
    protected Integer height;
    protected Date dateMaximum;
    protected Date dateMinimum;
    protected BigDecimal decimalMaximum;
    protected BigDecimal decimalMinimum;
    protected Long integerMaximum;
    protected Long integerMinimum;
    protected String multimediaLink;
    protected Integer randomizeOptions;
    protected String regularExpression;
    protected Integer visible;
    protected Long surveyDefinitionPageId;
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

    public Integer getQuestionOrder() {
        return this.questionOrder;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getRequired() {
        return this.required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public Integer getDuplicate() {
        return this.duplicate;
    }

    public void setDuplicate(Integer duplicate) {
        this.duplicate = duplicate;
    }

    public Long getDataSetId() {
        return this.dataSetId;
    }

    public void setDataSetId(Long dataSetId) {
        this.dataSetId = dataSetId;
    }

    public Integer getAllowOtherTextBox() {
        return this.allowOtherTextBox;
    }

    public void setAllowOtherTextBox(Integer allowOtherTextBox) {
        this.allowOtherTextBox = allowOtherTextBox;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getDateMaximum() {
        return this.dateMaximum;
    }

    public void setDateMaximum(Date dateMaximum) {
        this.dateMaximum = dateMaximum;
    }

    public Date getDateMinimum() {
        return this.dateMinimum;
    }

    public void setDateMinimum(Date dateMinimum) {
        this.dateMinimum = dateMinimum;
    }

    public BigDecimal getDecimalMaximum() {
        return this.decimalMaximum;
    }

    public void setDecimalMaximum(BigDecimal decimalMaximum) {
        this.decimalMaximum = decimalMaximum;
    }

    public BigDecimal getDecimalMinimum() {
        return this.decimalMinimum;
    }

    public void setDecimalMinimum(BigDecimal decimalMinimum) {
        this.decimalMinimum = decimalMinimum;
    }

    public Long getIntegerMaximum() {
        return this.integerMaximum;
    }

    public void setIntegerMaximum(Long integerMaximum) {
        this.integerMaximum = integerMaximum;
    }

    public Long getIntegerMinimum() {
        return this.integerMinimum;
    }

    public void setIntegerMinimum(Long integerMinimum) {
        this.integerMinimum = integerMinimum;
    }

    public String getMultimediaLink() {
        return this.multimediaLink;
    }

    public void setMultimediaLink(String multimediaLink) {
        this.multimediaLink = multimediaLink;
    }

    public Integer getRandomizeOptions() {
        return this.randomizeOptions;
    }

    public void setRandomizeOptions(Integer randomizeOptions) {
        this.randomizeOptions = randomizeOptions;
    }

    public String getRegularExpression() {
        return this.regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public Integer getVisible() {
        return this.visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Long getSurveyDefinitionPageId() {
        return this.surveyDefinitionPageId;
    }

    public void setSurveyDefinitionPageId(Long surveyDefinitionPageId) {
        this.surveyDefinitionPageId = surveyDefinitionPageId;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
