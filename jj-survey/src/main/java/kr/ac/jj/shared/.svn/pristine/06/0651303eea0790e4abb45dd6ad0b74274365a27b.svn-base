package kr.ac.jj.shared.application.common.email.scheduler;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.application.common.email.service.EmailServiceImpl;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.BaseJob;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.JobDetailTrigger;
import kr.ac.jj.shared.infrastructure.framework.core.support.scheduling.model.SchedulerJobConfig;

/**
 * 이메일 발송 SchedulerJob
 */
@Component
@DisallowConcurrentExecution
public class EmailSendSchedulerJob extends BaseJob {

    private static final Logger log = LoggerFactory.getLogger(EmailSendSchedulerJob.class);

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired EmailServiceImpl emailService;

    @Override
    public void executeJob(SchedulerJobConfig jobConfig, JobDetailTrigger jobDetailTrigger,
            JobExecutionContext context) {

        this.sendEmail(1);
    }

    private void sendEmail(int sendCount) {
        emailService.updateSndngSttusWorking();

        List<TbComEmailSndng> tbComEmailSndngList = emailService.readSendList();

        if (tbComEmailSndngList.size() == 0) {
            return;
        }

        for (TbComEmailSndng tbComEmailSndng : tbComEmailSndngList) {
            try {
                tbComEmailSndng.setSndngDt(new Date());

                emailService.send(tbComEmailSndng);

                tbComEmailSndng.setSndngSttus(TbComEmailSndng.SndngSttus.SUCCESS);
                tbComEmailSndng.setSndngResult("OK");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);

                tbComEmailSndng.setSndngSttus(TbComEmailSndng.SndngSttus.FAILURE);
                tbComEmailSndng.setSndngResult(ExceptionUtils.getStackTrace(e));
            } finally {
                emailService.updateSendResult(tbComEmailSndng);

                if (tbComEmailSndng.getSndngSttus() == TbComEmailSndng.SndngSttus.FAILURE) {
                    emailService.createSend(tbComEmailSndng.getEmailId(), true);
                }
            }
        }

        if (sendCount < sharedConfig.getEmail().getOnetimeSendCount()) {
            this.sendEmail(sendCount + 1);
        }
    }

}
