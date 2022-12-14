package kr.ac.jj.survey.application.result.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.application.result.model.SurveyResultProcessionLabel;
import kr.ac.jj.survey.application.result.model.SurveyResultQuestion;
import kr.ac.jj.survey.application.result.model.SurveyResultQuestionOption;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문 결과 Mapper
 */
@MainSqlMapper
public interface SurveyResultMapper {

    /**
     * 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 디바이스별 응답 목록 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public BaseMapList selectDeviceSubmitList(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 공통문항/만족도 응답 목록 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public BaseMapList selectCmmnRspnsList(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 공통문항/만족도 컬럼 목록 조회
     *
     * @param surveyQestnrId
     * @return
     */
    public List<String> selectCmmnColumnList(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 질문 목록 조회
     *
     * @param surveyDefinitionId
     * @param resultHandler
     */
    public void selectSurveyQuestionList(@Param("surveyDefinitionId") Long surveyDefinitionId,
            DataResultHandler<SurveyResultQuestion> resultHandler);

    /**
     * 질문 옵션 목록 조회
     *
     * @param surveyDefinitionId
     * @param resultHandler
     */
    public void selectSurveyQuestionOptionList(@Param("surveyDefinitionId") Long surveyDefinitionId,
            DataResultHandler<SurveyResultQuestionOption> resultHandler);

    /**
     * 행렬 라벨 목록 조회
     *
     * @param surveyDefinitionId
     * @param resultHandler
     */
    public void selectSurveyProcessionLabelList(@Param("surveyDefinitionId") Long surveyDefinitionId,
            DataResultHandler<SurveyResultProcessionLabel> resultHandler);

    /**
     * 응답 데이터 목록 조회
     *
     * @param surveyDefinitionId
     * @param resultHandler
     */
    public void selectSurveyDataList(@Param("surveyDefinitionId") Long surveyDefinitionId,
            DataResultHandler<Map<String, Object>> resultHandler);

}
