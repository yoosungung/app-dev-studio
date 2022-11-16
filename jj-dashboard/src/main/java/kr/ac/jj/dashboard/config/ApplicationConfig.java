package kr.ac.jj.dashboard.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        List<String> definitionList = new ArrayList<String>();
        definitionList.add("/WEB-INF/layouts/dashboard/definition/*.xml");

        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(definitionList.toArray(new String[definitionList.size()]));
        configurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        configurer.setCheckRefresh(true);

        return configurer;
    }

}
