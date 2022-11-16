package kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.page;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.definition.page.JdSurveyDefinitionPage;

/**
 * Entity Mapper
 */
abstract interface JdSurveyDefinitionPageEntityMapper {

    /**
     * 목록 조회 - SURVEY_DEFINITION_ID(으)로
     *
     * @param surveyDefinitionId
     * @return
     */
    public List<JdSurveyDefinitionPage> selectListBySurveyDefinitionId(
            @Param("surveyDefinitionId") Long surveyDefinitionId);

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyDefinitionPage select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyDefinitionPage
     * @return
     */
    public int insert(@Param("jdSurveyDefinitionPage") JdSurveyDefinitionPage jdSurveyDefinitionPage);

    /**
     * 수정
     *
     * @param jdSurveyDefinitionPage
     * @return
     */
    public int update(@Param("jdSurveyDefinitionPage") JdSurveyDefinitionPage jdSurveyDefinitionPage);

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
