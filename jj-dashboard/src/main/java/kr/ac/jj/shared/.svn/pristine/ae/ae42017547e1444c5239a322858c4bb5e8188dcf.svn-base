package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlMapHelperUtil {

    private static final Logger log = LoggerFactory.getLogger(SqlMapHelperUtil.class);

    private static SqlMapHelper defaultSqlMapHelper;
    private static Set<SqlMapHelper> sqlMapHelperSet;

    private static void initSqlMapHelperSet() {
        if (sqlMapHelperSet != null) {
            return;
        }

        defaultSqlMapHelper = new SqlMapHelperDefault();
        sqlMapHelperSet = new HashSet<SqlMapHelper>();
    }

    public static void addSqlMapHelper(SqlMapHelper sqlMapHelper) {
        initSqlMapHelperSet();

        sqlMapHelperSet.add(sqlMapHelper);
    }

    public static SqlMapHelper getSqlMapHelper(DataSource dataSource) {
        Connection connection = null;
        DatabaseMetaData metaData;

        try {
            connection = dataSource.getConnection();
            metaData = connection.getMetaData();
            log.debug("DatabaseMetaData.getDatabaseProductName() ==> {}", metaData.getDatabaseProductName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    log.error("sql error", e);
                }
            }
        }

        initSqlMapHelperSet();

        for (SqlMapHelper sqlMapHelper : sqlMapHelperSet) {
            if (sqlMapHelper.checkDatabaseMetaData(metaData)) {
                return newSqlMapHelper(sqlMapHelper);
            }
        }

        return newSqlMapHelper(defaultSqlMapHelper);
    }

    public static SqlMapHelper getSqlMapHelper(String databaseKind) {
        initSqlMapHelperSet();

        for (SqlMapHelper sqlMapHelper : sqlMapHelperSet) {
            if (sqlMapHelper.checkDatabaseKind(databaseKind)) {
                return newSqlMapHelper(sqlMapHelper);
            }
        }

        return newSqlMapHelper(defaultSqlMapHelper);
    }

    private static SqlMapHelper newSqlMapHelper(SqlMapHelper sqlMapHelper) {
        try {
            return sqlMapHelper.getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

}
