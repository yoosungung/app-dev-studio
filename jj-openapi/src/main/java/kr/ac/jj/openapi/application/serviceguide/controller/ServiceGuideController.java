package kr.ac.jj.openapi.application.serviceguide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 서비스 이용안내 Controller
 */
@Controller
@RequestMapping
public class ServiceGuideController {

    /**
     * 조회 화면
     *
     * @return
     */
    @GetMapping(path = "/serviceguide/ServiceGuide/viewRead.do")
    public String viewRead() {
        return "tiles2-main:/serviceguide/ServiceGuideViewFormRead";
    }

    /**
     * 수정 화면
     *
     * @return
     */
    @GetMapping(path = "/admin/serviceguide/ServiceGuide/viewEdit")
    public String viewEdit() {
        return "tiles2-common:/serviceguide/ServiceGuideViewFormEdit";
    }

}
