package kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPersonToPerson;

/**
 * 설문_설문지 - 사람(대상자) TO 사람 Mapper
 */
@MainSqlMapper
public interface TbSurveyQestnrPersonToPersonMapper {

    /**
     * 그리드 목록 조회 - SURVEY_QESTNR_ID(으)로
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectGridListBySurveyQestnrId(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 설문 발송 대상 사람 목록 조회
     *
     * @param surveyQestnrId
     * @param resultHandler
     */
    public void selectSurveySendTargetPersonList(@Param("surveyQestnrId") String surveyQestnrId,
            DataResultHandler<TbSurveyQestnrPersonToPerson> resultHandler);

    /**
     * 설문 미응답자 사람 목록 조회
     *
     * @param surveyQestnrId
     * @param resultHandler
     */
    public void selectSurveySendTargetUnAnswered(@Param("surveyQestnrId") String surveyQestnrId,
            DataResultHandler<TbSurveyQestnrPersonToPerson> resultHandler);

    /**
     * 발송 대상 사람 조회
     *
     * @param surveyQestnrId
     * @param resultHandler
     */
    public TbSurveyQestnrPersonToPerson selectSurveySendTargetPerson(@Param("surveyQestnrId") String surveyQestnrId,
            @Param("surveyPersonId") String surveyPersonId);

}
