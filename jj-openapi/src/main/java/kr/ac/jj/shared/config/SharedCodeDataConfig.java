package kr.ac.jj.shared.config;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.FileCodeDataSource;
import kr.ac.jj.shared.infrastructure.framework.mybatis.support.codedata.DatabaseCodeDataSource;
import kr.ac.jj.shared.infrastructure.framework.mybatis.support.codedata.QueryCodeDataSource;

@Configuration
public class SharedCodeDataConfig {

    private @Autowired SqlSession sqlSession;
    private @Autowired MessageSourceAccessor messageSourceAccessor;

    @Bean
    public CodeDataSourceAccessor codeDataSourceAccessor() {
        CodeDataSourceAccessor codeDataSourceAccessor = new CodeDataSourceAccessor();
        codeDataSourceAccessor.addCodeDataSource(databaseCodeDataSource());
        codeDataSourceAccessor.addCodeDataSource(queryCodeDataSource());
        codeDataSourceAccessor.addCodeDataSource(fileCodeDataSource());
        codeDataSourceAccessor.addBooleanType("1", "0");

        return codeDataSourceAccessor;
    }

    @Bean
    public DatabaseCodeDataSource databaseCodeDataSource() {
        DatabaseCodeDataSource codeDataSource = new DatabaseCodeDataSource(sqlSession);
        codeDataSource.setSqlMapperId("common.codedata.CodeDataMapper.selectList");
        codeDataSource.setGroupColumn("CODE_GROUP");
        codeDataSource.setLocaleColumn("CODE_LOCALE");
        codeDataSource.setCodeColumn("CODE_VALUE");
        codeDataSource.setNameColumn("CODE_NAME");

        return codeDataSource;
    }

    @Bean
    public QueryCodeDataSource queryCodeDataSource() {
        QueryCodeDataSource codeDataSource = new QueryCodeDataSource(sqlSession);
        codeDataSource.addSqlMapperNamespacePrefix("codedata.");

        return codeDataSource;
    }

    @Bean
    public FileCodeDataSource fileCodeDataSource() {
        FileCodeDataSource codeDataSource = new FileCodeDataSource();
        codeDataSource.setMessageSourceAccessor(messageSourceAccessor);
        codeDataSource.addDirectories("classpath:/configuration/codedata");

        return codeDataSource;
    }

}
