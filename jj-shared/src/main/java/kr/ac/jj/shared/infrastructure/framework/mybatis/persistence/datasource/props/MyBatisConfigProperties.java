package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.datasource.props;

import org.springframework.transaction.TransactionDefinition;

public class MyBatisConfigProperties {

    private String[] mapperLocations;
    private Transaction transaction;

    public String[] getMapperLocations() {
        return this.mapperLocations;
    }

    public void setMapperLocations(String[] mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public static class Transaction {

        private IsolationLevelNames isolationLevel;
        private String[] readOnlyMethodNames;

        public IsolationLevelNames getIsolationLevel() {
            return this.isolationLevel;
        }

        public void setIsolationLevel(IsolationLevelNames isolationLevel) {
            this.isolationLevel = isolationLevel;
        }

        public String[] getReadOnlyMethodNames() {
            return this.readOnlyMethodNames;
        }

        public void setReadOnlyMethodNames(String[] readOnlyMethodNames) {
            this.readOnlyMethodNames = readOnlyMethodNames;
        }

    }

    public enum IsolationLevelNames {

        ISOLATION_DEFAULT(TransactionDefinition.ISOLATION_DEFAULT), //
        ISOLATION_READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED), //
        ISOLATION_READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED), //
        ISOLATION_REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ), //
        ISOLATION_SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE), //
        ;

        private final int value;

        private IsolationLevelNames(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

}
