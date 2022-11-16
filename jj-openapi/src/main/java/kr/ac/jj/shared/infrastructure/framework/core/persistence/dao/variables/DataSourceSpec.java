package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.variables;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.SysException;

public class DataSourceSpec {

    private static final Logger log = LoggerFactory.getLogger(DataSourceSpec.class);

    private final DataSource dataSource;

    public DataSourceSpec(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource getDataSource() {
        return this.dataSource;
    }

    public String getDatabaseId() {
        throw new SysException("Database id provider is not available.");
    }

    protected String getDatabaseProductName(DataSource dataSource) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            return metaData.getDatabaseProductName();
        } catch (SQLException e) {
            throw new BaseException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.warn("Error ocurred while closing " + connection, e);
                }
            }
        }
    }

}
