package kr.ac.jj.shared.application.common.sms.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.com.sms.TbComSmsMapper;
import kr.ac.jj.shared.domain.main.model.com.sms.TbComSms;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;

/**
 * 문자메시지 Service
 */
@Service
public class SmsServiceNoTxImpl {

    private @Autowired TbComSmsMapper tbComSmsMapper;

    /**
     * 결과 업데이트 대상 목록 조회
     *
     * @param resultHandler
     */
    public void readForResultUpdateList(DataResultHandler<TbComSms> resultHandler) {
        Date limitDateTime = DateUtils.addDays(new Date(), -5);

        tbComSmsMapper.selectForResultUpdateList(limitDateTime, resultHandler);
    }

    /**
     * 발송 대상 목록 조회
     *
     * @param resultHandler
     */
    public void readForSendList(DataResultHandler<TbComSms> resultHandler) {
        tbComSmsMapper.selectForSendList(resultHandler);
    }

}
