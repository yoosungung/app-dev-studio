package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.variables;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.helper.SqlHelperUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextProvider;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

public class SqlVariables {

    public static final String BIND_KEY = "$var";

    private static CodeDataSourceAccessor codeDataSourceAccessor;

    private DataSourceSpec dataSourceSpec;
    private String schemaNamePrefix;
    private String schemaNameSuffix;

    public void setDataSourceSpec(DataSourceSpec dataSourceSpec) {
        this.dataSourceSpec = dataSourceSpec;
    }

    private CodeDataSourceAccessor getCodeDataSourceAccessor() {
        if (codeDataSourceAccessor == null && ApplicationContextProvider.getApplicationContext() != null) {
            codeDataSourceAccessor = ApplicationContextUtil.getBean(CodeDataSourceAccessor.class);
        }

        return codeDataSourceAccessor;
    }

    public String getDatabaseId() {
        if (this.dataSourceSpec == null) {
            return null;
        }

        return this.dataSourceSpec.getDatabaseId();
    }

    public String getSchemaNamePrefix() {
        if (this.schemaNamePrefix == null) {
            SqlHelper sqlHelper = SqlHelperUtil.getSqlHelper(this.dataSourceSpec.getDataSource());
            this.schemaNamePrefix = sqlHelper.getSchemaNamePrefix();
        }

        return this.schemaNamePrefix;
    }

    public String getSchemaNameSuffix() {
        if (this.schemaNameSuffix == null) {
            SqlHelper sqlHelper = SqlHelperUtil.getSqlHelper(this.dataSourceSpec.getDataSource());
            this.schemaNameSuffix = sqlHelper.getSchemaNameSuffix();
        }

        return this.schemaNameSuffix;
    }

    public Date getSystemDate() {
        return new Date();
    }

    public Locale getDefaultLocale() {
        return Locale.getDefault();
    }

    public String getDefaultLocaleString() {
        return this.getDefaultLocale().toString();
    }

    public String getCodeData(String codeDataPath, String columnName) {
        return this.getCodeData(codeDataPath, columnName, null, this.getDefaultLocale());
    }

    public String getCodeData(String codeDataPath, String columnName, String defaultText) {
        return this.getCodeData(codeDataPath, columnName, defaultText, this.getDefaultLocale());
    }

    protected String getCodeData(String codeDataPath, String columnName, String defaultText, Locale locale) {
        BaseMapList codeDataList = this.getCodeDataSourceAccessor().getList(codeDataPath, locale);

        StringBuilder sb = new StringBuilder();

        sb.append("CASE ").append(columnName);

        for (int i = 0; i < codeDataList.size(); i++) {
            String code = codeDataList.get(i, "code") + "";
            String name = codeDataList.get(i, "name") + "";

            sb.append(" WHEN '").append(code.replaceAll("'", "''")).append("'");
            sb.append(" THEN '").append(name.replaceAll("'", "''")).append("'");
        }

        if (StringUtils.isNotEmpty(defaultText)) {
            sb.append(" ELSE ").append(defaultText);
        }

        sb.append(" END");

        return sb.toString();
    }

}
