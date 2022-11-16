package kr.ac.jj.survey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicies;

@Configuration
public class FilePolicyConfig {

    @Bean
    @Primary
    public FilePolicies filePolicies(FilePolicies sharedFilePolicies) {
        FilePolicies filePolicies = sharedFilePolicies;

        return filePolicies;
    }

}
