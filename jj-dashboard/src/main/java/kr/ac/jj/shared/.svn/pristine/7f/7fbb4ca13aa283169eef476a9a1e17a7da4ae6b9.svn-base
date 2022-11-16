package kr.ac.jj.shared.application.bbs.gnrl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 게시판(일반) Controller
 */
@Controller
@RequestMapping(path = "/bbs/gnrl/BbsGeneral/{bbsCode}")
public class BbsGeneralController {

    /**
     * 화면
     *
     * @param bbsCode
     * @return
     */
    @GetMapping
    public String view(@PathVariable String bbsCode) {
        return "tiles2-shared:/bbs/gnrl/BbsGeneralView";
    }

}
