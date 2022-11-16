package com.jd.survey.pub.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.Survey;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문조사
 */
@MainSqlMapper(value = "com.jd.surveyMapper")
public interface SurveyMapper<T extends Serializable> {

    /**
     * 설문조사 조회
     */
    Survey getSurvey(@Param("surveyDefinitionId") long surveyDefinitionId, @Param("login") String login);

    /**
     * 설문조사 건수 조회
     */
    int getSurveyCount(@Param("surveyDefinitionId") long surveyDefinitionId, @Param("login") String login);

    /**
     * 설문조사 수정으로 상태 변경
     */
    int modSurveyStatus(@Param("surveyId") long surveyId, @Param("login") String login);

}
