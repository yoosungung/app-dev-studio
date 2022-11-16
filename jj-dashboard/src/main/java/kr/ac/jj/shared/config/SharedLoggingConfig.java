package kr.ac.jj.shared.config;

import java.io.File;

import org.dizitart.no2.Nitrite;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextProvider;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.AutowiringSpringQuartzBeanJobFactory;
import kr.ac.jj.shared.infrastructure.logging.collection.LoggingCollections;
import kr.ac.jj.shared.infrastructure.logging.filter.LoggingFilter;
import kr.ac.jj.shared.infrastructure.logging.scheduler.LoggingCreatorSchedulerJob;
import kr.ac.jj.shared.infrastructure.logging.util.LoggingUtil;

/**
 * 로깅 Config
 */
@Configuration
public class SharedLoggingConfig {

    @Value(value = "${config.shared.log.database-name:default}")
    private String logDatabaseName;

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<LoggingFilter>();
        registrationBean.setFilter(new LoggingFilter(this.logDatabaseName));
        registrationBean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER - 1);
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    @Bean
    public Nitrite loggingDatabase() {
        File dbFile = LoggingUtil.getDatabaseFile(this.logDatabaseName);
        FileUtil.deleteFile(dbFile);

        return Nitrite.builder() //
                .filePath(dbFile) //
                .compressed() //
                .openOrCreate();
    }

    @Bean
    public LoggingCollections loggingCollections() {
        return new LoggingCollections(loggingDatabase());
    }

    @Bean
    public SchedulerFactory loggingSchedulerFactoryBean() {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        AutowiringSpringQuartzBeanJobFactory jobFactory = new AutowiringSpringQuartzBeanJobFactory();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        jobFactory.setApplicationContext(applicationContext);

        Scheduler scheduler;

        try {
            scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            scheduler.start();
        } catch (SchedulerException e) {
            throw new BaseException(e);
        }

        JobDetail jobDetail = JobBuilder.newJob(LoggingCreatorSchedulerJob.class) //
                .storeDurably() //
                .build();

        Trigger trigger = TriggerBuilder.newTrigger() //
                .forJob(jobDetail) //
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).repeatForever()) //
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new BaseException(e);
        }

        return schedulerFactory;
    }

}
