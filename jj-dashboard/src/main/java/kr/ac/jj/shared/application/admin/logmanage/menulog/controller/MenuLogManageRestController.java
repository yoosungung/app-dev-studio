package kr.ac.jj.shared.application.admin.logmanage.menulog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.menulog.model.MenuLogManageModel;
import kr.ac.jj.shared.application.admin.logmanage.menulog.service.MenuLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 메뉴 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/menulog/MenuLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MenuLogManageRestController {

    private @Autowired MenuLogManageServiceImpl menuLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        menuLogManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param logId
     * @return
     */
    @GetMapping(path = "/{logId}")
    public MenuLogManageModel get(@PathVariable String logId) {
        return menuLogManageService.read(logId);
    }

    /**
     * 삭제
     *
     * @param logId
     */
    @DeleteMapping(path = "/{logId}")
    public void delete(@PathVariable String logId) {
        menuLogManageService.delete(logId);
    }

}
