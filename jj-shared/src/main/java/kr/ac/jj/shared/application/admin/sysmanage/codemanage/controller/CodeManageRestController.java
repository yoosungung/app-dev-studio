package kr.ac.jj.shared.application.admin.sysmanage.codemanage.controller;

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

import kr.ac.jj.shared.application.admin.sysmanage.codemanage.model.CodeManageModel;
import kr.ac.jj.shared.application.admin.sysmanage.codemanage.service.CodeManageServiceImpl;
import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

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
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public CodeManageModel get() {
        return codeManageService.read();
    }

    /**
     * 조회
     *
     * @param codeGroupId
     * @return
     */
    @GetMapping(path = "/{codeGroupId}")
    public CodeManageModel get(@PathVariable String codeGroupId) {
        return codeManageService.read(codeGroupId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody CodeManageModel model) {
        String codeGroupId = codeManageService.create(model);

        codeDataSourceAccessor.refreshAll();

        return codeGroupId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{codeGroupId}")
    public void put(@RequestBody CodeManageModel model) {
        codeManageService.update(model);

        codeDataSourceAccessor.refreshAll();
    }

    /**
     * 삭제
     *
     * @param codeGroupId
     */
    @DeleteMapping(path = "/{codeGroupId}")
    public void delete(@PathVariable String codeGroupId) {
        codeManageService.delete(codeGroupId);

        codeDataSourceAccessor.refreshAll();
    }

}
