package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.SurveyDefinitionStatus;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 통계
 */
@MainSqlMapper
public interface SurveyDefinitionStatusMapper<T extends Serializable> {

    /**
     * 기관유형별 통계
     */
    List<SurveyDefinitionStatus> getOrganStatus(@Param("surveyDefinitionId") long surveyDefinitionId);

    /**
     * 지역별 통계
     */
    List<SurveyDefinitionStatus> getAreaStatus(@Param("surveyDefinitionId") long surveyDefinitionId);

}
