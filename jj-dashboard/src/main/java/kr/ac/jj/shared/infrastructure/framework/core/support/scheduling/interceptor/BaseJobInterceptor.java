package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor;

import java.util.List;

import org.quartz.JobExecutionContext;

import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;

public interface BaseJobInterceptor {

    public void beforeAddJobList();

    public void afterAddJob(SchedulerJobConfig jobConfig, List<JobDetailTrigger> jobDetailTriggerList);

    public void afterAddJobList();

    public String executeJobBefore(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context);

    public void executeJobSuccess(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context);

    public void executeJobFailure(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context, Exception exception);

    public void executeJobFinally(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context);

}
