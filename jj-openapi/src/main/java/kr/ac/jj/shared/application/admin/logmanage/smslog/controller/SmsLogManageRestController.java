package kr.ac.jj.shared.application.admin.logmanage.smslog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.logmanage.smslog.model.SmsLogManageModel;
import kr.ac.jj.shared.application.admin.logmanage.smslog.service.SmsLogManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 문자메시지 로그 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/logmanage/smslog/SmsLogManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class SmsLogManageRestController {

    private @Autowired SmsLogManageServiceImpl smsLogManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        smsLogManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param smsId
     * @return
     */
    @GetMapping(path = "/{smsId}")
    public SmsLogManageModel get(@PathVariable String smsId) {
        return smsLogManageService.read(smsId);
    }

    /**
     * 생성
     *
     * @param method
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestParam("method") String method, @RequestBody SmsLogManageModel model) {
        if (StringUtils.equals(method, "create")) {
            return smsLogManageService.create(model);
        }

        if (StringUtils.equals(method, "send")) {
            smsLogManageService.send(model);
        }

        return null;
    }

    /**
     * 재발송 처리
     *
     * @param smsId
     */
    @PutMapping(path = "/{smsId}", params = { "reSend" })
    public void put(@PathVariable String smsId) {
        smsLogManageService.updateReSend(smsId);
    }

    /**
     * 삭제
     *
     * @param smsId
     */
    @DeleteMapping(path = "/{smsId}")
    public void delete(@PathVariable String smsId) {
        smsLogManageService.delete(smsId);
    }

}
