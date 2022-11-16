package kr.ac.jj.shared.application.common.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.file.model.FileDownloadInfo;
import kr.ac.jj.shared.infrastructure.file.view.FileDownloadView;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.model.UploadedFile;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicies;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicy;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.upload.FileUpload;
import kr.ac.jj.shared.infrastructure.util.BaseUtil;

/**
 * 파일 Controller
 */
@Controller
@RequestMapping(path = "/common/file")
public class FileController {

    private @Autowired FilePolicies filePolicies;
    private @Autowired FileServiceImpl fileService;
    private @Autowired FileDownloadView fileDownloadView;

    /**
     * 업로드
     *
     * @param policy
     * @param request
     * @return
     */
    @PostMapping(path = "/upload")
    public @ResponseBody TbComFile upload(@RequestParam String policy, MultipartHttpServletRequest request) {
        FilePolicy filePolicy = filePolicies.getFilePolicy(policy);

        FileUpload fileUpload = new FileUpload(filePolicy, request);

        List<UploadedFile> uploadedFileList = fileUpload.getUploadedFileList();

        if (uploadedFileList.size() == 0) {
            return null;
        }

        UploadedFile uploadedFile = uploadedFileList.get(0);

        TbComFile tbComFile = new TbComFile();
        tbComFile.set_JOB_TYPES(DataJobTypes.CREATE);
        tbComFile.setFilePolicy(policy);
        tbComFile.setFileRpstr(filePolicy.getRepositoryName());
        tbComFile.setStrePath(filePolicy.getSubPath() + uploadedFile.getUploadedDateFolder());
        tbComFile.setStreFileNm(uploadedFile.getUploadedFilename());
        tbComFile.setOrginlFileNm(uploadedFile.getOriginalFilename());
        tbComFile.setFileExtsn(uploadedFile.getExtension().toUpperCase());
        tbComFile.setFileSize(uploadedFile.getSize());

        return tbComFile;
    }

    /**
     * 이미지 업로드
     *
     * @param response
     * @param multipartFile
     * @return
     */
    @PostMapping(path = "/uploadImage")
    public @ResponseBody BaseMap uploadImage(HttpServletResponse response,
            @RequestParam("upload") MultipartFile multipartFile) {

        StringBuilder sb = new StringBuilder();
        sb.append("data:image/png;base64,");

        try {
            sb.append(StringUtils.newStringUtf8(Base64Utils.encode(multipartFile.getBytes())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BaseMap result = new BaseMap();
        result.put("url", sb.toString());
        result.put("uploaded", true);

        return result;
    }

    /**
     * 다운로드
     *
     * @param dwldIds
     * @return
     */
    @GetMapping(path = "/download")
    public ModelAndView download(@RequestParam(name = "dwldId") String dwldId) {
        TbComFile tbComFile = fileService.readDownloadInfo(dwldId);

        FileDownloadInfo fileDownloadInfo = new FileDownloadInfo();

        fileDownloadInfo.setDownloadFile(tbComFile.getFile());
        fileDownloadInfo.setOriginalFileName(tbComFile.getOrginlFileNm());

        ModelAndView mav = new ModelAndView();
        mav.setView(fileDownloadView);
        mav.addObject(fileDownloadInfo);

        return mav;
    }

    /**
     * 임시 파일 다운로드
     *
     * @param tempFileName
     * @param realFileName
     * @return
     */
    @PostMapping(path = "/downloadTempFile")
    public ModelAndView downloadExcelFile(@RequestParam String tempFileName, @RequestParam String realFileName) {
        File tempFile = new File(BaseUtil.getTempDirectory(), tempFileName);

        FileDownloadInfo fileDownloadInfo = new FileDownloadInfo();
        fileDownloadInfo.setDownloadFile(tempFile);
        fileDownloadInfo.setOriginalFileName(realFileName);
        fileDownloadInfo.setDeleteDownloadFile(true);

        ModelAndView mav = new ModelAndView();
        mav.setView(fileDownloadView);
        mav.addObject(fileDownloadInfo);

        return mav;
    }

}
