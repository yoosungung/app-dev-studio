package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor;

import java.util.List;

import org.quartz.JobExecutionContext;

import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;

public class DefaultJobInterceptor implements BaseJobInterceptor {

    @Override
    public void beforeAddJobList() {
    }

    @Override
    public void afterAddJob(SchedulerJobConfig jobConfig, List<JobDetailTrigger> jobDetailTriggerList) {
    }

    @Override
    public void afterAddJobList() {
    }

    @Override
    public String executeJobBefore(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
        return null;
    }

    @Override
    public void executeJobSuccess(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
    }

    @Override
    public void executeJobFailure(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context, Exception exception) {
    }

    @Override
    public void executeJobFinally(String executeId, SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {
    }

}
