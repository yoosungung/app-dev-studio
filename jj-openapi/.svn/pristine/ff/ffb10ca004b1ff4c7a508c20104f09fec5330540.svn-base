package kr.ac.jj.openapi.application.serviceguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.openapi.application.serviceguide.model.ServiceGuideModel;
import kr.ac.jj.openapi.application.serviceguide.service.ServiceGuideServiceImpl;

/**
 * 서비스 이용안내 RestController
 */
@RestController
@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ServiceGuideRestController {

    private @Autowired ServiceGuideServiceImpl serviceGuideService;

    /**
     * 조회
     *
     * @return
     */
    @GetMapping(path = { "/serviceguide/ServiceGuide/read.do", "/admin/serviceguide/ServiceGuide/read" })
    public ServiceGuideModel get() {
        return serviceGuideService.read();
    }

    /**
     * 수정
     *
     * @param model
     */
    @PutMapping(path = "/admin/serviceguide/ServiceGuide/update")
    public void put(@RequestBody ServiceGuideModel model) {
        serviceGuideService.update(model);
    }

}
