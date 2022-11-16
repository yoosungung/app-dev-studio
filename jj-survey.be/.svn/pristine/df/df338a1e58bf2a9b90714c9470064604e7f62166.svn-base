package kr.ac.jj.survey.application.admin.sysmanage.authormanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.admin.sysmanage.authormanage.model.AuthorityManageModel;
import kr.ac.jj.survey.application.admin.sysmanage.authormanage.service.AuthorityManageServiceImpl;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.infrastructure.security.intercept.ReloadableFilterInvocationSecurityMetadataSource;

/**
 * 권한 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/sysmanage/authormanage/AuthorityManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class AuthorityManageRestController {
    private @Autowired AuthorityManageServiceImpl authorityManageService;
    private @Autowired ReloadableFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        authorityManageService.readList(gridRequest);
    }

    /**
     * 목록 순서 저장
     *
     * @param ordrList
     */
    @PutMapping(path = "/updateOrdrList")
    public void updateOrdrList(@RequestBody BaseMapList ordrList) {
        authorityManageService.updateOrdrList(ordrList);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }

    /**
     * 조회
     *
     * @param authorId
     * @return
     */
    @GetMapping
    public AuthorityManageModel read(@RequestBody String authorId) {
        return authorityManageService.read(authorId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String create(@RequestBody AuthorityManageModel model) {
        String authorId = authorityManageService.create(model);

        filterInvocationSecurityMetadataSource.reloadRequestMap();

        return authorId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping
    public void update(@RequestBody AuthorityManageModel model) {
        authorityManageService.update(model);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }

    /**
     * 삭제
     *
     * @param authorId
     */
    @DeleteMapping
    public void update(@RequestBody String authorId) {
        authorityManageService.delete(authorId);

        filterInvocationSecurityMetadataSource.reloadRequestMap();
    }
}
