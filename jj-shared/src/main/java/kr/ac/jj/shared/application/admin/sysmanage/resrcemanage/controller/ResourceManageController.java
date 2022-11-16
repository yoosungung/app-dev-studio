package kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 리소스 관리 Controller
 */
@Controller
@RequestMapping(path = "/admin/sysmanage/resrcemanage/ResourceManage")
public class ResourceManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/admin/sysmanage/resrcemanage/ResourceManageView";
    }

}
