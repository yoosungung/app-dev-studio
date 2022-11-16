package kr.ac.jj.openapi.application.apilist.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.openapi.application.apilist.service.ApiListServiceImpl;
import kr.ac.jj.shared.infrastructure.util.BaseUtil;

/**
 * api 리스트 RestController
 */
@RestController
@RequestMapping(path = "/apilist/ApiList", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ApiListRestController {

    private @Autowired ApiListServiceImpl apiListService;

    /**
     * api 테스트
     *
     * @param category
     * @param name
     * @param apiRequest
     */
    @PostMapping(path = "/{category}/{name}")
    public void api(@PathVariable String category, @PathVariable String name, @RequestBody ApiRequest apiRequest) {
        apiListService.api(category, name, apiRequest);
    }

    /**
     * 다운로드
     *
     * @param category
     * @param name
     * @param apiRequest
     *
     * @return
     */
    @PostMapping(path = "/{category}/{name}", params = {"download"})
    public String download(@PathVariable String category, @PathVariable String name, @RequestBody ApiRequest apiRequest) {
        File tempFile = BaseUtil.getTempFile();
        apiListService.download(category, name, apiRequest, tempFile);
        return tempFile.getName();
    }
}
