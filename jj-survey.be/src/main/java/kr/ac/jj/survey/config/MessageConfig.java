package kr.ac.jj.survey.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.ac.jj.survey.infrastructure.framework.core.support.message.ReloadableAllBasenamesMessageSource;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.MessageUtil;

@Configuration
public class MessageConfig {
    @Value(value = "${spring.messages.cache-duration}")
    private int cacheDuration;

    @Bean
    public MessageSource messageSource() {
        ReloadableAllBasenamesMessageSource messageSource = new ReloadableAllBasenamesMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(this.cacheDuration);

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

    @Bean
    public MessageUtil messageUtil() {
        MessageUtil messageUtil = new MessageUtil();
        messageUtil.setMessageSourceAccessor(messageSourceAccessor());

        return messageUtil;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);

        return localeResolver;
    }
}
