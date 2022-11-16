package kr.ac.jj.shared.application.admin.logmanage.errorlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.errorlog.model.ErrorLogManageModel;
import kr.ac.jj.shared.application.admin.logmanage.errorlog.service.ErrorLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 에러 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/errorlog/ErrorLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ErrorLogManageRestController {

    private @Autowired ErrorLogManageServiceImpl errorLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        errorLogManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param errorLogId
     * @return
     */
    @GetMapping(path = "/{errorLogId}")
    public ErrorLogManageModel get(@PathVariable String errorLogId) {
        return errorLogManageService.read(errorLogId);
    }

    /**
     * 삭제
     *
     * @param errorLogId
     */
    @DeleteMapping(path = "/{errorLogId}")
    public void delete(@PathVariable String errorLogId) {
        errorLogManageService.delete(errorLogId);
    }

}
