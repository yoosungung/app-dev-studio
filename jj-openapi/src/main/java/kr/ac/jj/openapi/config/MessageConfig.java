package kr.ac.jj.openapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.ac.jj.shared.infrastructure.framework.core.support.message.ReloadableAllBasenamesMessageSource;

@Configuration
public class MessageConfig {

    @Value(value = "${spring.messages.cache-duration}")
    private int cacheDuration;

    @Bean
    public MessageSource messageSource() {
        ReloadableAllBasenamesMessageSource messageSource = new ReloadableAllBasenamesMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(this.cacheDuration);

        messageSource.addRootpath("classpath:/configuration/message/shared");
        messageSource.addRootpath("classpath:/configuration/message/openapi");

        return messageSource;
    }

}
