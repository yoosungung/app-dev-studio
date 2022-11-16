package com.jd.survey.pub.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.pub.domain.Survey;
import com.jd.survey.pub.domain.SurveyDefinitionStatus;
import com.jd.survey.pub.mapper.SurveyDefinitionStatusMapper;
import com.jd.survey.pub.mapper.SurveyMapper;
import com.jd.survey.pub.mapper.SurveySecUserDefinitionMapper;

@Transactional(readOnly = true)
@Service("PubSurveyService")
public class PubSurveyService {
	
	@Autowired private SurveyMapper<Serializable> surveyMapper;
	
	@Autowired private SurveyDefinitionStatusMapper<Serializable> surveyDefinitionStatusMapper;
	
	@Autowired private SurveySecUserDefinitionMapper<Serializable> secUserSurveyDefinitionMapper;
	
	//설문조사 조회
	public Survey getSurvey(long surveyDefinitionId, String login) {
		return surveyMapper.getSurvey(surveyDefinitionId, login);
	}
	
	// 진행중인 설문조사 건수 조회
	public int getSurveyCount(long surveyDefinitionId, String login) {
		return surveyMapper.getSurveyCount(surveyDefinitionId, login);
	}
	
	// 설문조사 설정 기관유형별 통계
	public List<SurveyDefinitionStatus> getOrganStatus(long surveyDefinitionId) {
		return surveyDefinitionStatusMapper.getOrganStatus(surveyDefinitionId);
	}
	
	// 설문조사 설정 기관유형별 통계
	public List<SurveyDefinitionStatus> getAreaStatus(long surveyDefinitionId) {
		return surveyDefinitionStatusMapper.getAreaStatus(surveyDefinitionId);
	}
	
}
