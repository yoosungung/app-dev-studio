package kr.ac.jj.survey.config.datasources;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@DependsOn("transactionManager")
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.jd.survey" })
@EntityScan("com.jd.survey")
public class XaDataSourceForJdConfig {

    private @Autowired JpaVendorAdapter jpaVendorAdapter;

    @Bean
    @ConfigurationProperties(prefix = "config.survey.datasources.main-jpa.atomikos")
    public DataSource xaForJdDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean jdEntityManagerFactory() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        jpaProperties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        jpaProperties.put("hibernate.connection.charSet", "UTF-8");
        jpaProperties.put("hibernate.id.new_generator_mappings", "false");
        jpaProperties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(xaForJdDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.jd.survey");
        entityManager.setPersistenceUnitName("persistenceUnit");
        entityManager.setJpaProperties(jpaProperties);

        return entityManager;
    }

}
