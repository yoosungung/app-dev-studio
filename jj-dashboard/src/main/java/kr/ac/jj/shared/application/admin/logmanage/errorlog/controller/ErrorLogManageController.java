package kr.ac.jj.shared.application.admin.logmanage.errorlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 에러 로그 관리 Controller
 */
@Controller
@RequestMapping(path = "/admin/logmanage/errorlog/ErrorLogManage")
public class ErrorLogManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/admin/logmanage/errorlog/ErrorLogManageView";
    }

}
