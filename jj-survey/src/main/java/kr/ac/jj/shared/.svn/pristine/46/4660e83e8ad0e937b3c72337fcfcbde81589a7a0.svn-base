package kr.ac.jj.shared.application.admin.appmanage.deptmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.appmanage.deptmanage.model.DepartmentManageModel;
import kr.ac.jj.shared.application.admin.appmanage.deptmanage.service.DepartmentManageServiceImpl;

/**
 * 부서 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/appmanage/deptmanage/DepartmentManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class DepartmentManageRestController {

    private @Autowired DepartmentManageServiceImpl departmentManageService;

    /**
     * 트리 조회
     *
     * @return
     */
    @GetMapping(path = "/readTree")
    public List<Map<String, Object>> readTree() {
        return departmentManageService.readTree();
    }

    /**
     * 조회
     *
     * @param deptId
     * @return
     */
    @GetMapping(path = "/{deptId}")
    public DepartmentManageModel get(@PathVariable String deptId) {
        return departmentManageService.read(deptId);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{deptId}")
    public void put(@RequestBody DepartmentManageModel model) {
        departmentManageService.update(model);
    }

}
