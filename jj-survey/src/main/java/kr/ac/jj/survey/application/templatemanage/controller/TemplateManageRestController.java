package kr.ac.jj.survey.application.templatemanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.templatemanage.model.TemplateManageModel;
import kr.ac.jj.survey.application.templatemanage.service.TemplateManageServiceImpl;

/**
 * 템플릿 관리 RestController
 */
@RestController
@RequestMapping(path = "/templatemanage/TemplateManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class TemplateManageRestController {

    private @Autowired TemplateManageServiceImpl templateManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        templateManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public TemplateManageModel get() {
        return templateManageService.read();
    }

    /**
     * 조회
     *
     * @param surveyTmplatId
     * @return
     */
    @GetMapping(path = "/{surveyTmplatId}")
    public TemplateManageModel get(@PathVariable String surveyTmplatId) {
        return templateManageService.read(surveyTmplatId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody TemplateManageModel model) {
        return templateManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{surveyTmplatId}")
    public void put(@RequestBody TemplateManageModel model) {
        templateManageService.update(model);
    }

    /**
     * 수정 - 상태값
     *
     * @param surveyTmplatId
     * @param tmplatSttus
     */
    @PutMapping(path = "/{surveyTmplatId}", params = { "updateStatus" })
    public void put(@PathVariable String surveyTmplatId, @RequestBody String tmplatSttus) {
        templateManageService.updateStatus(surveyTmplatId, tmplatSttus);
    }

    /**
     * 삭제
     *
     * @param surveyTmplatId
     */
    @DeleteMapping(path = "/{surveyTmplatId}")
    public void delete(@PathVariable String surveyTmplatId) {
        templateManageService.delete(surveyTmplatId);
    }

}
