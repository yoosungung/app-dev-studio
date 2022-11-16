package kr.ac.jj.shared.infrastructure.scheduler.interceptor;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor.BaseJobInterceptor;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;
import kr.ac.jj.shared.infrastructure.logging.util.LoggingUtil;
import kr.ac.jj.shared.infrastructure.scheduler.service.SchedulerJobServiceImpl;

/**
 * 스케줄러 Job Interceptor
 */
@Component
public class SchedulerJobInterceptor implements BaseJobInterceptor {

    private @Autowired SchedulerJobServiceImpl schedulerJobService;

    @Override
    public void beforeAddJobList() {
        schedulerJobService.initialize();
    }

    @Override
    public void afterAddJob(SchedulerJobConfig jobConfig, List<JobDetailTrigger> jobDetailTriggerList) {
        schedulerJobService.create(jobConfig, jobDetailTriggerList);
    }

    @Override
    public void afterAddJobList() {
    }

    @Override
    public String executeJobBefore(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
        LoggingUtil.setLoggingType("scheduler-" + jobConfig.getScheduleKey());

        return schedulerJobService.createLog(jobConfig, jobDetailTrigger, context);
    }

    @Override
    public void executeJobSuccess(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
        schedulerJobService.updateLog(executeId, jobConfig, jobDetailTrigger, context, null);
    }

    @Override
    public void executeJobFailure(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context, Exception exception) {
        schedulerJobService.updateLog(executeId, jobConfig, jobDetailTrigger, context, exception);
    }

    @Override
    public void executeJobFinally(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
        LoggingUtil.removeLoggingType();
    }

}
