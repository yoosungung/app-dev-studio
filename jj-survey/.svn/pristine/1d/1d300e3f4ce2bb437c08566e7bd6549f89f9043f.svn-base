package kr.ac.jj.survey.domain.main.mapper.jd.survey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.JdSurvey;

/**
 *  Entity Mapper
 */
abstract interface JdSurveyEntityMapper
{
    /**
     * 목록 조회 - SURVEY_DEFINITION_ID(으)로
     *
     * @param surveyDefinitionId
     * @return
     */
    public List<JdSurvey> selectListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurvey select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurvey
     * @return
     */
    public int insert(@Param("jdSurvey") JdSurvey jdSurvey);

    /**
     * 수정
     *
     * @param jdSurvey
     * @return
     */
    public int update(@Param("jdSurvey") JdSurvey jdSurvey);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

    /**
     * 목록 삭제 - SURVEY_DEFINITION_ID(으)로
     *
     * @param surveyDefinitionId
     * @return
     */
    public int deleteListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);
}
