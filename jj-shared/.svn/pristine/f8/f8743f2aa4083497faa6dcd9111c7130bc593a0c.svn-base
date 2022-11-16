package kr.ac.jj.shared.config;

import java.util.Properties;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.ac.jj.shared.infrastructure.framework.web.filter.HeaderHttpMethodOverrideFilter;

@Configuration
public class SharedApplicationConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.beanName();

        registry.tiles();

        registry.jsp() //
                .prefix("/WEB-INF/views/") //
                .suffix(".jsp");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //
                        .allowedOrigins("*") //
                        .allowedMethods( //
                                HttpMethod.GET.name(), //
                                HttpMethod.POST.name(), //
                                HttpMethod.PUT.name(), //
                                HttpMethod.DELETE.name(), //
                                HttpMethod.OPTIONS.name()) //
                ;
            }

        };
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();

        return jacksonMessageConverter;
    }

    @Bean
    public FilterRegistrationBean<HeaderHttpMethodOverrideFilter> headerHttpMethodOverrideFilter() {
        FilterRegistrationBean<HeaderHttpMethodOverrideFilter> registrationBean = new FilterRegistrationBean<HeaderHttpMethodOverrideFilter>();
        registrationBean.setFilter(new HeaderHttpMethodOverrideFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registrationBean = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registrationBean.setFilter(new HiddenHttpMethodFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.transport.protocol", "smtp");
        javaMailProperties.setProperty("mail.smtp.from", "");
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.debug", "false");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("");
        mailSender.setPassword("");
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

}
