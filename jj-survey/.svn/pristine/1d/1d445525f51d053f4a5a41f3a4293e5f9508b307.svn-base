package kr.ac.jj.survey.application.result.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.shared.infrastructure.util.BaseUtil;
import kr.ac.jj.survey.application.result.model.SurveyResultModel;
import kr.ac.jj.survey.application.result.service.SurveyResultServiceImpl;

/**
 * 설문 결과 RestController
 */
@RestController
@RequestMapping(path = "/result/SurveyResult", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class SurveyResultRestController {

    private @Autowired SurveyResultServiceImpl surveyResultService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        surveyResultService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param surveyQestnrId
     * @return
     */
    @GetMapping(path = "/{surveyQestnrId}")
    public SurveyResultModel get(@PathVariable String surveyQestnrId) {
        return surveyResultService.read(surveyQestnrId);
    }

    /**
     * 엑셀 생성
     *
     * @param surveyQestnrId
     * @return
     */
    @PostMapping(path = "/{surveyQestnrId}", params = { "createExcel" })
    public String createExcel(@PathVariable String surveyQestnrId) {
        File tempFile = BaseUtil.getTempFile();

        surveyResultService.createExcel(surveyQestnrId, tempFile);

        return tempFile.getName();
    }

}
