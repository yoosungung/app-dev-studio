package kr.ac.jj.survey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jd.survey.web.MessageSourceMapAdapterFactory;

import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.infrastructure.framework.core.support.message.ReloadableAllBasenamesMessageSource;

@Configuration
public class MessageConfig {

    private @Autowired SharedConfigProperties sharedConfig;

    @Value(value = "${spring.messages.cache-duration}")
    private int cacheDuration;

    @Bean
    public MessageSource messageSource() {
        ReloadableAllBasenamesMessageSource messageSource = new ReloadableAllBasenamesMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(this.cacheDuration);
        messageSource.setInvalidPrefix(sharedConfig.getMessage().getInvalidText().getPrefix());
        messageSource.setInvalidSuffix(sharedConfig.getMessage().getInvalidText().getSuffix());
        messageSource.setDefaultEncoding("UTF-8");

        messageSource.addRootpath("classpath:/configuration/message/shared");
        messageSource.addRootpath("classpath:/configuration/message/survey");

        messageSource.addBasename("classpath:/configuration/message/jd/messages");
        messageSource.addBasename("classpath:/configuration/message/jd/application");

        return messageSource;
    }

    @Bean
    public MessageSourceMapAdapterFactory messages() {
        return new MessageSourceMapAdapterFactory();
    }

}
