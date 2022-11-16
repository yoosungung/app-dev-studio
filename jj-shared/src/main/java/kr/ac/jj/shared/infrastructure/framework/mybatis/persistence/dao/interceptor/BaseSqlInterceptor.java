package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.variables.SqlVariables;

@Intercepts({ //
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }), //
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) //
})
public class BaseSqlInterceptor implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(BaseSqlInterceptor.class);

    private SqlVariables sqlVariables;

    public void setSqlVariables(SqlVariables sqlVariables) {
        this.sqlVariables = sqlVariables;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();

        if (args[1] == null) {
            args[1] = new HashMap<String, Object>();
        }

        final MappedStatement ms = (MappedStatement) args[0];

        log.info("=== SQL Mapper ==> {}", ms.getId());

        if (this.sqlVariables != null && (args[1] instanceof Map)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> parameter = (Map<String, Object>) args[1];

            if (!parameter.containsKey(SqlVariables.BIND_KEY)) {
                parameter.put(SqlVariables.BIND_KEY, this.sqlVariables);
            }
        }

        MDC.put(MappedStatement.class.getName() + ".id", ms.getId());

        Object proceed = invocation.proceed();

        MDC.remove(MappedStatement.class.getName() + ".id");

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
