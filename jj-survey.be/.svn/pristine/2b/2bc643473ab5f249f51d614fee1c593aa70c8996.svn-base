package kr.ac.jj.survey.application.admin.appmanage.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.admin.appmanage.usermanage.model.UserManageModel;
import kr.ac.jj.survey.application.admin.appmanage.usermanage.service.UserManageServiceImpl;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

/**
 * 사용자 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/appmanage/usermanage/UserManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class UserManageRestController {
    private @Autowired UserManageServiceImpl userManageServiceImpl;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        userManageServiceImpl.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param personId
     * @return
     */
    @GetMapping
    public UserManageModel read(@RequestParam(required = false) String personId) {
        return userManageServiceImpl.read(personId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String create(@RequestBody UserManageModel model) {
        return userManageServiceImpl.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping
    public void update(@RequestBody UserManageModel model) {
        userManageServiceImpl.update(model);
    }

    /**
     * 삭제
     *
     * @param personId
     */
    @DeleteMapping
    public void delete(@RequestBody String personId) {
        userManageServiceImpl.delete(personId);
    }
}
