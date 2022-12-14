package kr.ac.jj.survey.domain.main.mapper.survey.cmmnrspns;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.cmmnrspns.TbSurveyCmmnRspns;

/**
 * 설문_공통문항 응답 Mapper
 */
@MainSqlMapper
public interface TbSurveyCmmnRspnsMapper extends TbSurveyCmmnRspnsEntityMapper {

    /**
     * 조회 - 설문 응답 ID(SURVEY_ID)로
     *
     * @param surveyId
     * @return
     */
    public TbSurveyCmmnRspns selectBySurveyId(@Param("surveyId") Long surveyId);

    /**
     * 공통 응답 ID 조회 - 설문 응답 ID(SURVEY_ID)로
     *
     * @param surveyId
     * @return
     */
    public String selectCmmnRspnsIdBySurveyId(@Param("surveyId") Long surveyId);

    /**
     * 공통 응답 최종값 조회
     *
     * @return
     */
    public TbSurveyCmmnRspns selectCmmnRspnsByMaxRspnsOdr();

    /**
     * 목록 삭제 - SURVEY_DEFINITION_ID(으)로
     *
     * @param surveyDefinitionId
     * @return
     */
    public int deleteListBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

}
