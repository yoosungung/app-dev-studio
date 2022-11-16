package kr.ac.jj.openapi.application.main.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import kr.ac.jj.openapi.application.bannermanage.service.BannerManageServiceImpl;
import kr.ac.jj.openapi.application.main.mapper.MainMapper;
import kr.ac.jj.shared.application.bbs.faq.model.BbsFaqModel;
import kr.ac.jj.shared.application.bbs.gnrl.model.BbsGeneralModel;
import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

/**
 * 메인 Service
 */
@Service
public class MainServiceImpl {

    private @Autowired FileServiceImpl fileService;
    private @Autowired BannerManageServiceImpl bannerManageService;
    private @Autowired MainMapper mainMapper;

    public List<BbsNoticeModel> readNoticeList() {
        return mainMapper.readNoticeList();
    }

    public List<BbsGeneralModel> readQnaList() {
        return mainMapper.readQnaList();
    }

    public List<BbsFaqModel> readFaqList() {
        return mainMapper.readFaqList();
    }

    public List<String> readBannerList() {
        List<String> dwldIds = bannerManageService.readUsingList();
        List<String> files = new ArrayList<String>();
        for (int i=0; i<dwldIds.size(); i++) {
            TbComFile tbComFile = fileService.readDownloadInfo(dwldIds.get(i));
            File file = tbComFile.getFile();

            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            try {
                byte[] bytes = IOUtils.toByteArray(FileUtil.getResourceStream(file));
                sb.append(StringUtils.newStringUtf8(Base64Utils.encode(bytes)));

                files.add(sb.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return files;
    }
}
