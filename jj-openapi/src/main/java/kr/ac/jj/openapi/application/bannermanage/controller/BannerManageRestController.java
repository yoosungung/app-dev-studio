package kr.ac.jj.openapi.application.bannermanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.openapi.application.bannermanage.model.BannerManageModel;
import kr.ac.jj.openapi.application.bannermanage.service.BannerManageServiceImpl;
import kr.ac.jj.openapi.domain.main.model.api.banner.TbApiBanner;

/**
 * 배너 관리 RestController
 */
@RestController
@RequestMapping(path = "/bannermanage/BannerManage", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class BannerManageRestController {

    private @Autowired BannerManageServiceImpl bannerManageService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public List<TbApiBanner> readList() {
        return bannerManageService.readList();
    }

    /**
     * 사용중 배너 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readUsingList")
    public List<String> readUsingList() {
        return bannerManageService.readUsingList();
    }

    /**
     * 첨부파일 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readAtchFile")
    public List<TbApiBanner> readAtchFile(@RequestBody List<TbApiBanner> bannerList) {
        return bannerManageService.readAtchFile(bannerList);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping(path = "/create")
    public void create(@RequestBody BannerManageModel model) {
        bannerManageService.create(model);
    }

    /**
     * 목록 삭제
     *
     * @param bannerIdList
     */
    @PostMapping(path = "/deleteList")
    public void deleteList(@RequestBody List<String> bannerIdList) {
        bannerManageService.deleteList(bannerIdList);
    }

    /**
     * 사용여부 업데이트
     *
     * @param model
     * @return
     */
    @PostMapping(path = "/useUpdate")
    public void useUpdate(@RequestBody TbApiBanner tbApiBanner) {
        bannerManageService.useUpdate(tbApiBanner);
    }

}
