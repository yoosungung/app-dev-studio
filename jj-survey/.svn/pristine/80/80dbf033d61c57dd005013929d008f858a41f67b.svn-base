package com.jd.survey.pub.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.pub.domain.SurveyDefinition;
import com.jd.survey.pub.mapper.SurveyDefinitionMapper;
import com.jd.survey.pub.mapper.SurveySecUserDefinitionMapper;
import com.jd.survey.pub.web.cmd.SurveyDefinitionCmd;
import com.jd.survey.pub.web.cmd.SurveySecUserDefinitionCmd;

@Transactional(readOnly = true)
@Service("SurveyDefinitionService")
public class SurveyDefinitionService {

    @Autowired
    private SurveyDefinitionMapper<Serializable> surveyDefinitionMapper;

    @Autowired
    private SurveySecUserDefinitionMapper<Serializable> surveySecUserDefinitionMapper;

    // 설문조사 검색
    public SurveyDefinitionCmd searchSurveyDefinition(SurveyDefinitionCmd cmd) {
        int total = surveyDefinitionMapper.getSurveyDefinitionCount(cmd);
        if (total > 0) {
            cmd.setTotal(total);
            cmd.setSource(surveyDefinitionMapper.getSurveyDefinitionList(cmd));
        }
        return cmd;
    }

    // 설문조사 조회
    public SurveyDefinition getSurveyDefinition(long surveyDefinitionId) {
        return surveyDefinitionMapper.getSurveyDefinition(surveyDefinitionId);
    }

    // 투표중 설문조사 목록
    public List<SurveyDefinition> getProcessSurveyList() {

        List<SurveyDefinition> surveyDefinitionList = new ArrayList<SurveyDefinition>();

        for (SurveyDefinition surveyDefinition : this.searchSurveyDefinition(new SurveyDefinitionCmd()).getPageList()) {
            if (surveyDefinition.getStatus().equals("P")) {
                surveyDefinitionList.add(surveyDefinition);
            }
        }
        return surveyDefinitionList;
    }

    // 메인 설문조사 목록
    public List<SurveyDefinition> getSurveyDefinitionList(SurveyDefinitionCmd cmd) {
        cmd.setTotal(surveyDefinitionMapper.getSurveyDefinitionCount(cmd));
        return surveyDefinitionMapper.getSurveyDefinitionList(cmd);
    }

    // 메인 설문조사 통계
    public SurveyDefinition getMainStatics() {
        return surveyDefinitionMapper.getMainStatics();
    }

    // 진행중인 설문조사 건수 조회
    public int getProcessingSurvey(SurveyDefinitionCmd cmd) {
        int processingCnt = 0;
        for (SurveyDefinition surveyDefinition : this.getSurveyDefinitionList(cmd)) {
            if (surveyDefinition.getStatus().equals("P")) {
                processingCnt += 1;
            }
        }
        return processingCnt;
    }

    // 개인별 조사완료 실태조사 목록
    public List<SurveyDefinition> getMainRealSurveyDefinitionListByLogin(String login) {

        if (login != null) {
            List<SurveyDefinition> mainRealSurveyDefinitionList = surveyDefinitionMapper.getRealSurveyDefinitionList();
            for (SurveyDefinition realSurveyDefinition : mainRealSurveyDefinitionList) {
                for (SurveyDefinition userSurveyDefinition : surveyDefinitionMapper
                        .getRealSurveyDefinitionListByLogin(login)) {
                    if (realSurveyDefinition.getSurveyDefinitionId() == userSurveyDefinition.getSurveyDefinitionId()) {
                        realSurveyDefinition.setRealStatus("S");
                        realSurveyDefinition.setSurveyId(userSurveyDefinition.getSurveyId());
                    }
                }
            }
            return mainRealSurveyDefinitionList;

        } else {
            return this.getRealSurveyDefinitionList();
        }
    }

    // 최근 실태조사 목록
    public List<SurveyDefinition> getRealSurveyDefinitionList() {
        return surveyDefinitionMapper.getRealSurveyDefinitionList();
    }

    // 조사대상 개별 확인
    public int getUserSurveyDefinitionCount(long surveyDefinitionId, long userId) {
        return surveySecUserDefinitionMapper.getUserSurveyDefinitionCount(surveyDefinitionId, userId);
    }

    // 조사설정별 대상자 목록
    public SurveySecUserDefinitionCmd searchTargets(SurveySecUserDefinitionCmd cmd) {
        int total = surveySecUserDefinitionMapper.targetsCountBySurveyDefinitionId(cmd);

        if (total > 0) {
            cmd.setTotal(total);
            cmd.setSource(surveySecUserDefinitionMapper.targetListBySurveyDefinitionId(cmd));
        }
        return cmd;
    }

}
