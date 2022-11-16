package kr.ac.jj.survey.application.realmmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 설문 분야 관리 Controller
 */
@Controller
@RequestMapping(path = "/realmmanage/RealmManage")
public class RealmManageController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-common:/realmmanage/RealmManageView";
    }

}
