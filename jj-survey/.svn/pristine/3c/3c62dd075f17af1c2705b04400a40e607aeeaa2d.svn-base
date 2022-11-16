package com.jd.survey.pub.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.survey.com.BaseCmd.SortOrder;
import com.jd.survey.domain.security.User;
import com.jd.survey.domain.settings.Question;
import com.jd.survey.domain.settings.SurveyDefinitionPage;
import com.jd.survey.domain.survey.QuestionAnswer;
import com.jd.survey.domain.survey.Survey;
import com.jd.survey.domain.survey.SurveyPage;
import com.jd.survey.pub.domain.SurveyDefinition;
import com.jd.survey.pub.domain.SurveyDefinitionStatus;
import com.jd.survey.pub.service.PubSurveyService;
import com.jd.survey.pub.service.SurveyDefinitionService;
import com.jd.survey.pub.web.cmd.SurveyDefinitionCmd;
import com.jd.survey.pub.web.cmd.SurveySecUserDefinitionCmd;
import com.jd.survey.service.security.UserService;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.service.survey.SurveyService;

@RequestMapping({ "/pub/survey" })
@Controller("survey.pub.SurveyDefinitionController")
public class SurveyDefinitionController {

    // ~ Static fields/initializers
    // =========================================================

    private static final String DATE_FORMAT = "date_format";

    // ~ Instance fields
    // ====================================================================

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private PubSurveyService pubSurveyService;

    @Autowired
    private SurveySettingsService surveySettingsService;

    @Autowired
    private SurveyDefinitionService surveyDefinitionService;

    /*설문조사----------------------------------------------------------------------------------------*/

    // 설문조사 검색
    @RequestMapping(value = { "/research" }, produces = "text/html")
    public String researchList(SurveyDefinitionCmd cmd, ModelMap model) {

        model.addAttribute("cmd", surveyDefinitionService.searchSurveyDefinition(cmd));
        model.addAttribute("surveyType", "research");
        return "pub/survey/research/search";
    }

