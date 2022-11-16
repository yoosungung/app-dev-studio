package kr.ac.jj.survey.application.qestnr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.qestnr.service.QuestionnaireServiceImpl;

/**
 * 설문지 RestController
 */
@RestController
@RequestMapping(path = "/qestnr/Questionnaire", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class QuestionnaireRestController {

    private @Autowired QuestionnaireServiceImpl questionnaireService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        questionnaireService.readList(gridRequest);
    }

}
