package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

public class JobDetailTrigger {

    private final JobDetail jobDetail;
    private final Trigger trigger;

    public JobDetailTrigger(JobDetail jobDetail, Trigger trigger) {
        this.jobDetail = jobDetail;
        this.trigger = trigger;
    }

    public JobDetail getJobDetail() {
        return this.jobDetail;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public String getCronExpression() {
        if (this.trigger == null) {
            return null;
        }

        JobDataMap jobDataMap = this.trigger.getJobDataMap();

        if (jobDataMap == null) {
            return null;
        }

        return jobDataMap.getString("cronExpression");
    }

    public int getTriggerNo() {
        if (this.trigger == null) {
            return -1;
        }

        JobDataMap jobDataMap = this.trigger.getJobDataMap();

        if (jobDataMap == null || !jobDataMap.containsKey("triggerNo")) {
            return -1;
        }

        return jobDataMap.getIntValue("triggerNo");
    }

}
