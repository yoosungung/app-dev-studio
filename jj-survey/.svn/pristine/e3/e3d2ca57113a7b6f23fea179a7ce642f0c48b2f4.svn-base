package kr.ac.jj.survey.domain.main.mapper.jd.survey.question.option;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveyQuestionOptionMapper extends JdSurveyQuestionOptionEntityMapper {

    /**
     * 목록 삭제 - SURVEY_DEFINITION_ID로
     *
     * @param surveyDefinitionId
     * @return
     */
    public int deleteListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

    /**
     * 복사 목록 생성
     *
     * @param sourceQuestionId
     * @param targetQuestionId
     * @return
     */
    public int insertCopyList(@Param("sourceQuestionId") Long sourceQuestionId,
            @Param("targetQuestionId") Long targetQuestionId);

}
