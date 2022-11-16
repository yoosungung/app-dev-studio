package kr.ac.jj.shared.application.bbs.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 게시판(공지사항) Controller
 */
@Controller
@RequestMapping(path = "/bbs/notice/BbsNotice")
public class BbsNoticeController {

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/bbs/notice/BbsNoticeView";
    }

}
