package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.variables;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.variables.DataSourceSpec;

public class MyBatisDataSourceSpec extends DataSourceSpec {
    private final DatabaseIdProvider databaseIdProvider;

    public MyBatisDataSourceSpec(DataSource dataSource, DatabaseIdProvider databaseIdProvider) {
        super(dataSource);

        this.databaseIdProvider = databaseIdProvider;
    }

    @Override
    public String getDatabaseId() {
        try {
            return this.databaseIdProvider.getDatabaseId(this.getDataSource());
        } catch (SQLException e) {
            throw new BaseException(e);
        }
    }
}