    // 진행중 설문조사들
    @Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_SURVEY_ADMIN" })
    @RequestMapping(value = { "/research/{id}" }, params = "process", produces = "text/html")
    public String process(@PathVariable long id, Principal principal, ModelMap model,
            HttpServletRequest httpServletRequest) {

        if (surveyDefinitionService.getSurveyDefinition(id).getStatus().equals("E")) {
            return "redirect:/pub/survey/research/" + id;
        } else {
            List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();

            List<SurveyDefinition> processSurveyList = surveyDefinitionService.getProcessSurveyList();

            long nextId = 0, previousId = 0;

            for (SurveyDefinition processSurvey : processSurveyList) {

                if (id == processSurvey.getSurveyDefinitionId()) {
                    nextId = getNextId(processSurveyList, processSurvey);
                    previousId = getPreviousId(processSurveyList, processSurvey);

                    if (principal != null && pubSurveyService.getSurveyCount(processSurvey.getSurveyDefinitionId(),
                            principal.getName()) > 0) {
                        List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(
                                pubSurveyService.getSurvey(processSurvey.getSurveyDefinitionId(), principal.getName())
                                        .getSurveyId(),
                                messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

                        processSurvey.setQuestionAnswers(surveyPages.get(0).getQuestionAnswers());
                        processSurvey.setAnswerStatus(pubSurveyService
                                .getSurvey(processSurvey.getSurveyDefinitionId(), principal.getName()).getStatus());
                        processSurvey.setSurveyId(pubSurveyService
                                .getSurvey(processSurvey.getSurveyDefinitionId(), principal.getName()).getSurveyId());
                    } else {
                        for (SurveyDefinitionPage surveyDefinitionPage : surveySettingsService
                                .surveyDefinition_findById(processSurvey.getSurveyDefinitionId()).getPages()) {
                            for (Question question : surveyDefinitionPage.getQuestions()) {
                                questionAnswers.add(new QuestionAnswer(question));
                            }
                        }
                        processSurvey.setQuestionAnswers(questionAnswers);
                    }
                    model.addAttribute("surveyDefinition", processSurvey);
                }
            }

            model.addAttribute("nextId", nextId);
            model.addAttribute("previousId", previousId);
            model.addAttribute("surveyType", "research");

            // 최신설문조사
            model.addAttribute("newestSurveyList", newestSurveyList());
            return "pub/survey/research/process";
        }

    }

    // 설문조사 투표
    @Secured({ "ROLE_USER", "ROLE_ADMIN", "ROLE_SURVEY_ADMIN" })
    @RequestMapping(value = { "/research" }, method = RequestMethod.POST, produces = "text/html")
    public String vote(@Valid SurveyPage surveyPage, Principal principal, ModelMap model,
            HttpServletRequest httpServletRequest) {

        Survey survey = surveyService.survey_create(surveyPage.getSurvey().getTypeId(), principal.getName(),
                httpServletRequest.getRemoteAddr());

        surveyPage.setSurvey(survey);
        surveyPage = surveyService.surveyPage_updateSettings(surveyPage);

        surveyService.surveyPage_update(surveyPage,
                messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
        surveyService.survey_submit(survey.getId());

        model.clear();
        return "redirect:/pub/survey/research/" + surveyPage.getSurvey().getTypeId() + "/?process";
    }

    // 종료된 설문조사
    @RequestMapping(value = { "/research/{id}" }, produces = "text/html")
    public String done(@PathVariable long id, Principal principal, ModelMap model,
            HttpServletRequest httpServletRequest) {

        if (principal != null && pubSurveyService.getSurveyCount(id, principal.getName()) > 0) {
            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(
                    pubSurveyService.getSurvey(id, principal.getName()).getSurveyId(),
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            model.addAttribute("surveyPages", surveyPages);
        } else {
            model.addAttribute("surveyDefinition", surveySettingsService.surveyDefinition_findById(id));
        }

        model.addAttribute("survey", surveyDefinitionService.getSurveyDefinition(id));
        model.addAttribute("surveyType", "research");
        // 최신설문조사
        model.addAttribute("newestSurveyList", newestSurveyList());
        return "pub/survey/research/done";
    }

    /*실태조사----------------------------------------------------------------------------------------*/

    // 실태조사 대상검색
    @RequestMapping(value = { "/targets/{id}" }, produces = "text/html")
    public String targetSearch(@PathVariable long id, SurveySecUserDefinitionCmd cmd, ModelMap model) {

        if (cmd.getSurveyDefinitionId() == 0) {
            cmd.setSort("creationDate");
            cmd.setSortOrder(SortOrder.asc);
            cmd.setKeywords(null);
            cmd.setStatus(null);
            cmd.setPage(1);
        }

        for (SurveyDefinition surveyDefinition : surveyDefinitionService.getRealSurveyDefinitionList()) {
            if (id == surveyDefinition.getDepartmentId())
                cmd.setSurveyDefinitionId(surveyDefinition.getSurveyDefinitionId());
        }

        cmd.setDepartmentId(id);
        model.addAttribute("cmd", surveyDefinitionService.searchTargets(cmd));
        model.addAttribute("surveyDefinitionId", cmd.getSurveyDefinitionId());
        return "pub/survey/targets";
    }

    // 시설장비 관리 실태조사
    @RequestMapping(value = { "/equip" }, produces = "text/html")
    public String equipShow(Principal principal, ModelMap model) {

        model.addAttribute("surveyDefinition", realSurvey(2, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "equip");
        return "pub/survey/equip/show";
    }

    // 전담운영인력 실태조사
    @RequestMapping(value = { "/oper" }, produces = "text/html")
    public String operShow(Principal principal, ModelMap model) {

        model.addAttribute("surveyDefinition", realSurvey(3, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "oper");
        return "pub/survey/oper/show";
    }

    // 대형연구시설 실태조사
    @RequestMapping(value = { "/wolf" }, produces = "text/html")
    public String wolfShow(Principal principal, ModelMap model) {

        model.addAttribute("surveyDefinition", realSurvey(4, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "wolf");
        return "pub/survey/wolf/show";
    }

    // 시설장비 관리 실태조사 우수 연구기관
    @RequestMapping(value = { "/equip" }, params = "best", produces = "text/html")
    public String equipBest(Principal principal, ModelMap model) {
        model.addAttribute("surveyDefinition", realSurvey(2, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "equip");
        return "pub/survey/equip/best";
    }

    // 전담운영인력 실태조사 우수 연구기관
    @RequestMapping(value = { "/oper" }, params = "best", produces = "text/html")
    public String operBest(Principal principal, ModelMap model) {
        model.addAttribute("surveyDefinition", realSurvey(3, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "oper");
        return "pub/survey/oper/best";
    }

    // 대형연구시설 실태조사 우수 연구기관
    @RequestMapping(value = { "/wolf" }, params = "best", produces = "text/html")
    public String wolfBest(Principal principal, ModelMap model) {
        model.addAttribute("surveyDefinition", realSurvey(4, (principal != null ? principal.getName() : "")));
        model.addAttribute("surveyType", "wolf");
        return "pub/survey/wolf/best";
    }

    /*methods----------------------------------------------------------------------------------------*/

    // 조사대상 개인별 확인
    @RequestMapping(value = { "/{id}" }, produces = "application/json; charset=utf-8")
    public @ResponseBody int UserSurveyDefinitionCount(@PathVariable long id, Principal principal) {
        User user = userService.user_findByLogin(principal.getName());
        return surveyDefinitionService.getUserSurveyDefinitionCount(id, user.getId());
    }

    // 조사 기관유형/지역별 통계
    @RequestMapping(value = { "/status/{id}/{order}" }, produces = "application/json; charset=utf-8")
    public @ResponseBody List<SurveyDefinitionStatus> surveyDefinitionStatus(@PathVariable long id,
            @PathVariable int order) {
        if (order == 1)
            return pubSurveyService.getOrganStatus(id);
        else
            return pubSurveyService.getAreaStatus(id);
    }

    // 최신설문조사
    List<SurveyDefinition> newestSurveyList() {
        SurveyDefinitionCmd cmd = new SurveyDefinitionCmd();
        cmd.setPage(8);
        return surveyDefinitionService.getSurveyDefinitionList(cmd);
    }

    // 로그인에 따른 실태조사 조회
    SurveyDefinition realSurvey(long id, String login) {
        List<SurveyDefinition> realSurveylist = new ArrayList<SurveyDefinition>();
        SurveyDefinition realSurveyDefinition = null;

        if (StringUtils.hasText(login)) {
            realSurveylist = surveyDefinitionService.getMainRealSurveyDefinitionListByLogin(login);
        } else {
            realSurveylist = surveyDefinitionService.getRealSurveyDefinitionList();
        }

        for (SurveyDefinition surveyDefinition : realSurveylist) {
            if (id == surveyDefinition.getDepartmentId()) {
                realSurveyDefinition = surveyDefinition;
            }
        }

        return realSurveyDefinition;
    }

    public long getNextId(List<SurveyDefinition> list, SurveyDefinition surveyDefinition) {
        int idx = list.indexOf(surveyDefinition);
        if (idx < 0 || idx + 1 == list.size())
            return 0;
        return list.get(idx + 1).getSurveyDefinitionId();
    }

    public long getPreviousId(List<SurveyDefinition> list, SurveyDefinition surveyDefinition) {
        int idx = list.indexOf(surveyDefinition);
        if (idx <= 0)
            return 0;
        return list.get(idx - 1).getSurveyDefinitionId();
    }

}
