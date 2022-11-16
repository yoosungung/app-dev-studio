package kr.ac.jj.shared.application.admin.logmanage.actionlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 액션 로그 관리 Controller
 */
@Controller
@RequestMapping(path = "/admin/logmanage/actionlog/ActionLogManage")
public class ActionLogManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/admin/logmanage/actionlog/ActionLogManageView";
    }

}
