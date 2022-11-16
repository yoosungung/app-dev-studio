package kr.ac.jj.shared.infrastructure.framework.core.persistence.transaction.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.transaction.props.TransactionConfigProperties;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.transaction.props.TransactionConfigProperties.IsolationLevelNames;

public class TransactionInterceptorFactory {

    private final TransactionConfigProperties transactionConfig;

    private String attributeName;
    private TransactionManager transactionManager;

    public TransactionInterceptorFactory(TransactionConfigProperties transactionConfig) {
        this.transactionConfig = transactionConfig;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public TransactionManager getTransactionManager() {
        return this.transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public TransactionInterceptor getObject() {
        IsolationLevelNames isolationLevel = null;
        String[] readOnlyMethodNames = null;

        if (this.transactionConfig != null) {
            isolationLevel = this.transactionConfig.getIsolationLevel();
            readOnlyMethodNames = this.transactionConfig.getReadOnlyMethodNames();
        }

        List<NameMatchTransactionAttributeSource> transactionAttributeSourceList = new ArrayList<NameMatchTransactionAttributeSource>();

        NameMatchTransactionAttributeSource transactionAttributeSource;
        RuleBasedTransactionAttribute transactionAttribute;

        if (readOnlyMethodNames != null) {
            transactionAttribute = new RuleBasedTransactionAttribute();
            transactionAttribute.setName(this.attributeName + ".readOnly");
            if (isolationLevel != null) {
                transactionAttribute.setIsolationLevel(isolationLevel.getValue());
            }
            transactionAttribute.setReadOnly(true);
            transactionAttributeSource = new NameMatchTransactionAttributeSource();
            for (String methodName : readOnlyMethodNames) {
                transactionAttributeSource.addTransactionalMethod(methodName, transactionAttribute);
            }
            transactionAttributeSourceList.add(transactionAttributeSource);
        }

        {
            transactionAttribute = new RuleBasedTransactionAttribute();
            transactionAttribute.setName(this.attributeName);
            if (isolationLevel != null) {
                transactionAttribute.setIsolationLevel(isolationLevel.getValue());
            }
            transactionAttributeSource = new NameMatchTransactionAttributeSource();
            transactionAttributeSource.addTransactionalMethod("*", transactionAttribute);
            transactionAttributeSourceList.add(transactionAttributeSource);
        }

        TransactionAttributeSource[] transactionAttributeSources = new TransactionAttributeSource[transactionAttributeSourceList
                .size()];
        transactionAttributeSourceList.toArray(transactionAttributeSources);

        TransactionInterceptor txAdvice = new TransactionInterceptor();
        txAdvice.setTransactionManager(this.transactionManager);
        txAdvice.setTransactionAttributeSources(transactionAttributeSources);

        return txAdvice;
    }

}
