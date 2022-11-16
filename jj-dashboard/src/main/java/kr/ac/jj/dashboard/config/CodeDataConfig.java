package kr.ac.jj.dashboard.config;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.shared.infrastructure.framework.mybatis.support.codedata.QueryCodeDataSource;

@Configuration
public class CodeDataConfig {

    private @Autowired CodeDataSourceAccessor codeDataSourceAccessor;

    @Qualifier(value = "sqlSession.dw")
    private @Autowired SqlSession sqlSessionDw;

    @PostConstruct
    public void postConstruct() {
        codeDataSourceAccessor.addCodeDataSource(dashboardQueryCodeDataSource());
    }

    @Bean
    public QueryCodeDataSource dashboardQueryCodeDataSource() {
        QueryCodeDataSource codeDataSource = new QueryCodeDataSource(sqlSessionDw);
        codeDataSource.addSqlMapperNamespacePrefix("dashboard-codedata.");

        return codeDataSource;
    }

}
