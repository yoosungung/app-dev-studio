package kr.ac.jj.survey.application.admin.appmanage.groupmanage.controller;

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

import kr.ac.jj.survey.application.admin.appmanage.groupmanage.service.GroupManageServiceImpl;
import kr.ac.jj.survey.application.admin.appmanage.usermanage.model.UserManageModel;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

/**
 * 그룹 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/appmanage/groupmanage/GroupManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class GroupManageRestController {
    private @Autowired GroupManageServiceImpl groupManageServiceImpl;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        groupManageServiceImpl.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param personId
     * @return
     */
    @GetMapping
    public UserManageModel read(@RequestParam(required = false) String personId) {
        return groupManageServiceImpl.read(personId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String create(@RequestBody UserManageModel model) {
        return groupManageServiceImpl.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping
    public void update(@RequestBody UserManageModel model) {
        groupManageServiceImpl.update(model);
    }

    /**
     * 삭제
     *
     * @param personId
     */
    @DeleteMapping
    public void delete(@RequestBody String personId) {
        groupManageServiceImpl.delete(personId);
    }
}
