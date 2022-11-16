package kr.ac.jj.shared.application.admin.logmanage.emaillog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.emaillog.model.EmailLogManageModel;
import kr.ac.jj.shared.application.admin.logmanage.emaillog.service.EmailLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 이메일 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/emaillog/EmailLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class EmailLogManageRestController {

    private @Autowired EmailLogManageServiceImpl emailLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        emailLogManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param logId
     * @return
     */
    @GetMapping(path = "/{logId}")
    public EmailLogManageModel put(@PathVariable String logId) {
        return emailLogManageService.read(logId);
    }

    /**
     * 삭제
     *
     * @param logId
     */
    @DeleteMapping(path = "/{logId}")
    public void delete(@PathVariable String logId) {
        emailLogManageService.delete(logId);
    }

}
