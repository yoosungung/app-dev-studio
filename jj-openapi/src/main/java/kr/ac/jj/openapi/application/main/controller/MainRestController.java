package kr.ac.jj.openapi.application.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.openapi.application.main.service.MainServiceImpl;
import kr.ac.jj.shared.application.bbs.faq.model.BbsFaqModel;
import kr.ac.jj.shared.application.bbs.gnrl.model.BbsGeneralModel;
import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

/**
 * 메인 Controller
 */
@RestController
@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MainRestController {

    private @Autowired MainServiceImpl mainService;
    private @Autowired FileServiceImpl fileService;

    /**
     * 공지사항 목록 조회
     *
     * @return
     */
    @GetMapping(path = "/readNoticeList.do")
    public List<BbsNoticeModel> readNoticeList() {
        return mainService.readNoticeList();
    }

    /**
     * Q&A 목록 조회
     *
     * @return
     */
    @GetMapping(path = "/readQnaList.do")
    public List<BbsGeneralModel> readQnaList() {
        return mainService.readQnaList();
    }

    /**
     * FAQ 목록 조회
     *
     * @return
     */
    @GetMapping(path = "/readFaqList.do")
    public List<BbsFaqModel> readFaqList() {
        return mainService.readFaqList();
    }

    /**
     * 배너 목록 조회
     *
     * @return
     */
    @GetMapping(path = "/readBannerList.do")
    public List<String> readBannerList() {
        return mainService.readBannerList();
    }

    /**
     * 배너 Base64 String 변환
     *
     * @param dwldId
     * @return
     */
    @GetMapping(path = "/getBase64.do")
    public String getFilePath(@RequestParam String dwldId) {
        TbComFile tbComFile = fileService.readDownloadInfo(dwldId);

        File file = tbComFile.getFile();

        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");

        try {
            byte[] bytes = IOUtils.toByteArray(FileUtil.getResourceStream(file));
            sb.append(StringUtils.newStringUtf8(Base64Utils.encode(bytes)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

}
