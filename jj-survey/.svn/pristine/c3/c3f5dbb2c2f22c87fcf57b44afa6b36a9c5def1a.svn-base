package kr.ac.jj.survey.domain.main.mapper.jd.survey.question;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.question.JdSurveyQuestion;

/**
 * Entity Mapper
 */
abstract interface JdSurveyQuestionEntityMapper {

    /**
     * 목록 조회 - SURVEY_DEFINITION_PAGE_ID(으)로
     *
     * @param surveyDefinitionPageId
     * @return
     */
    public List<JdSurveyQuestion> selectListBySurveyDefinitionPageId(
            @Param("surveyDefinitionPageId") Long surveyDefinitionPageId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyQuestion select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyQuestion
     * @return
     */
    public int insert(@Param("jdSurveyQuestion") JdSurveyQuestion jdSurveyQuestion);

    /**
     * 수정
     *
     * @param jdSurveyQuestion
     * @return
     */
    public int update(@Param("jdSurveyQuestion") JdSurveyQuestion jdSurveyQuestion);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

    /**
     * 목록 삭제 - SURVEY_DEFINITION_PAGE_ID(으)로
     *
     * @param surveyDefinitionPageId
     * @return
     */
    public int deleteListBySurveyDefinitionPageId(@Param("surveyDefinitionPageId") Long surveyDefinitionPageId);

}
