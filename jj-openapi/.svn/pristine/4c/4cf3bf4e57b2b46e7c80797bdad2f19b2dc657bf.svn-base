package kr.ac.jj.openapi.config.datasources;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelperForMariaDB;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelperUtil;

@Configuration
public class DataSourceMainConfig {

    @MapperScan(annotationClass = MainSqlMapper.class, sqlSessionFactoryRef = "sqlSessionFactory.main", basePackages = {
            "kr.ac.jj.openapi" })
    public static class MapperScanPackages {

        public MapperScanPackages() {
            SqlHelperUtil.addSqlHelper(new SqlHelperForMariaDB());
        }

    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Component
    public @interface MainSqlMapper {

        String value() default "";

    }

}
