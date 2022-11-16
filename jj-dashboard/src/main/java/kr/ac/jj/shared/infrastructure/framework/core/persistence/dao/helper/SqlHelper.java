package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper;

import java.sql.DatabaseMetaData;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo.OrderBy;

public abstract class SqlHelper {

    public static final String WRAPPER_KEY = "_SELECT_QUERY_WRAPPER_";

    public enum SelectWrappingTypes {
        LIMIT_ZERO, PAGING
    }

    private String schemaNamePrefix;
    private String schemaNameSuffix;
    private boolean schemaNameToUpperCase;

    private PagingInfo pagingInfo;

    public String getSchemaNamePrefix() {
        return this.schemaNamePrefix;
    }

    public String getSchemaNameSuffix() {
        return this.schemaNameSuffix;
    }

    public void setSchemaNameWrapper(String schemaNamePrefix, String schemaNameSuffix) {
        this.schemaNamePrefix = schemaNamePrefix;
        this.schemaNameSuffix = schemaNameSuffix;
    }

    public String getWrappedSchemaName(String schemaName) {
        return this.schemaNamePrefix + schemaName + this.schemaNameSuffix;
    }

    public boolean isSchemaNameToUpperCase() {
        return this.schemaNameToUpperCase;
    }

    public void setSchemaNameToUpperCase(boolean schemaNameToUpperCase) {
        this.schemaNameToUpperCase = schemaNameToUpperCase;
    }

    public String getSelectPrefix() {
        if (this.pagingInfo == null) {
            return null;
        }

        String prefix = this.getPagingPrefix();

        if (StringUtils.isEmpty(prefix)) {
            return null;
        }

        return "\n" + prefix + "\n";
    }

    public String getSelectSuffix() {
        if (this.pagingInfo == null) {
            return null;
        }

        String suffix = this.getPagingSuffix();

        if (StringUtils.isEmpty(suffix)) {
            return null;
        }

        return "\n" + suffix;
    }

    public String getWrappedSelectSql(String originSql, SelectWrappingTypes wrappingType) {
        String prefix;
        String suffix;

        if (wrappingType == SelectWrappingTypes.LIMIT_ZERO) {
            prefix = this.getLimitZeroPrefix();
            suffix = this.getLimitZeroSuffix();
        } else if (wrappingType == SelectWrappingTypes.PAGING) {
            prefix = this.getPagingPrefix();
            suffix = this.getPagingSuffix();
        } else {
            return originSql;
        }

        StringBuilder sql = new StringBuilder();

        if (StringUtils.isNotEmpty(prefix)) {
            sql.append(prefix + "\n");
        }

        sql.append(originSql);

        if (StringUtils.isNotEmpty(suffix)) {
            sql.append("\n" + suffix);
        }

        return sql.toString();
    }

    public PagingInfo getPagingInfo() {
        return this.pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public String getOrderByString(String defaultOrderByString) {
        OrderBy orderBy = this.pagingInfo.getOrderBy();

        if (orderBy != null) {
            String orderByString = orderBy.toString(this.schemaNamePrefix, this.schemaNameSuffix,
                    this.schemaNameToUpperCase);

            if (StringUtils.isNotEmpty(orderByString)) {
                return "ORDER BY " + orderByString;
            }
        }

        return defaultOrderByString;
    }

    public abstract boolean checkDatabaseMetaData(DatabaseMetaData metaData);

    public abstract boolean checkDatabaseKind(String databaseKind);

    public abstract String getDriverClassName();

    public abstract String getLimitZeroPrefix();

    public abstract String getLimitZeroSuffix();

    public abstract String getPagingPrefix();

    public abstract String getPagingSuffix();

    public abstract String getSqlRecordCount();

    public abstract String getSqlIfnull(String expr1, String expr2);

    public abstract String getSqlUpperCase(String str);

    public abstract String getSqlLikeBoth(String str);

    public abstract String getSqlLikeLeft(String str);

    public abstract String getSqlLikeRight(String str);

}
