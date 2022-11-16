package kr.ac.jj.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import kr.ac.jj.shared.infrastructure.framework.web.multipart.nameformatter.SimpleFilenameFormatter;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicies;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicy;

@Configuration
public class SharedFilePolicyConfig {

    @Bean
    public FilePolicies sharedFilePolicies() {
        FilePolicies filePolicies = new FilePolicies();

        // 기본 정책
        {
            FilePolicy filePolicy = filePolicies.getDefaultPolicy();
            filePolicy.setRepositoryName("repository1");
            filePolicy.setMaxUploadSize(-1);
            filePolicy.setMaxUploadSizePerFile(-1);
            filePolicy.setSubPath("/default");
            filePolicy.setDateFolder("/yyyy/MM");
            filePolicy.setNameFormatter(new SimpleFilenameFormatter());
            filePolicy.setDeletePhysicalFile(false);
            filePolicy.setIncludeExtensions("bmp", "gif", "jpg", "jpeg", "png", "zip", "rar", "pdf", "txt", "hwp",
                    "docx", "docm", "doc", "dotm", "dot", "xlxs", "xlsm", "xlsb", "xltx", "xltm", "xls", "xlt", "pptx",
                    "pptm", "ppt", "potx", "potm", "ppsx", "ppsm");
            filePolicy.setExcludeExtensions();
        }

        // 임시
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("temp");
            filePolicy.setSubPath("/temp");
            filePolicy.setDateFolder(null);
        }

        // 임시 - 이미지
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("temp.image");
            filePolicy.setSubPath("/temp/image");
            filePolicy.setDateFolder(null);
            filePolicy.setIncludeExtensions("bmp", "gif", "jpg", "jpeg", "png");
        }

        // 사람
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("person");
            filePolicy.setSubPath("/person");
        }

        // 이메일
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("email");
            filePolicy.setSubPath("/email");
            filePolicy.setMaxUploadSizePerFile("10MB");
        }

        // 게시판 - 일반(답변형)
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("bbs.general");
            filePolicy.setSubPath("/bbs/general");
        }

        // 게시판 - 공지사항
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("bbs.notice");
            filePolicy.setSubPath("/bbs/notice");
        }

        // 게시판 - FAQ
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("bbs.faq");
            filePolicy.setSubPath("/bbs/faq");
        }

        return filePolicies;
    }

    @Bean
    @Primary
    @Profile(value = "shared")
    public FilePolicies filePolicies(FilePolicies sharedFilePolicies) {
        FilePolicies filePolicies = sharedFilePolicies;

        return filePolicies;
    }

}
