package kr.ac.jj.openapi.application.keymanage.controller;

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

import kr.ac.jj.openapi.application.keymanage.model.KeyManageModel;
import kr.ac.jj.openapi.application.keymanage.service.KeyManageServiceImpl;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 키 발급 관리 RestController
 */
@RestController
@RequestMapping(path = "/keymanage/KeyManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class KeyManageRestController {

    private @Autowired KeyManageServiceImpl keyManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        keyManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param svcKeyId
     * @return
     */
    @GetMapping(path = "/{svcKeyId}")
    public KeyManageModel get(@PathVariable String svcKeyId) {
        return keyManageService.read(svcKeyId);
    }

    /**
     * 검토(승인/반려)
     *
     * @param model
     */
    @PutMapping(path = "/{svcKeyId}")
    public void put(@RequestBody KeyManageModel model) {
        keyManageService.update(model);
    }

    /**
     * 메일 발송
     *
     * @param surveyQestnrId
     * @param surveyPersonId
     */
    @PutMapping(path = "/{svcKeyId}", params = { "sendMail" })
    public void sendMail(@RequestBody TbApiSvcKey tbApiSvcKey) {
        keyManageService.sendMail(tbApiSvcKey);
    }

    /**
     * 삭제
     *
     * @param model
     */
    @DeleteMapping(path = "/{svcKeyId}")
    public void delete(@PathVariable String svcKeyId) {
        keyManageService.delete(svcKeyId);
    }

}
