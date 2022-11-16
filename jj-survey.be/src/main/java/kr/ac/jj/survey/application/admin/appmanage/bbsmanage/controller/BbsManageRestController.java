package kr.ac.jj.survey.application.admin.appmanage.bbsmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.admin.appmanage.bbsmanage.model.BbsManageModel;
import kr.ac.jj.survey.application.admin.appmanage.bbsmanage.service.BbsManageServiceImpl;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

/**
 * 게시판 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/appmanage/bbsmanage/BbsManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class BbsManageRestController {
    private @Autowired BbsManageServiceImpl bbsManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        bbsManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param bbsId
     * @return
     */
    @GetMapping
    public BbsManageModel read(@RequestParam(required = false) String bbsId) {
        return bbsManageService.read(bbsId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String create(@RequestBody BbsManageModel model) {
        return bbsManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping
    public void update(@RequestBody BbsManageModel model) {
        bbsManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param bbsId
     */
    @DeleteMapping
    public void delete(@RequestBody String bbsId) {
        bbsManageService.delete(bbsId);
    }
}
