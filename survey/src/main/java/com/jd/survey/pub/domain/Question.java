package com.jd.survey.pub.domain;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.jd.survey.com.BaseBean;

/**
 * 설문조사 페이지 설정
 */
public class Question extends BaseBean {
	
	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = 7407648205327901062L;
	
	//~ Default Instance fields ============================================================
	
	/** 설문조사 페이지 설정 아이디*/
	private long surveyDefinitionPageId;

	/** 설문조사 페이지 설정 아이디*/
	private long questionId;
	
	/** 제목*/
	private  String type;
	
	/** 질문순번*/
	private int questionOrder;
	
	/** 질문명*/
	private String questionText;
	
	/** 팁*/
	private String tip;
	
	/** 필수여부*/
	private int required;

	/** 중복여부*/
	private int duplicate;
	
	/** 데이터집합아이디*/
	private int dataSetId;
	
	/** 기타항목허용여부*/
	private int allowOtherTextBox;
	
	/** 출력방향*/	
	private String direction;
	
	/** 넓이*/
	private int width;
	
	/** 높이*/
	private int height;
	
	/** 날짜최대값*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateMaximum;
	
	/** 날짜최소값*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateMinimum;
	
	/** 실수최대값*/
	private double decimalMaximum;
	
	/** 실수최소값*/
	private double decimalMinimum;
	
	/** 정수최대값*/
	private int integerMaximum;
	
	/** 정수최소값*/
	private int integerMinimum;
	
	/** 멀티미디어링크*/
	private String multimediaLink;
	
	/** 랜덤출력여부*/
	private int randomizeOptions;
	
	/** 정규표현식*/
	private String regularExpression;
	
	/** 출력여부*/
	private int visible;
	
	/** 질문옵션 목록*/
	private List<QuestionOption> questionOptionsList;
	
	
	//~ Instance fields ====================================================================
	
	//~ Constructors =======================================================================
	
	//~ Methods ============================================================================

	//~ Default Methods ====================================================================

	public long getSurveyDefinitionPageId() {
		return surveyDefinitionPageId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public String getType() {
		return type;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public String getQuestionText() {
		return questionText;
	}

	public String getTip() {
		return tip;
	}

	public int getRequired() {
		return required;
	}

	public int getDuplicate() {
		return duplicate;
	}

	public int getDataSetId() {
		return dataSetId;
	}

	public int getAllowOtherTextBox() {
		return allowOtherTextBox;
	}

	public String getDirection() {
		return direction;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Date getDateMaximum() {
		return dateMaximum;
	}

	public Date getDateMinimum() {
		return dateMinimum;
	}

	public double getDecimalMaximum() {
		return decimalMaximum;
	}

	public double getDecimalMinimum() {
		return decimalMinimum;
	}

	public int getIntegerMaximum() {
		return integerMaximum;
	}

	public int getIntegerMinimum() {
		return integerMinimum;
	}

	public String getMultimediaLink() {
		return multimediaLink;
	}

	public int getRandomizeOptions() {
		return randomizeOptions;
	}

	public String getRegularExpression() {
		return regularExpression;
	}

	public int getVisible() {
		return visible;
	}

	public List<QuestionOption> getQuestionOptionsList() {
		return questionOptionsList;
	}

	public void setSurveyDefinitionPageId(long surveyDefinitionPageId) {
		this.surveyDefinitionPageId = surveyDefinitionPageId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public void setDuplicate(int duplicate) {
		this.duplicate = duplicate;
	}

	public void setDataSetId(int dataSetId) {
		this.dataSetId = dataSetId;
	}

	public void setAllowOtherTextBox(int allowOtherTextBox) {
		this.allowOtherTextBox = allowOtherTextBox;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDateMaximum(Date dateMaximum) {
		this.dateMaximum = dateMaximum;
	}

	public void setDateMinimum(Date dateMinimum) {
		this.dateMinimum = dateMinimum;
	}

	public void setDecimalMaximum(double decimalMaximum) {
		this.decimalMaximum = decimalMaximum;
	}

	public void setDecimalMinimum(double decimalMinimum) {
		this.decimalMinimum = decimalMinimum;
	}

	public void setIntegerMaximum(int integerMaximum) {
		this.integerMaximum = integerMaximum;
	}

	public void setIntegerMinimum(int integerMinimum) {
		this.integerMinimum = integerMinimum;
	}

	public void setMultimediaLink(String multimediaLink) {
		this.multimediaLink = multimediaLink;
	}

	public void setRandomizeOptions(int randomizeOptions) {
		this.randomizeOptions = randomizeOptions;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public void setQuestionOptionsList(List<QuestionOption> questionOptionsList) {
		this.questionOptionsList = questionOptionsList;
	}
	
}