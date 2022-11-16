package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.datasource.props;

import java.util.Properties;

public class DatabaseIdProperties extends Properties {
    private static final long serialVersionUID = -8774250548697803892L;

    public DatabaseIdProperties() {
        this.setProperty("MySQL", "mariadb");
        this.setProperty("Microsoft SQL Server", "mssql");
        this.setProperty("Oracle", "oracle");
        this.setProperty("Tibero", "oracle");
        this.setProperty("PostgreSQL", "postgresql");
        this.setProperty("HSQL Database Engine", "hsqldb");
    }
}
