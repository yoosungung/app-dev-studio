package kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.config;

import java.util.List;
import java.util.Map;

import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.BaseJob;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.interceptor.BaseJobInterceptor;

public class SchedulerConfig {

    private Boolean enable;
    private Class<? extends BaseJobInterceptor> jobInterceptor;
    private Map<String, JobDetailConfig> jobDetails;
    private String[] clusteredProfiles;

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Class<? extends BaseJobInterceptor> getJobInterceptor() {
        return this.jobInterceptor;
    }

    public void setJobInterceptor(Class<? extends BaseJobInterceptor> jobInterceptor) {
        this.jobInterceptor = jobInterceptor;
    }

    public Map<String, JobDetailConfig> getJobDetails() {
        return this.jobDetails;
    }

    public void setJobDetails(Map<String, JobDetailConfig> jobDetails) {
        this.jobDetails = jobDetails;
    }

    public String[] getClusteredProfiles() {
        return this.clusteredProfiles;
    }

    public void setClusteredProfiles(String[] clusteredProfiles) {
        this.clusteredProfiles = clusteredProfiles;
    }

    public static class JobDetailConfig {

        private String name;
        private Class<? extends BaseJob> jobClass;
        private Boolean holdingJobContinuouslyExecute;
        private List<String> cronExpressionList;
        private Integer startDelay;
        private String[] profiles;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class<? extends BaseJob> getJobClass() {
            return this.jobClass;
        }

        public void setJobClass(Class<? extends BaseJob> jobClass) {
            this.jobClass = jobClass;
        }

        public Boolean getHoldingJobContinuouslyExecute() {
            return this.holdingJobContinuouslyExecute;
        }

        public void setHoldingJobContinuouslyExecute(Boolean holdingJobContinuouslyExecute) {
            this.holdingJobContinuouslyExecute = holdingJobContinuouslyExecute;
        }

        public List<String> getCronExpressionList() {
            return this.cronExpressionList;
        }

        public void setCronExpressionList(List<String> cronExpressionList) {
            this.cronExpressionList = cronExpressionList;
        }

        public Integer getStartDelay() {
            return this.startDelay;
        }

        public void setStartDelay(Integer startDelay) {
            this.startDelay = startDelay;
        }

        public String[] getProfiles() {
            return this.profiles;
        }

        public void setProfiles(String[] profiles) {
            this.profiles = profiles;
        }

    }

}
