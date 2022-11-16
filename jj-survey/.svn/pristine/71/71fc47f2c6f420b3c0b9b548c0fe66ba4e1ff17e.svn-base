package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.SurveySecUserDefinition;
import com.jd.survey.pub.web.cmd.SurveySecUserDefinitionCmd;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 사용자 설문조사
 */
@MainSqlMapper
public interface SurveySecUserDefinitionMapper<T extends Serializable> {

    /**
     * 사용자설문조사 건수
     */
    int getUserSurveyDefinitionCount(@Param("surveyDefinitionId") Long surveyDefinitionId,
            @Param("userId") Long userId);

    /**
     * 사용자설문조사 목록 조회
     */
    List<SurveySecUserDefinition> getUserSurveyDefinitionsBySurveyDefinitionId(
            @Param("surveyDefinitionId") long surveyDefinitionId);

    /**
     * 사용자설문조사 등록
     */
    int addUserSurveyDefinition(SurveySecUserDefinition userSurveyDefinition);

    /**
     * 사용자설문조사 전체삭제
     */
    int delUserSurveyDefinitionsBySurveyDefinitionId(@Param("surveyDefinitionId") long surveyDefinitionId);

    /**
     * 사용자설문조사 개별삭제
     */
    int delUserSurveyDefinitionBySurveyDefinitionIdAndUserId(SurveySecUserDefinition userSurveyDefinition);

    /**
     * 조사설정별 대상자 건수
     */
    int targetsCountBySurveyDefinitionId(SurveySecUserDefinitionCmd cmd);

    /**
     * 조사설정별 대상자 목록
     */
    List<SurveySecUserDefinition> targetListBySurveyDefinitionId(SurveySecUserDefinitionCmd cmd);

}
