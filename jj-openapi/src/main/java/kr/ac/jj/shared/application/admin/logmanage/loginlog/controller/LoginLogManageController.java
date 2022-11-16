package kr.ac.jj.shared.application.admin.logmanage.loginlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 접속 로그 관리 Controller
 */
@Controller
@RequestMapping(path = "/admin/logmanage/loginlog/LoginLogManage")
public class LoginLogManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/admin/logmanage/loginlog/LoginLogManageView";
    }

}
