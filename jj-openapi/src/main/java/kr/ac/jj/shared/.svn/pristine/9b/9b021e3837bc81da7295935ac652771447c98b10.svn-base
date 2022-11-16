package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.variables;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.SysException;

public class DataSourceSpec {

    private static final Logger log = LoggerFactory.getLogger(DataSourceSpec.class);

    private final DataSource dataSource;
    private final Map<String, String[]> nameDecorators;

    public DataSourceSpec(DataSource dataSource) {
        this.dataSource = dataSource;
        this.nameDecorators = new LinkedHashMap<String, String[]>();

        this.setNameDecorator("Microsoft SQL Server", "[", "]");
        this.setNameDecorator("MySQL", "`");
        this.setNameDecorator("Oracle", "\"");
        this.setNameDecorator("Tibero", "\"");
    }

    protected DataSource getDataSource() {
        return this.dataSource;
    }

    public String getDatabaseId() {
        throw new SysException("Database id provider is not available.");
    }

    public String getNamePrefix() {
        if (this.dataSource == null) {
            return null;
        }

        String databaseProductName = this.getDatabaseProductName(this.dataSource);
        String[] decorator = this.getNameDecorator(databaseProductName);

        if (decorator != null) {
            return decorator[0];
        }

        return "";
    }

    public String getNameSuffix() {
        if (this.dataSource == null) {
            return null;
        }

        String databaseProductName = this.getDatabaseProductName(this.dataSource);
        String[] decorator = this.getNameDecorator(databaseProductName);

        if (decorator != null) {
            return decorator[1];
        }

        return "";
    }

    private String[] getNameDecorator(String key) {
        for (Entry<String, String[]> entry : nameDecorators.entrySet()) {
            if (key.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }

    public void setNameDecorator(String key, String prefix) {
        this.setNameDecorator(key, prefix, prefix);
    }

    public void setNameDecorator(String key, String prefix, String suffix) {
        String[] decorator = new String[] { prefix, suffix };

        this.nameDecorators.put(key, decorator);
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
