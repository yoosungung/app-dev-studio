package kr.ac.jj.shared.application.bbs.notice.controller;

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

import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;
import kr.ac.jj.shared.application.bbs.notice.service.BbsNoticeServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 게시판(공지사항) RestController
 */
@RestController
@RequestMapping(path = "/bbs/notice/BbsNotice", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class BbsNoticeRestController {

    private @Autowired BbsNoticeServiceImpl bbsNoticeService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        bbsNoticeService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public BbsNoticeModel get() {
        return bbsNoticeService.read();
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @param updateRdcnt
     * @return
     */
    @GetMapping(path = "/{bbscttId}")
    public BbsNoticeModel get(@PathVariable String bbscttId, @RequestParam Boolean updateRdcnt) {
        return bbsNoticeService.read(bbscttId, updateRdcnt);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody BbsNoticeModel model) {
        return bbsNoticeService.create(model);
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{bbscttId}")
    public void put(@RequestBody BbsNoticeModel model) {
        bbsNoticeService.update(model);
    }

    /**
     * 삭제
     *
     * @param bbscttId
     */
    @DeleteMapping(path = "/{bbscttId}")
    public void delete(@PathVariable String bbscttId) {
        bbsNoticeService.delete(bbscttId);
    }

}
