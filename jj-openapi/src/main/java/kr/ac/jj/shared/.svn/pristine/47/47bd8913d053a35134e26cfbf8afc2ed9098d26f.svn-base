package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.logging.StatementLoggingService;

@Intercepts({ //
        @Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }), //
        @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }) //
})
public class StatementLogInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(StatementLogInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
        StringBuilder parameter = new StringBuilder();

        if (parameterMappingList.size() > 0) {
            for (ParameterMapping parameterMapping : parameterMappingList) {
                parameter.append(parameterMapping.toString() + "\n");
            }
            Object parameterObject = boundSql.getParameterObject();
            parameter.append(StringUtils.leftPad("", 80, "-") + "\n");
            parameter.append(parameterObject.toString());
        }

        Object proceed;

        try {
            proceed = invocation.proceed();
        } catch (InvocationTargetException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        } finally {
            this.createLog(sql, parameter.toString());
        }

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void createLog(String sql, String parameter) {
        if (StringUtils.equals(MDC.get("log.jdbc.disabled"), "1")) {
            return;
        }

        try {
            MDC.put("log.jdbc.disabled", "1");

            StatementLoggingService loggingService;

            try {
                loggingService = ApplicationContextUtil.getBean(StatementLoggingService.class);
            } catch (NoSuchBeanDefinitionException e) {
                loggingService = null;
            }

            if (loggingService != null) {
                String id = MDC.get(MappedStatement.class.getName() + ".id");

                loggingService.createLog(id, sql, parameter);
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            MDC.remove("log.jdbc.disabled");
        }
    }

}
