package kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class DataResultHandler<T> implements ResultHandler<T> {

    public void handleStart() {
    }

    @Override
    public void handleResult(ResultContext<? extends T> resultContext) {
    }

    public void handleSuccess() {
    }

    public void handleFailure() {
    }

    public void handleFinally() {
    }

}
