package kr.ac.jj.survey.domain.main.mapper.jd.survey.question;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveyQuestionMapper extends JdSurveyQuestionEntityMapper {

    /**
     * 목록 삭제 - SURVEY_DEFINITION_ID로
     *
     * @param surveyDefinitionId
     * @return
     */
    public int deleteListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

}
