package kr.ac.jj.shared.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.util.unit.DataSize;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import kr.ac.jj.shared.application.common.menu.interceptor.MenuAuthLogInterceptor;
import kr.ac.jj.shared.infrastructure.file.view.FileDownloadView;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextProvider;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config.SchedulerConfig;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.util.SpringQuartzSchedulerUtil;
import kr.ac.jj.shared.infrastructure.framework.web.filter.HeaderHttpMethodOverrideFilter;
import kr.ac.jj.shared.infrastructure.framework.web.support.download.config.FileDownloadConfig;

@Configuration
public class SharedApplicationConfig implements WebMvcConfigurer {

    private @Autowired MenuAuthLogInterceptor menuAuthLogInterceptor;

    @PostConstruct
    public void postConstruct() {
        applicationContextProvider();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.beanName();

        registry.tiles();

        registry.jsp() //
                .prefix("/WEB-INF/views/") //
                .suffix(".jsp") //
        ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());

        registry.addInterceptor(menuAuthLogInterceptor) //
                .excludePathPatterns("/error", "/static/**", "/**/*.*") //
                .excludePathPatterns("/common/menu/MenuLoad/**/*") //
        ;

        registry.addInterceptor(menuAuthLogInterceptor) //
                .addPathPatterns("/**/*.do") //
        ;
    }

    @Bean
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
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
    @ConfigurationProperties(prefix = "config.shared.scheduler")
    public SchedulerConfig schedulerConfig() {
        return new SchedulerConfig();
    }

    @Bean
    public Scheduler scheduler() {
        return SpringQuartzSchedulerUtil.start();
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

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofBytes(-1));
        factory.setMaxFileSize(DataSize.ofBytes(-1));

        return factory.createMultipartConfig();
    }

    @Bean
    public FileDownloadConfig fileDownloadConfig() {
        FileDownloadConfig fileDownloadConfig = new FileDownloadConfig();
        fileDownloadConfig.setContentTransferEncoding("binary;");
        fileDownloadConfig.setDownloadFilenameUrlEncode(false);
        fileDownloadConfig.setDownloadFilenameEncoding(true);

        return fileDownloadConfig;
    }

    @Bean
    public FileDownloadView fileDownloadView() {
        FileDownloadView fileDownloadView = new FileDownloadView();

        return fileDownloadView;
    }

    @Bean
    @Profile(value = "shared")
    public TilesConfigurer tilesConfigurer() {
        List<String> definitionList = new ArrayList<String>();

        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(definitionList.toArray(new String[definitionList.size()]));
        configurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        configurer.setCheckRefresh(true);

        return configurer;
    }

}
