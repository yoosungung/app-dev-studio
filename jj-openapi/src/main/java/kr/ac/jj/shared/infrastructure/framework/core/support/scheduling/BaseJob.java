package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config.SchedulerConfig;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor.BaseJobInterceptor;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.util.BaseJobSchedulerUtil;

public abstract class BaseJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(BaseJob.class);

    private @Autowired SchedulerConfig schedulerConfig;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        JobDetailTrigger jobDetailTrigger = new JobDetailTrigger(jobDetail, trigger);
        int triggerNo = jobDetailTrigger.getTriggerNo();

        if (triggerNo != 0 && BooleanUtils.isFalse(schedulerConfig.getEnable())) {
            return;
        }

        String scheduleKey = jobDetail.getKey().getName();
        SchedulerJobConfig jobConfig = new SchedulerJobConfig(scheduleKey);

        if (triggerNo != 0 && !jobConfig.isEnable()) {
            return;
        }

        if (triggerNo != 0 && !jobConfig.isHoldingJobContinuouslyExecute()) {
            String scheduledFireTime = DateFormatUtils.format(context.getScheduledFireTime(), "yyyyMMdd-HHmmss");
            String fireTime = DateFormatUtils.format(context.getFireTime(), "yyyyMMdd-HHmmss");

            if (!scheduledFireTime.equals(fireTime)) {
                return;
            }
        }

        BaseJobInterceptor jobInterceptor = BaseJobSchedulerUtil.getJobInterceptor();
        String executeId = null;

        try {
            executeId = jobInterceptor.executeJobBefore(jobConfig, jobDetailTrigger, context);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }

        try {

            this.executeJob(jobConfig, jobDetailTrigger, context);

            try {
                jobInterceptor.executeJobSuccess(executeId, jobConfig, jobDetailTrigger, context);
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }

        } catch (RuntimeException e) {
            try {
                jobInterceptor.executeJobFailure(executeId, jobConfig, jobDetailTrigger, context, e);
            } catch (RuntimeException e2) {
                log.error(e.getMessage(), e2);
            }

            throw e;
        } finally {
            try {
                jobInterceptor.executeJobFinally(executeId, jobConfig, jobDetailTrigger, context);
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public abstract void executeJob(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context);

}
