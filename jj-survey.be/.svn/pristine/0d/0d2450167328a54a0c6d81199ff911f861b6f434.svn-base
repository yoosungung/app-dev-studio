package kr.ac.jj.survey.application.admin.sysmanage.codemanage.controller;

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

import kr.ac.jj.survey.application.admin.sysmanage.codemanage.model.CodeManageModel;
import kr.ac.jj.survey.application.admin.sysmanage.codemanage.service.CodeManageServiceImpl;
import kr.ac.jj.survey.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

/**
 * 코드 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/sysmanage/codemanage/CodeManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CodeManageRestController {
    private @Autowired CodeManageServiceImpl codeManageService;
    private @Autowired CodeDataSourceAccessor codeDataSourceAccessor;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        codeManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param codeGroupId
     * @return
     */
    @GetMapping
    public CodeManageModel read(@RequestParam(required = false) String codeGroupId) {
        return codeManageService.read(codeGroupId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String create(@RequestBody CodeManageModel model) {
        String codeGroupId = codeManageService.create(model);

        codeDataSourceAccessor.refreshAll();

        return codeGroupId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping
    public void update(@RequestBody CodeManageModel model) {
        codeManageService.update(model);

        codeDataSourceAccessor.refreshAll();
    }

    /**
     * 삭제
     *
     * @param codeGroupId
     */
    @DeleteMapping
    public void update(@RequestBody String codeGroupId) {
        codeManageService.delete(codeGroupId);

        codeDataSourceAccessor.refreshAll();
    }
}
