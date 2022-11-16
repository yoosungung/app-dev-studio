package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.util;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config.SchedulerConfig;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor.BaseJobInterceptor;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor.DefaultJobInterceptor;

public class BaseJobSchedulerUtil {

    private static DefaultJobInterceptor defaultJobInterceptor;

    public static BaseJobInterceptor getJobInterceptor() {
        SchedulerConfig schedulerConfig = ApplicationContextUtil.getConfigBean(SchedulerConfig.class);

        Class<? extends BaseJobInterceptor> jobInterceptorClass = schedulerConfig.getJobInterceptor();

        if (jobInterceptorClass != null) {
            return ApplicationContextUtil.getBean(jobInterceptorClass);
        }

        if (defaultJobInterceptor == null) {
            defaultJobInterceptor = new DefaultJobInterceptor();
        }

        return defaultJobInterceptor;
    }

}
