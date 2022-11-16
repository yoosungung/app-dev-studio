package kr.ac.jj.shared.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperForMariaDB;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperForOracle;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelperUtil;

@Configuration
@EnableTransactionManagement
public class SharedDataSourceMapperConfig {

    public SharedDataSourceMapperConfig() {
        SqlMapHelperUtil.addSqlMapHelper(new SqlMapHelperForMariaDB());
        SqlMapHelperUtil.addSqlMapHelper(new SqlMapHelperForOracle());
    }

    @MapperScan(annotationClass = SharedSqlMapper.class, sqlSessionFactoryRef = "sqlSessionFactory.main", basePackages = {
            "kr.ac.jj.shared" })
    public static class SharedDataSource {
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Component
    public @interface SharedSqlMapper {

        String value() default "";

    }

}
