package kr.ac.jj.openapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicies;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicy;

@Configuration
public class FilePolicyConfig {

    @Bean
    @Primary
    public FilePolicies filePolicies(FilePolicies sharedFilePolicies) {
        FilePolicies filePolicies = sharedFilePolicies;

        // 배너
        {
            FilePolicy filePolicy = filePolicies.newFilePolicy("banner");
            filePolicy.setSubPath("/banner");
            filePolicy.setIncludeExtensions("bmp", "gif", "jpg", "jpeg", "png");
        }

        return filePolicies;
    }

}
