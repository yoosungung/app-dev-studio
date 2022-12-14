package kr.ac.jj.openapi.application.keymanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 키 발급 관리 Controller
 */
@Controller
@RequestMapping(path = "/keymanage/KeyManage")
public class KeyManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/keymanage/KeyManageView";
    }

}
