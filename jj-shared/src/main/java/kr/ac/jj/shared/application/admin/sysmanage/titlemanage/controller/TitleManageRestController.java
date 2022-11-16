package kr.ac.jj.shared.application.admin.sysmanage.titlemanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.application.admin.sysmanage.titlemanage.model.TitleManageModel;
import kr.ac.jj.shared.application.admin.sysmanage.titlemanage.service.TitleManageServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 타이틀 관리 RestController
 */
@RestController
@RequestMapping(path = "/admin/sysmanage/titlemanage/TitleManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class TitleManageRestController {

    private @Autowired TitleManageServiceImpl titleManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        titleManageService.readList(gridRequest);
    }

    /**
     * 조회
     *
     * @param titleCode
     * @return
     */
    @GetMapping(path = "/{titleCode}")
    public TitleManageModel get(@PathVariable String titleCode) {
        return titleManageService.read(titleCode);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{titleCode}")
    public void update(@PathVariable TitleManageModel model) {
        titleManageService.update(model);
    }

}
