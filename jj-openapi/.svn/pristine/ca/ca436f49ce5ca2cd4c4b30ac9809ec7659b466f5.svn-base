package kr.ac.jj.openapi.application.servicelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.openapi.application.servicelog.service.ApiLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * API 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/apilog/ApiLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ApiLogManageRestController {

    private @Autowired ApiLogManageServiceImpl apiLogManageService;
    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        apiLogManageService.readList(gridRequest);
    }

}
