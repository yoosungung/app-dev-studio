package com.jd.survey.pub.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.survey.domain.settings.SurveyDefinitionPage;

/**
 * 설문조사 페이지 설정
 */
public interface SurveyDefinitionPageMapper<T extends Serializable> {
	
	/**
	 * 설문조사 페이지 설정 목록 
	 */
	List<SurveyDefinitionPage> getSurveyDefinitionPageList(@Param("surveyDefinitionId") String surveyDefinitionId);
}