package kr.ac.jj.shared.application.admin.sysmanage.authormanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.sysmanage.authormanage.model.AuthorityHierarchyManageModel;
import kr.ac.jj.shared.application.admin.sysmanage.authormanage.service.AuthorityHierarchyManageServiceImpl;
import kr.ac.jj.shared.infrastructure.security.intercept.ReloadableFilterInvocationSecurityMetadataSource;

/**
 * 권한 계층 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/sysmanage/authormanage/AuthorityHierarchyManage", consumes = {
        MediaType.APPLICATION_JSON_VALUE })
public class AuthorityHierarchyManageRestController {

    private @Autowired AuthorityHierarchyManageServiceImpl authorityHierarchyManageService;
    private @Autowired ReloadableFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    /**
     * 조회
     *
     * @param authorId
     * @return
     */
    @GetMapping
    public AuthorityHierarchyManageModel read() {
        return authorityHierarchyManageService.read();
    }

    /**
     * 목록 저장
     *
     * @param model
     */
    @PutMapping
    public void updateList(@RequestBody AuthorityHierarchyManageModel model) {
        authorityHierarchyManageService.updateList(model);

        filterInvocationSecurityMetadataSource.reloadHierarchy();
    }

}
