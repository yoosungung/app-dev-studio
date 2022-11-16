package kr.ac.jj.survey.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.ac.jj.survey.infrastructure.framework.web.filter.HeaderHttpMethodOverrideFilter;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
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
}
