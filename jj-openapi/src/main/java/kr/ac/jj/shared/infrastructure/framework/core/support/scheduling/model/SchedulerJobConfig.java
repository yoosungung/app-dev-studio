package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.profiles.ProfilesUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.BaseJob;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config.SchedulerConfig;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config.SchedulerConfig.JobDetailConfig;

public class SchedulerJobConfig implements Serializable {

    private static final long serialVersionUID = -7524620025618723066L;

    private final String scheduleKey;

    private final String name;
    private final Class<? extends BaseJob> jobClass;
    private final List<String> cronExpressionList;
    private final int startDelay;
    private final boolean holdingJobContinuouslyExecute;
    private final String[] profiles;

    public SchedulerJobConfig(String scheduleKey) {
        SchedulerConfig schedulerConfig = ApplicationContextUtil.getConfigBean(SchedulerConfig.class);

        this.scheduleKey = scheduleKey;

        JobDetailConfig jobDetailConfig = schedulerConfig.getJobDetails().get(scheduleKey);

        this.name = jobDetailConfig.getName();
        this.cronExpressionList = jobDetailConfig.getCronExpressionList();
        this.jobClass = jobDetailConfig.getJobClass();
        this.startDelay = ObjectUtils.defaultIfNull(jobDetailConfig.getStartDelay(), 0);
        this.holdingJobContinuouslyExecute = ObjectUtils
                .defaultIfNull(jobDetailConfig.getHoldingJobContinuouslyExecute(), false);
        this.profiles = jobDetailConfig.getProfiles();
    }

    public String getScheduleKey() {
        return this.scheduleKey;
    }

    public String getName() {
        return this.name;
    }

    public Class<? extends BaseJob> getJobClass() {
        return this.jobClass;
    }

    public List<String> getCronExpressionList() {
        return this.cronExpressionList;
    }

    public int getStartDelay() {
        return this.startDelay;
    }

    public boolean isHoldingJobContinuouslyExecute() {
        return this.holdingJobContinuouslyExecute;
    }

    public String[] getProfiles() {
        return this.profiles;
    }

    public boolean isEnable() {
        return this.isEnable(false);
    }

    public boolean isEnable(boolean withClusteredProfiles) {
        if (this.profiles == null) {
            return true;
        }

        if (ProfilesUtil.isActivated(this.profiles)) {
            return true;
        }

        if (!withClusteredProfiles) {
            return false;
        }

        SchedulerConfig schedulerConfig = ApplicationContextUtil.getConfigBean(SchedulerConfig.class);

        String[] clusteredProfiles = schedulerConfig.getClusteredProfiles();

        if (ProfilesUtil.isActivated(clusteredProfiles)) {
            for (String profile : this.profiles) {
                if (ArrayUtils.contains(clusteredProfiles, profile)) {
                    return true;
                }
            }
        }

        return false;
    }

}
