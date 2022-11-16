package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.interceptor;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.MDC;

@Intercepts({ //
        @Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }), //
        @Signature(type = StatementHandler.class, method = "update", args = { Statement.class }) //
})
public class StatementLogInterceptor implements Interceptor {
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

        MDC.put(BoundSql.class.getName() + ".sql", sql);
        MDC.put(BoundSql.class.getName() + ".parameter", parameter.toString());

        Object proceed = invocation.proceed();

        MDC.remove(BoundSql.class.getName() + ".sql");
        MDC.remove(BoundSql.class.getName() + ".parameter");

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
