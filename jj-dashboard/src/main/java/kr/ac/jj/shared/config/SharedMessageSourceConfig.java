package kr.ac.jj.shared.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import kr.ac.jj.shared.infrastructure.framework.core.support.message.ReloadableAllBasenamesMessageSource;

@Configuration
@Profile(value = "shared")
public class SharedMessageSourceConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableAllBasenamesMessageSource messageSource = new ReloadableAllBasenamesMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

}
