package kr.ac.jj.survey.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperForMariaDB;
import kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperForOracle;
import kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperUtil;

@Configuration
@EnableTransactionManagement
public class DataSourceMapperConfig {
    public DataSourceMapperConfig() {
        SqlMapHelperUtil.addSqlMapHelper(new SqlMapHelperForMariaDB());
        SqlMapHelperUtil.addSqlMapHelper(new SqlMapHelperForOracle());
    }

    @MapperScan(annotationClass = MainSqlMapper.class, sqlSessionFactoryRef = "sqlSessionFactory.main", basePackages = {
            "kr.ac.jj.survey" })
    public static class MainDataSource {
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Component
    public @interface MainSqlMapper {
        String value() default "";
    }
}
