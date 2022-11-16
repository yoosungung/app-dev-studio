package kr.ac.jj.shared.application.admin.logmanage.loginlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.loginlog.service.LoginLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 접속 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/loginlog/LoginLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class LoginLogManageRestController {

    private @Autowired LoginLogManageServiceImpl loginLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        loginLogManageService.readList(gridRequest);
    }

}
