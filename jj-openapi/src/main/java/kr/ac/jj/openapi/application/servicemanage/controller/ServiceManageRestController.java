package kr.ac.jj.openapi.application.servicemanage.controller;

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

import kr.ac.jj.openapi.application.servicemanage.model.ServiceManageModel;
import kr.ac.jj.openapi.application.servicemanage.service.ServiceManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 서비스 관리 RestController
 */
@RestController
@RequestMapping(path = "/servicemanage/ServiceManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ServiceManageRestController {

    private @Autowired ServiceManageServiceImpl serviceManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        serviceManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public ServiceManageModel get() {
        return serviceManageService.read();
    }

    /**
     * 조회
     *
     * @param svcId
     * @return
     */
    @GetMapping(path = "/{svcId}")
    public ServiceManageModel get(@PathVariable String svcId) {
        return serviceManageService.read(svcId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody ServiceManageModel model) {
        String svcId = serviceManageService.create(model);

        return svcId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{svcId}")
    public void put(@RequestBody ServiceManageModel model) {
        serviceManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param svcId
     */
    @DeleteMapping(path = "/{svcId}")
    public void delete(@PathVariable String svcId) {
        serviceManageService.delete(svcId);
    }

}
