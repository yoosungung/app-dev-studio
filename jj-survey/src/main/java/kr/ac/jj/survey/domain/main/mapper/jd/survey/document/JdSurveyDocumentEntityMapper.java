package kr.ac.jj.survey.domain.main.mapper.jd.survey.document;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.document.JdSurveyDocument;

/**
 * Entity Mapper
 */
abstract interface JdSurveyDocumentEntityMapper {

    /**
     * 목록 조회 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public List<JdSurveyDocument> selectListByQuestionId(@Param("questionId") Long questionId);

    /**
     * 목록 조회 - SURVEY_ID(으)로
     *
     * @param surveyId
     * @return
     */
    public List<JdSurveyDocument> selectListBySurveyId(@Param("surveyId") Long surveyId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyDocument select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyDocument
     * @return
     */
    public int insert(@Param("jdSurveyDocument") JdSurveyDocument jdSurveyDocument);

    /**
     * 수정
     *
     * @param jdSurveyDocument
     * @return
     */
    public int update(@Param("jdSurveyDocument") JdSurveyDocument jdSurveyDocument);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

    /**
     * 목록 삭제 - QUESTION_ID(으)로
     *
     * @param questionId
     * @return
     */
    public int deleteListByQuestionId(@Param("questionId") Long questionId);

    /**
     * 목록 삭제 - SURVEY_ID(으)로
     *
     * @param surveyId
     * @return
     */
    public int deleteListBySurveyId(@Param("surveyId") Long surveyId);

}
