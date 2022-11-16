package kr.ac.jj.survey.application.realmmanage.controller;

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
import kr.ac.jj.survey.application.realmmanage.model.RealmManageModel;
import kr.ac.jj.survey.application.realmmanage.service.RealmManageServiceImpl;

/**
 * 설문 분야 관리 관리 RestController
 */
@RestController
@RequestMapping(path = "/realmmanage/RealmManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RealmManageRestController {

    private @Autowired RealmManageServiceImpl realmManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        realmManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public RealmManageModel get() {
        return realmManageService.read();
    }

    /**
     * 조회
     *
     * @param personId
     * @return
     */
    @GetMapping(path = "/{surveyRealmId}")
    public RealmManageModel get(@PathVariable String surveyRealmId) {
        return realmManageService.read(surveyRealmId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody RealmManageModel model) {
        return realmManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{surveyRealmId}")
    public void put(@RequestBody RealmManageModel model) {
        realmManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param personId
     */
    @DeleteMapping(path = "/{surveyRealmId}")
    public void delete(@PathVariable String surveyRealmId) {
        realmManageService.delete(surveyRealmId);
    }

}
