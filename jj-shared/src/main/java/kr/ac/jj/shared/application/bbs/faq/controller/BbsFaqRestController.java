package kr.ac.jj.shared.application.bbs.faq.controller;

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

import kr.ac.jj.shared.application.bbs.faq.model.BbsFaqModel;
import kr.ac.jj.shared.application.bbs.faq.service.BbsFaqServiceImpl;
import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 게시판(FAQ) RestController
 */
@RestController
@RequestMapping(path = "/bbs/faq/BbsFaq", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class BbsFaqRestController {

    private @Autowired BbsFaqServiceImpl bbsFaqService;
    private @Autowired CodeDataSourceAccessor codeDataSourceAccessor;

    /**
     * 목록 조회
     *
     * @param gridRequest
     */
    @PostMapping(path = "/readList")
    public void readList(@RequestBody GridRequest gridRequest) {
        bbsFaqService.readList(gridRequest);
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    @GetMapping
    public BbsFaqModel get() {
        return bbsFaqService.read();
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    @GetMapping(path = "/{bbscttId}")
    public BbsFaqModel get(@PathVariable String bbscttId) {
        return bbsFaqService.read(bbscttId);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    @PostMapping
    public String post(@RequestBody BbsFaqModel model) {
//        codeDataSourceAccessor.refreshAll();

    	String bbsFaqId = bbsFaqService.create(model);
        return bbsFaqId;
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/{bbscttId}")
    public void put(@RequestBody BbsFaqModel model) {
        bbsFaqService.update(model);
    }

    /**
     * 삭제
     *
     * @param bbscttId
     */
    @DeleteMapping(path = "/{bbscttId}")
    public void delete(@PathVariable String bbscttId) {
        bbsFaqService.delete(bbscttId);
    }

}
