package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.helper;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class SqlMapHelperForOracle extends SqlMapHelperDefault {

    public SqlMapHelperForOracle() {
        this.setSchemaNameWrapper("\"", "\"");
        this.setSchemaNameToUpperCase(true);
    }

    @Override
    public boolean checkDatabaseMetaData(DatabaseMetaData metaData) {
        try {
            String databaseProductName = metaData.getDatabaseProductName();
            return "Oracle".equals(databaseProductName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkDatabaseKind(String databaseKind) {
        return StringUtils.equals(databaseKind, "oracle");
    }

    @Override
    public String getDriverClassName() {
        return "oracle.jdbc.OracleDriver";
    }

    @Override
    public String getLimitZeroPrefix() {
        return "SELECT * FROM (";
    }

    @Override
    public String getLimitZeroSuffix() {
        return ") WHERE ROWNUM <= 0";
    }

    @Override
    public String getPagingPrefix() {
        if (this.getPagingInfo().getCurrentPageNo() == -1) {
            return "SELECT * FROM (";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT TEMP__2.* FROM (\n");
        sb.append("SELECT " + this.getSqlRecordCount() + " AS TOTAL_RECORD_COUNT\n");
        sb.append("     , ROW_NUMBER() OVER(" + this.getOrderByString("ORDER BY ROWNUM ASC") + ") AS RN\n");
        sb.append("     , TEMP__1.*\n");
        sb.append("FROM   (");

        return sb.toString();
    }

    @Override
    public String getPagingSuffix() {
        if (this.getPagingInfo().getCurrentPageNo() == -1) {
            return ") WHERE ROWNUM <= #{paging.recordCountPerPage}";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("       ) TEMP__1) TEMP__2");

        if (this.getPagingInfo().getRecordCountPerPage() != -1) {
            sb.append(
                    "\nWHERE  TEMP__2.RN > #{paging.currentPageFirstRecordIndex} AND TEMP__2.RN <= #{paging.currentPageLastRecordNo}");
        }

        return sb.toString();
    }

    @Override
    public String getSqlRecordCount() {
        return "COUNT(*) OVER()";
    }

    @Override
    public String getSqlIfnull(String expr1, String expr2) {
        return "NVL(" + expr1 + ", " + expr2 + ")";
    }

    @Override
    public String getSqlUpperCase(String str) {
        return "UPPER(" + str + ")";
    }

    @Override
    public String getSqlLikeBoth(String str) {
        return "'%' || " + str + " || '%'";
    }

    @Override
    public String getSqlLikeLeft(String str) {
        return str + " || '%'";
    }

    @Override
    public String getSqlLikeRight(String str) {
        return "'%' || " + str;
    }

}
