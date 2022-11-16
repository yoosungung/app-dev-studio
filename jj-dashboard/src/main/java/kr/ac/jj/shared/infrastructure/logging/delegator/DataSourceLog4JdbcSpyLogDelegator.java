package kr.ac.jj.shared.infrastructure.logging.delegator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.sql.Spy;

public class DataSourceLog4JdbcSpyLogDelegator extends Slf4jSpyLogDelegator {

    @Override
    public void sqlOccurred(Spy spy, String methodCall, String sql) {
        super.sqlOccurred(spy, methodCall, sql);

        this.putData(sql, null);
    }

    @Override
    public void sqlTimingOccurred(Spy spy, long execTime, String methodCall, String sql) {
        super.sqlTimingOccurred(spy, execTime, methodCall, sql);

        this.putData(sql, Long.toString(execTime));
    }

    private void putData(String sql, String execTime) {
        if (StringUtils.equals(MDC.get("log.jdbc.disabled"), "1")) {
            return;
        }

        MDC.put("log.jdbc.sql", sql);
        MDC.put("log.jdbc.execTime", execTime);
    }

}
