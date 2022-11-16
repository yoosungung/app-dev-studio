package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.interceptor;

import java.lang.reflect.InvocationTargetException;
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

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class DataResultHandleInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return this.proceedInvocation(invocation);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private Object proceedInvocation(Invocation invocation) {
        Object[] args = invocation.getArgs();

        DataResultHandler resultHandler = null;

        if (args[3] != null && args[3] instanceof DataResultHandler) {
            resultHandler = (DataResultHandler) args[3];
        }

        if (resultHandler == null) {
            try {
                return invocation.proceed();
            } catch (InvocationTargetException e) {
                throw new BaseException(e);
            } catch (IllegalAccessException e) {
                throw new BaseException(e);
            }
        }

        {
            Object proceedResult = null;

            try {
                if (resultHandler != null) {
                    resultHandler.handleStart();
                }
                proceedResult = invocation.proceed();
                if (resultHandler != null) {
                    resultHandler.handleSuccess();
                }
            } catch (InvocationTargetException e) {
                if (resultHandler != null) {
                    resultHandler.handleFailure();
                }
                throw new BaseException(e);
            } catch (IllegalAccessException e) {
                if (resultHandler != null) {
                    resultHandler.handleFailure();
                }
                throw new BaseException(e);
            } catch (RuntimeException e) {
                if (resultHandler != null) {
                    resultHandler.handleFailure();
                }
                throw e;
            } finally {
                if (resultHandler != null) {
                    resultHandler.handleFinally();
                }
            }

            return proceedResult;
        }
    }
}
