package kr.ac.jj.openapi.application.keystatus.controller;

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

import kr.ac.jj.openapi.application.keystatus.model.KeyStatusModel;
import kr.ac.jj.openapi.application.keystatus.service.KeyStatusServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 키 신청 현황 RestController
 */
@RestController
@RequestMapping(path = "/keystatus/KeyStatus", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class KeyStatusRestController {

    private @Autowired KeyStatusServiceImpl keyStatusService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        keyStatusService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public KeyStatusModel get() {
        return keyStatusService.read();
    }

    /**
     * 조회
     *
     * @param svcKeyId
     * @return
     */
    @GetMapping(path = "/{svcKeyId}")
    public KeyStatusModel get(@PathVariable String svcKeyId) {
        return keyStatusService.read(svcKeyId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody KeyStatusModel model) {
        String svcKeyId = keyStatusService.create(model);

        return svcKeyId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{svcKeyId}")
    public void put(@RequestBody KeyStatusModel model) {
        keyStatusService.update(model);
    }

    /**
     * 삭제
     *
     * @param svcKeyId
     */
    @DeleteMapping(path = "/{svcKeyId}")
    public void delete(@PathVariable String svcKeyId) {
        keyStatusService.delete(svcKeyId);
    }

}
