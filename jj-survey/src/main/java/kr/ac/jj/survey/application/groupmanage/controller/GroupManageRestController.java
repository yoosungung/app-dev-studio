package kr.ac.jj.survey.application.groupmanage.controller;

import java.util.List;
import java.util.Map;

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

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.groupmanage.model.GroupManageModel;
import kr.ac.jj.survey.application.groupmanage.service.GroupManageServiceImpl;

/**
 * 그룹 관리 RestController
 */
@RestController
@RequestMapping(path = "/groupmanage/GroupManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class GroupManageRestController {

    private @Autowired GroupManageServiceImpl groupManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        groupManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public GroupManageModel get() {
        return groupManageService.read();
    }

    /**
     * 조회
     *
     * @param surveyGroupId
     * @return
     */
    @GetMapping(path = "/{surveyGroupId}")
    public GroupManageModel get(@PathVariable String surveyGroupId) {
        return groupManageService.read(surveyGroupId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody GroupManageModel model) {
        return groupManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{surveyGroupId}")
    public void put(@RequestBody GroupManageModel model) {
        groupManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param surveyGroupId
     */
    @DeleteMapping(path = "/{surveyGroupId}")
    public void delete(@PathVariable String surveyGroupId) {
        groupManageService.delete(surveyGroupId);
    }

    /**
     * 사람(대상자) 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPersonList")
    public void readPersonList(@RequestBody GridRequest gridRequest) {
        groupManageService.readPersonList(gridRequest);
    }

    /**
     * 사람(대상자) 목록 검색
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readPersonSearchList")
    public void readPersonSearchList(@RequestBody GridRequest gridRequest) {
        groupManageService.readPersonSearchList(gridRequest);
    }

    /**
     * 사람(대상자) 생성
     *
     * @param surveyGroupId
     * @param personId
     */
    @PostMapping(path = "/{surveyGroupId}", params = { "personId" })
    public void post(@PathVariable String surveyGroupId, @RequestBody String personId) {
        groupManageService.createPerson(surveyGroupId, personId);
    }

    /**
     * 사람(대상자) 삭제
     *
     * @param surveyGroupId
     * @param personId
     */
    @DeleteMapping(path = "/{surveyGroupId}", params = { "personId" })
    public void delete(@PathVariable String surveyGroupId, @RequestParam String personId) {
        groupManageService.deletePerson(surveyGroupId, personId);
    }

    /**
     * 조직 트리 조회
     *
     * @param surveyGroupId
     * @param deptSe
     * @return
     */
    @GetMapping(path = "/readDeptTree")
    public List<Map<String, Object>> readDeptTree(@RequestParam String surveyGroupId, @RequestParam String deptSe) {
        return groupManageService.readDeptTree(surveyGroupId, deptSe);
    }

    /**
     * 조직별 사람(대상자) 목록 생성
     *
     * @param surveyGroupId
     * @param tbComDeptList
     */
    @PostMapping(path = "/{surveyGroupId}", params = { "deptId" })
    public void postDept(@PathVariable String surveyGroupId, @RequestBody List<TbComDept> tbComDeptList) {
        groupManageService.createDeptPersonList(surveyGroupId, tbComDeptList);
    }

}
