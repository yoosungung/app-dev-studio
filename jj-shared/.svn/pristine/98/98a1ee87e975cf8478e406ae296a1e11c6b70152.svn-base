package kr.ac.jj.shared.application.admin.logmanage.actionlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.actionlog.model.ActionLogManageModel;
import kr.ac.jj.shared.application.admin.logmanage.actionlog.service.ActionLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 액션 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/actionlog/ActionLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ActionLogManageRestController {

    private @Autowired ActionLogManageServiceImpl actionLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        actionLogManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param logId
     * @return
     */
    @GetMapping(path = "/{logId}")
    public ActionLogManageModel get(@PathVariable String logId) {
        return actionLogManageService.read(logId);
    }

    /**
     * 삭제
     *
     * @param logId
     */
    @DeleteMapping(path = "/{logId}")
    public void delete(@PathVariable String logId) {
        actionLogManageService.delete(logId);
    }

}
