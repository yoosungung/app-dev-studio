package com.jd.survey.pub.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jd.survey.pub.service.SurveyDefinitionService;
import com.jd.survey.pub.web.cmd.SurveyDefinitionCmd;
import com.jd.survey.zeus.service.ZeusQuestionAndAnswerService;

//@RequestMapping({"/pub/main"})
//@Controller
public class MainController {

    @Autowired
    private ZeusQuestionAndAnswerService zeusQuestionAndAnswerService;

    @Autowired
    private SurveyDefinitionService surveyDefinitionService;

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String main(SurveyDefinitionCmd cmd, Principal principal, ModelMap model) {

        // 현재 진행중인 설문조사 건수에 따른 조회
        if (surveyDefinitionService.getProcessingSurvey(cmd) > 0) {
            model.addAttribute("surveyList", surveyDefinitionService.getSurveyDefinitionList(cmd));
        } else {
            cmd.setPageSize(2);
            model.addAttribute("surveyList", surveyDefinitionService.getSurveyDefinitionList(cmd));
        }

        // 로그인에 따른 실태조사 상태별 목록
        if (principal != null) {
            model.addAttribute("actualSurveyList",
                    surveyDefinitionService.getMainRealSurveyDefinitionListByLogin(principal.getName()));
        } else {
            model.addAttribute("actualSurveyList", surveyDefinitionService.getRealSurveyDefinitionList());
        }

        // 최신설문조사
        cmd.setPageSize(6);
        model.addAttribute("newestSurveyList", surveyDefinitionService.getSurveyDefinitionList(cmd));
        // 설문조사 현황
        model.addAttribute("surveyStatics", surveyDefinitionService.getMainStatics());
        // qna
        // model.addAttribute("qnaList",
        // zeusQuestionAndAnswerService.getQuestionAndAnswerList());

        return "pub/main";
    }

}
