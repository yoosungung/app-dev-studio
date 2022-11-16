package kr.ac.jj.survey.infrastructure.framework.web.security.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import kr.ac.jj.survey.infrastructure.framework.web.security.attribute.ServletSecurityAttribute;
import kr.ac.jj.survey.infrastructure.framework.web.servlet.util.ServletUtil;

@Configuration
public class ServletSecurityAttributeConfig {
    @PostConstruct
    public void init() {
        ServletUtil.setAttribute(new ServletSecurityAttribute());
    }
}
