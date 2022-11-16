package kr.ac.jj.shared.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;

@Configuration
public class SharedMessageConfig {

    private @Autowired MessageSource messageSource;

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource);
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
