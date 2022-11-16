package kr.ac.jj.shared.application.admin.logmanage.smslog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.logmanage.smslog.mapper.SmsLogManageMapper;
import kr.ac.jj.shared.application.admin.logmanage.smslog.model.SmsLogManageModel;
import kr.ac.jj.shared.application.common.sms.model.BaseSms;
import kr.ac.jj.shared.application.common.sms.service.SmsServiceImpl;
import kr.ac.jj.shared.domain.main.mapper.com.sms.TbComSmsMapper;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 문자메시지 로그 관리 Service
 */
@Service
public class SmsLogManageServiceImpl {

    private @Autowired SmsLogManageMapper smsLogManageMapper;
    private @Autowired TbComSmsMapper tbComSmsMapper;
    private @Autowired SmsServiceImpl smsService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        smsLogManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param smsId
     * @return
     */
    public SmsLogManageModel read(String smsId) {
        SmsLogManageModel model = new SmsLogManageModel();

        model.setTbComSms(tbComSmsMapper.select(smsId));

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(SmsLogManageModel model) {
        BaseSms sms = new BaseSms();
        sms.initTbComSms(model.getTbComSms());
        sms.setDeptCode("50548200"); // TODO 실제 부서코드로는 매칭되는 데이터가 없음

        String smsId = smsService.create(sms);

        return smsId;
    }

    /**
     * 발송
     *
     * @param model
     */
    public void send(SmsLogManageModel model) {
        BaseSms sms = new BaseSms();
        sms.initTbComSms(model.getTbComSms());
        sms.setDeptCode("50548200"); // TODO 실제 부서코드로는 매칭되는 데이터가 없음

        smsService.send(sms);
    }

    /**
     * 재발송 처리
     *
     * @param model
     */
    public void updateReSend(String smsId) {
        tbComSmsMapper.updateReSend(smsId);
    }

    /**
     * 삭제
     *
     * @param smsId
     */
    public void delete(String smsId) {
        tbComSmsMapper.delete(smsId);
    }

}
