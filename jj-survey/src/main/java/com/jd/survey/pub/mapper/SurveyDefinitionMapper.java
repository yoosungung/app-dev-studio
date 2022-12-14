package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.SurveyDefinition;
import com.jd.survey.pub.web.cmd.SurveyDefinitionCmd;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문조사
 */
@MainSqlMapper
public interface SurveyDefinitionMapper<T extends Serializable> {

    /**
     * 설문조사 조회
     */
    SurveyDefinition getSurveyDefinition(@Param("surveyDefinitionId") long surveyDefinitionId);

    /**
     * 설문조사 건수 조회
     */
    int getSurveyDefinitionCount(SurveyDefinitionCmd cmd);

    /**
     * 설문조사 목록
     */
    List<SurveyDefinition> getSurveyDefinitionList(SurveyDefinitionCmd cmd);

    /**
     * 메인 설문조사 통계
     */
    SurveyDefinition getMainStatics();

    /**
     * 메인 실태조사 조회
     */
    List<SurveyDefinition> getRealSurveyDefinitionList();

    /**
     * 개인별 조사완료 실태조사 목록
     */
    List<SurveyDefinition> getRealSurveyDefinitionListByLogin(String login);

}
