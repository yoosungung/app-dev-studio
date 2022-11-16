package kr.ac.jj.survey.application.common.sms;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.application.common.sms.model.BaseSms;
import kr.ac.jj.shared.application.common.sms.sender.SmsSender;
import kr.ac.jj.shared.domain.main.mapper.com.sms.TbComSmsMapper;
import kr.ac.jj.shared.domain.main.model.com.sms.TbComSms;
import kr.ac.jj.survey.domain.umslink.mapper.em.log.EmLogMapper;
import kr.ac.jj.survey.domain.umslink.mapper.em.tran.EmTranMapper;
import kr.ac.jj.survey.domain.umslink.mapper.members.MembersMapper;
import kr.ac.jj.survey.domain.umslink.model.em.log.EmLog;
import kr.ac.jj.survey.domain.umslink.model.em.tran.EmTran;

@Component(value = "smsSender.UmsSmsSender")
public class UmsSmsSender implements SmsSender {

    private static final Logger log = LoggerFactory.getLogger(UmsSmsSender.class);

    private @Autowired TbComSmsMapper tbComSmsMapper;
    private @Autowired MembersMapper membersMapper;
    private @Autowired EmTranMapper emTranMapper;
    private @Autowired EmLogMapper emLogMapper;

    @Override
    public void updateResult(BaseSms sms) {
        TbComSms tbComSms = sms.getTbComSms();

        EmLog emLog = null;

        try {
            emLog = emLogMapper.selectByTranEtc2(DateFormatUtils.format(tbComSms.getTranDate(), "yyyyMM"),
                    tbComSms.getTranEtc2());

            if (emLog == null) {
                emLog = emLogMapper.selectByTranEtc2(
                        DateFormatUtils.format(DateUtils.addDays(tbComSms.getTranDate(), -1), "yyyyMM"),
                        tbComSms.getTranEtc2());
            }

            if (emLog == null) {
                emLog = emLogMapper.selectByTranEtc2(
                        DateFormatUtils.format(DateUtils.addDays(tbComSms.getTranDate(), 1), "yyyyMM"),
                        tbComSms.getTranEtc2());
            }
        } catch (Exception e) {
            log.error("SMS 로그 조회 에러", e);
            return;
        }

        if (emLog == null) {
            return;
        }

        tbComSms.setTranRefkey(emLog.getTranRefkey());
        tbComSms.setTranId(emLog.getTranId());
        tbComSms.setTranStatus(emLog.getTranStatus());
        tbComSms.setTranDate(emLog.getTranDate());
        tbComSms.setTranRsltdate(emLog.getTranRsltdate());
        tbComSms.setTranReportdate(emLog.getTranReportdate());
        tbComSms.setTranRslt(emLog.getTranRslt());
        tbComSms.setTranNet(emLog.getTranNet());
        tbComSmsMapper.updateResult(tbComSms);
    }

    @Override
    public void send(BaseSms sms) {
        /*
         * TRAN_STATUS : SMS상태에 따라 변경
         * 1 : 발송 요청
         * 2 : SMS G/W Server에 전송. 응답 대기 중
         * 3 : SMS G/W Server로부터 결과 수신
         */
        /*
         * TRAN_RSLT : SMS G/W Server로부터 수신된 결과
         * 0 : 성공
         * 1 : TIMEOUT
         * A : 핸드폰 번호 처리중
         * B : 음영지역
         * C : POWER OFF
         * D : 메시지 저장 개수 초과
         * 2 : 잘못된 전화 번호
         * a : 일시 서비스 정지
         * b : 기타 단말기 문제
         * c : 착신 거절
         * d : 기타
         * e : 이통사 SMC 형식 오류
         * f  : IB 자체 형식 오류
         * g : SMS 서비스 불가 단말기
         * h : 핸드폰 호 불가 상태
         * I  : SMC 운영자가 메시지 삭제
         * j  : 이통사 내부 메시지 Que Full
         */

        TbComSms tbComSms = sms.getTbComSms();

        if (StringUtils.isAnyEmpty(tbComSms.getTranRefkey(), tbComSms.getTranId())) {
            if (StringUtils.isEmpty(tbComSms.getDeptCode())) {
                log.warn("부서 코드가 없습니다.");
                return;
            }

            String umsGroupId = membersMapper.selectUmsGroupId(tbComSms.getDeptCode());

            if (StringUtils.isEmpty(umsGroupId)) {
                log.warn("부서 코드 \"{}\"의 UMS_GROUP_ID가 없습니다.", tbComSms.getDeptCode());
                return;
            }

            tbComSms.setTranRefkey(umsGroupId); // 각사용자ID -고정으로 입력 해야 함
            tbComSms.setTranId(umsGroupId); // 각사용자ID -고정으로 입력 해야 함
        }

        EmTran emTran = new EmTran();
        emTran.setTranPr(null); // PK. 자동증가
        emTran.setTranRefkey(tbComSms.getTranRefkey()); // 각사용자ID -고정으로 입력 해야 함
        emTran.setTranId(tbComSms.getTranId()); // 각사용자ID -고정으로 입력 해야 함
        emTran.setTranPhone(tbComSms.getTranPhone()); // 수신자 전화번호
        emTran.setTranCallback(tbComSms.getTranCallback()); // 송신자 전화번호
        emTran.setTranStatus("1"); // 메시지 상태(1) : 1로 고정
        emTran.setTranDate(new Date()); // 메시지 전송시간(YYYY-mm-dd HH:ii:ss)
        emTran.setTranRsltdate(null); // 핸드폰에 전달된 시간(YYYY-mm-dd HH:ii:ss)
        emTran.setTranReportdate(null); // 결과 수신 시간(YYYY-mm-dd HH:ii:ss)
        emTran.setTranRslt(null); // 전송 결과 (코드표 참조)
        emTran.setTranNet(tbComSms.getTranNet());
        emTran.setTranMsg(tbComSms.getTranMsg()); // 전송 메시지
        emTran.setTranEtc1(tbComSms.getTranEtc1()); // 메시지 제목
        emTran.setTranEtc2(tbComSms.getTranEtc2()); // 타 시스템 유일키(구분키)
        emTran.setTranEtc3(tbComSms.getTranEtc3()); // 수신자명
        emTran.setTranEtc4(tbComSms.getTranEtc4());
        emTran.setTranType(tbComSms.getTranType()); // 0: SMS (디폴트), 6: LMS
        emTranMapper.insert(emTran);

        tbComSmsMapper.updateTranPr(tbComSms.getSmsId(), emTran.getTranPr());
    }

}
