package kr.ac.jj.shared.application.admin.logmanage.emaillog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param emailId
     * @return
     */
    @GetMapping(path = "/{emailId}")
    public EmailLogManageModel get(@PathVariable String emailId) {
        return emailLogManageService.read(emailId);
    }

    /**
     * 생성
     *
     * @param method
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestParam("method") String method, @RequestBody EmailLogManageModel model) {
        if (StringUtils.equals(method, "create")) {
            return emailLogManageService.create(model);
        }

        if (StringUtils.equals(method, "send")) {
            emailLogManageService.send(model);
        }

        return null;
    }

    /**
     * 삭제
     *
     * @param emailId
     */
    @DeleteMapping(path = "/{emailId}")
    public void delete(@PathVariable String emailId) {
        emailLogManageService.delete(emailId);
    }

    /**
     * 발송 생성
     *
     * @param emailId
     */
    @PostMapping(path = "createSend")
    public void createSend(@RequestBody String emailId) {
        emailLogManageService.createSend(emailId);
    }

}
