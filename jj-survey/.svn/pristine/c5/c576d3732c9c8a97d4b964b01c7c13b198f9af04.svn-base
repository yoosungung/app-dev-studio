package kr.ac.jj.survey.domain.main.mapper.jd.survey.document;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveyDocumentMapper extends JdSurveyDocumentEntityMapper {

    /**
     * 목록 삭제 - SURVEY_DEFINITION_ID(으)로
     *
     * @param surveyDefinitionId
     * @return
     */
    public int deleteListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

}
