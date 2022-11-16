package kr.ac.jj.shared.infrastructure.logging.util;

import java.util.Date;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.MDC;

import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.model.sys.log.query.TbSysLogQuery;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.logging.service.LoggingServiceImpl;
import net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.sql.Spy;

public class DataSourceLog4JdbcSpyLogDelegator extends Slf4jSpyLogDelegator {

    @Override
    public void sqlOccurred(Spy spy, String methodCall, String sql) {
        SharedConfigProperties sharedConfig = ApplicationContextUtil.getConfigBean(SharedConfigProperties.class);

        if ("1".equals(MDC.get("log.jdbc.disabled")) || "sub".equals(LoggingUtil.getLoggingType())
                || LoggingUtil.getTbSysLog() == null
                || !(sharedConfig != null && sharedConfig.getLog().getQuery().isEnable())) {
            super.sqlOccurred(spy, methodCall, sql);
            return;
        }

        try {

            MDC.put("log.jdbc.disabled", "1");

            TbSysLogQuery tbSysLogQuery = new TbSysLogQuery();
            tbSysLogQuery.setQueryDt(new Date());
            tbSysLogQuery.setQueryId(MDC.get(MappedStatement.class.getName() + ".id"));
            tbSysLogQuery.setQuerySntenc(MDC.get(BoundSql.class.getName() + ".sql"));
            tbSysLogQuery.setQueryParamtr(MDC.get(BoundSql.class.getName() + ".parameter"));
            tbSysLogQuery.setQuerySntencCmplt(sql);

            LoggingServiceImpl loggingService = ApplicationContextUtil.getBean(LoggingServiceImpl.class);
            loggingService.createQuery(tbSysLogQuery);

        } catch (RuntimeException e) {
            throw e;
        } finally {
            MDC.remove("log.jdbc.disabled");
        }
    }

}
