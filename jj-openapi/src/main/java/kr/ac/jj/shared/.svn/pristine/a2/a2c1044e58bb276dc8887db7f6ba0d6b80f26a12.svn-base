package kr.ac.jj.shared.application.admin.appmanage.dtymanage.controller;

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

import kr.ac.jj.shared.application.admin.appmanage.dtymanage.model.DutyManageModel;
import kr.ac.jj.shared.application.admin.appmanage.dtymanage.service.DutyManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 직무 관리 Controller
 */
@RestController
@RequestMapping(path = "/admin/appmanage/dtymanage/DutyManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class DutyManageRestController {

    private @Autowired DutyManageServiceImpl dutyManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        dutyManageService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public DutyManageModel get() {
        return dutyManageService.read();
    }

    /**
     * 조회
     *
     * @param dtyId
     * @return
     */
    @GetMapping(path = "/{dtyId}")
    public DutyManageModel get(@PathVariable String dtyId) {
        return dutyManageService.read(dtyId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody DutyManageModel model) {
        return dutyManageService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{dtyId}")
    public void put(@RequestBody DutyManageModel model) {
        dutyManageService.update(model);
    }

    /**
     * 삭제
     *
     * @param dtyId
     */
    @DeleteMapping(path = "/{dtyId}")
    public void delete(@PathVariable String dtyId) {
        dutyManageService.delete(dtyId);
    }

}
