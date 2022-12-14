package com.jd.survey.pub.domain;

import java.util.List;

import com.jd.survey.com.BaseBean;

/**
 * 설문조사 페이지 설정
 */
public class SurveyDefinitionPage extends BaseBean {

    // ~ Static fields/initializers
    // =========================================================

    private static final long serialVersionUID = 6938440092199036812L;

    // ~ Default Instance fields
    // ============================================================

    /** 조사아이디 */
    private long surveyDefinitionId;

    /** 설문조사 페이지 설정 아이디 */
    private long surveyDefinitionPageId;

    /** 제목 */
    private String title;

    /** 지침 */
    private String instructions;

    /** 페이지순번 */
    private int pageOrder;

    /** 페이지분기로직 */
    private String pageLogicjson;

    /** 랜덤출력여부 */
    private int randomizeQuestions;

    /** 질문목록 */
    private List<Question> questionList;

    // ~ Instance fields
    // ====================================================================

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    // ~ Default Methods
    // ====================================================================

    public long getSurveyDefinitionId() {
        return surveyDefinitionId;
    }

    public long getSurveyDefinitionPageId() {
        return surveyDefinitionPageId;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getPageOrder() {
        return pageOrder;
    }

    public String getPageLogicjson() {
        return pageLogicjson;
    }

    public int getRandomizeQuestions() {
        return randomizeQuestions;
    }

    public void setSurveyDefinitionId(long surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public void setSurveyDefinitionPageId(long surveyDefinitionPageId) {
        this.surveyDefinitionPageId = surveyDefinitionPageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPageOrder(int pageOrder) {
        this.pageOrder = pageOrder;
    }

    public void setPageLogicjson(String pageLogicjson) {
        this.pageLogicjson = pageLogicjson;
    }

    public void setRandomizeQuestions(int randomizeQuestions) {
        this.randomizeQuestions = randomizeQuestions;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

}
