package kr.ac.jj.survey.domain.main.mapper.survey.qestnr;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;

/**
 * 설문_설문지 Mapper
 */
@MainSqlMapper
public interface TbSurveyQestnrMapper extends TbSurveyQestnrEntityMapper {

    /**
     * 조회 - SURVEY_DEFINITION_ID로
     *
     * @param surveyDefinitionId
     * @return
     */
    public TbSurveyQestnr selectBySurveyDefinitionId(@Param("surveyDefinitionId") Long surveyDefinitionId);

    /**
     * SURVEY_QESTNR_ID 조회 - SURVEY_ID로
     *
     * @param surveyId
     * @return
     */
    public String selectSurveyQestnrIdBySurveyId(@Param("surveyId") Long surveyId);

    /**
     * 설문 발송 대상 목록 조회
     *
     * @return
     */
    public List<TbSurveyQestnr> selectSurveySendTargetList();

    /**
     * 수정 - 발송 여부
     *
     * @param tbSurveyQestnr
     * @return
     */
    public int updateSndngYn(@Param("tbSurveyQestnr") TbSurveyQestnr tbSurveyQestnr);

}
