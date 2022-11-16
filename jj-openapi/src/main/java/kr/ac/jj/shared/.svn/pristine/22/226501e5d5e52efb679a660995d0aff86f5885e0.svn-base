package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class SqlMapHelperForMariaDB extends SqlMapHelperDefault {

    public SqlMapHelperForMariaDB() {
        this.setSchemaNameWrapper("`", "`");
        this.setSchemaNameToUpperCase(true);
    }

    @Override
    public boolean checkDatabaseMetaData(DatabaseMetaData metaData) {
        try {
            String databaseProductName = metaData.getDatabaseProductName();
            return "MySQL".equals(databaseProductName) || "MariaDB".equals(databaseProductName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkDatabaseKind(String databaseKind) {
        return StringUtils.equals(databaseKind, "mariadb");
    }

    @Override
    public String getDriverClassName() {
        return "org.mariadb.jdbc.Driver";
    }

    @Override
    public String getLimitZeroPrefix() {
        return null;
    }

    @Override
    public String getLimitZeroSuffix() {
        return "LIMIT 0";
    }

    @Override
    public String getPagingPrefix() {
        if (this.getPagingInfo().getCurrentPageNo() == -1) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT TEMP__2.* FROM (\n");
        sb.append("SELECT " + this.getSqlRecordCount() + " AS TOTAL_RECORD_COUNT\n");
        sb.append("     , TEMP__1.*\n");
        sb.append("FROM   (");

        return sb.toString();
    }

    @Override
    public String getPagingSuffix() {
        if (this.getPagingInfo().getCurrentPageNo() == -1) {
            return "LIMIT #{paging.recordCountPerPage}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("       ) TEMP__1) TEMP__2\n");

        if (this.getPagingInfo().getRecordCountPerPage() != -1) {
            sb.append(this.getOrderByString("ORDER BY 1 ASC")
                    + " LIMIT #{paging.recordCountPerPage} OFFSET #{paging.currentPageFirstRecordIndex}");
        } else {
            sb.append(this.getOrderByString(""));
        }

        return sb.toString();
    }

    @Override
    public String getSqlRecordCount() {
        return "COUNT(*) OVER()";
    }

    @Override
    public String getSqlIfnull(String expr1, String expr2) {
        return "IFNULL(" + expr1 + ", " + expr2 + ")";
    }

    @Override
    public String getSqlUpperCase(String str) {
        return "UPPER(" + str + ")";
    }

    @Override
    public String getSqlLikeBoth(String str) {
        return "CONCAT_WS('', '%', " + str + ", '%')";
    }

    @Override
    public String getSqlLikeLeft(String str) {
        return "CONCAT_WS('', " + str + ", '%')";
    }

    @Override
    public String getSqlLikeRight(String str) {
        return "CONCAT_WS('', '%', " + str + ")";
    }

}
