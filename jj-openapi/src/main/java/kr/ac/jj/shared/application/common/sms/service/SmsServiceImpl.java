package kr.ac.jj.shared.application.common.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.sms.model.BaseSms;
import kr.ac.jj.shared.application.common.sms.sender.SmsSender;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.mapper.com.sms.TbComSmsMapper;
import kr.ac.jj.shared.domain.main.model.com.sms.TbComSms;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

/**
 * 문자메시지 Service
 */
@Service
public class SmsServiceImpl {

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired TbComSmsMapper tbComSmsMapper;

    /**
     * 생성
     *
     * @param sms
     * @return
     */
    public String create(BaseSms sms) {
        TbComSms tbComSms = sms.getTbComSms();
        tbComSms.newId();
        tbComSmsMapper.insert(tbComSms);

        String smsId = tbComSms.getSmsId();

        return smsId;
    }

    /**
     * 결과 업데이트
     *
     * @param tbComSms
     */
    public void updateResult(BaseSms sms) {
        SmsSender smsSender = ApplicationContextUtil.getBean("smsSender." + sharedConfig.getSms().getSenderName());

        smsSender.updateResult(sms);
    }

    /**
     * 발송
     *
     * @param tbComSms
     */
    public void send(BaseSms sms) {
        SmsSender smsSender = ApplicationContextUtil.getBean("smsSender." + sharedConfig.getSms().getSenderName());

        smsSender.send(sms);
    }

}
