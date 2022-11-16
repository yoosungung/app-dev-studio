package com.jd.survey.pub.mapper;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.pub.domain.Survey;

/**
 * 설문조사
 */
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