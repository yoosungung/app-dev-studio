package kr.ac.jj.survey.infrastructure.logging.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.ac.jj.survey.infrastructure.logging.filter.LoggingFilter;

/**
 * 로깅 Config
 */
@Configuration
public class LoggingConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<LoggingFilter>(
                new LoggingFilter());
        registrationBean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER - 1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
