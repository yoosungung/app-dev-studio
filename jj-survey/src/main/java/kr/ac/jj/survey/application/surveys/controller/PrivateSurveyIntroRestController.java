package kr.ac.jj.survey.application.surveys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.surveys.model.PrivateSurveyIntroModel;
import kr.ac.jj.survey.application.surveys.service.PrivateSurveyIntroServiceImpl;

/**
 * 비공개 설문 시작 RestController
 */
@RestController
@RequestMapping({ "/surveys/private/PrivateSurveyIntro" })
public class PrivateSurveyIntroRestController {

    private @Autowired PrivateSurveyIntroServiceImpl privateSurveyIntroService;

    /**
     * 조회
     *
     * @param surveyId
     * @return
     */
    @GetMapping(path = "/{surveyId}")
    public PrivateSurveyIntroModel get(@PathVariable Long surveyId) {
        return privateSurveyIntroService.read(surveyId);
    }

    /**
     * 저장
     *
     * @param surveyId
     * @param model
     */
    @PutMapping(path = "/{surveyId}")
    public void put(@PathVariable Long surveyId, @RequestBody PrivateSurveyIntroModel model) {
        privateSurveyIntroService.update(surveyId, model);
    }

}
