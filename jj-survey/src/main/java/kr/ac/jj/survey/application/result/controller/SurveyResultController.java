package kr.ac.jj.survey.application.result.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 설문 결과 Controller
 */
@Controller
@RequestMapping(path = "/result/SurveyResult")
public class SurveyResultController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/result/SurveyResultView";
    }

}
