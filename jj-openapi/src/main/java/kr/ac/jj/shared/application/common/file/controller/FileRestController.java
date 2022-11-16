package kr.ac.jj.shared.application.common.file.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

/**
 * 파일 RestController
 */
@RestController
@RequestMapping(path = "/common/file", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class FileRestController {

    /**
     * 업로드 취소
     *
     * @param tbComFile
     */
    @DeleteMapping(path = "/revert")
    public @ResponseBody void revert(@RequestBody TbComFile tbComFile) {
        FileUtil.deleteFile(tbComFile.getFile());
    }

}
