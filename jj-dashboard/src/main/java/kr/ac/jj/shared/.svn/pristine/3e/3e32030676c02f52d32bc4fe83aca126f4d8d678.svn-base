package kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.controller;

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

import kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.model.ResourceManageModel;
import kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.service.ResourceManageServiceImpl;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.shared.infrastructure.security.intercept.ReloadableFilterInvocationSecurityMetadataSource;

/**
 * 리소스 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/sysmanage/resrcemanage/ResourceManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ResourceManageRestController {

    private @Autowired ResourceManageServiceImpl resourceManageService;
    private @Autowired ReloadableFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        resourceManageService.readList(gridRequest);
    }

    /**
     * 목록 순서 저장
     *
     * @param ordrList
     */
    @PostMapping(path = "/updateOrdrList")
    public void updateOrdrList(@RequestBody BaseMapList ordrList) {
        resourceManageService.updateOrdrList(ordrList);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public ResourceManageModel get() {
        return resourceManageService.read();
    }

    /**
     * 조회
     *
     * @param resrceId
     * @return
     */
    @GetMapping(path = "/{resrceId}")
    public ResourceManageModel get(@PathVariable String resrceId) {
        return resourceManageService.read(resrceId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody ResourceManageModel model) {
        String resrceId = resourceManageService.create(model);

        filterInvocationSecurityMetadataSource.reloadRequestMap();

        return resrceId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{resrceId}")
    public void put(@RequestBody ResourceManageModel model) {
        resourceManageService.update(model);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }

    /**
     * 삭제
     *
     * @param resrceId
     */
    @DeleteMapping(path = "/{resrceId}")
    public void delete(@PathVariable String resrceId) {
        resourceManageService.delete(resrceId);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }

}
