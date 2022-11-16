package kr.ac.jj.shared.application.admin.logmanage.emaillog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.jj.shared.application.admin.logmanage.emaillog.service.EmailLogManageServiceImpl;
import kr.ac.jj.shared.domain.main.model.com.email.atchfile.TbComEmailAtchFile;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.FileDownload;

/**
 * 이메일 로그 관리 Controller
 */
@Controller
@RequestMapping(path = "/admin/logmanage/emaillog/EmailLogManage")
public class EmailLogManageController {

    private @Autowired EmailLogManageServiceImpl emailLogManageService;

    /**
     * 화면
     *
     * @return
     */
    @GetMapping
    public String view() {
        return "tiles2-shared:/admin/logmanage/emaillog/EmailLogManageView";
    }

    /**
     * 파일 다운로드
     *
     * @param emailAtchFileId
     * @param request
     * @param response
     */
    @GetMapping(path = "/downloadFile")
    public void downloadFile(@RequestParam String emailAtchFileId, HttpServletRequest request,
            HttpServletResponse response) {

        TbComEmailAtchFile tbComEmailAtchFile = emailLogManageService.readFile(emailAtchFileId);

        FileDownload fileDown = new FileDownload(request, response);
        fileDown.download(tbComEmailAtchFile.getFilePath(), tbComEmailAtchFile.getFileNm());
    }

}
