package kr.ac.jj.survey.application.qestnr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 설문지 Controller
 */
@Controller
@RequestMapping(path = "/qestnr/Questionnaire")
public class QuestionnaireController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/qestnr/QuestionnaireView";
    }

}
