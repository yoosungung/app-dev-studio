package kr.ac.jj.openapi.application.keymanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.keymanage.mapper.KeyManageMapper;
import kr.ac.jj.openapi.application.keymanage.model.KeyManageModel;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.key.TbApiSvcKeyMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.shared.application.common.email.model.FreeMarkerTemplateEmail;
import kr.ac.jj.shared.application.common.email.service.EmailServiceImpl;
import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 키 발급 관리 Service
 */
@Service
public class KeyManageServiceImpl {

    private @Autowired KeyManageMapper keyManageMapper;
    private @Autowired TbApiSvcKeyMapper tbApiSvcKeyMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;
    private @Autowired EmailServiceImpl emailService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        keyManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param svckeyId
     * @return
     */
    public KeyManageModel read(String svckeyId) {
        KeyManageModel model = new KeyManageModel();

        TbApiSvcKey tbApiSvcKey = tbApiSvcKeyMapper.select(svckeyId);
        TbComPerson applcntTbComPerson = tbComPersonMapper.select(tbApiSvcKey.getApplcntId());

        LoginUser loginUser = SessionContextUtil.getLoginUser();
        TbComPerson tbComPerson = new TbComPerson();

        if ("".equals(tbApiSvcKey.getExmntPsnId()) || tbApiSvcKey.getExmntPsnId() == null) {
            tbComPerson = loginUser.getTbComPerson();
        } else {
            tbComPerson = tbComPersonMapper.select(tbApiSvcKey.getExmntPsnId());
        }

        if (tbApiSvcKey.getCallCoPday() == null) {
            tbApiSvcKey.setCallCoPday(10000);
        }

        model.setTbApiSvcKey(tbApiSvcKey);
        model.setApplcntTbComPerson(applcntTbComPerson);
        model.setTbComPerson(tbComPerson);

        return model;
    }

    /**
     * 검토(승인/반려)
     *
     * @param model
     */
    public void update(KeyManageModel model) {
        if (!this.read(model.getTbApiSvcKey().getSvcKeyId()).isEditable()) {
            throw new BizException("검토 할 수 없는 데이터입니다.");
        }

        TbApiSvcKey tbApiSvcKey = model.getTbApiSvcKey();

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        tbApiSvcKey.setExmntPsnId(loginUser.getPersonId());
        tbApiSvcKey.setExmntDt(new Date());

        tbApiSvcKeyMapper.update(tbApiSvcKey);
        sendMail(tbApiSvcKey);
    }

    /**
     * 메일 발송
     *
     * @param tbApiSvcKey
     */
    public void sendMail(TbApiSvcKey tbApiSvcKey) {
        tbApiSvcKey = tbApiSvcKeyMapper.select(tbApiSvcKey.getSvcKeyId());

        TbComPerson applcntTbComPerson = tbComPersonMapper.select(tbApiSvcKey.getApplcntId());

        String keySttus = tbApiSvcKey.getSttus();

        String beginDate = DateFormatUtils.format(tbApiSvcKey.getKeyUsePdBegin(), "yyyy.MM.dd");
        String endDate = DateFormatUtils.format(tbApiSvcKey.getKeyUsePdEnd(), "yyyy.MM.dd");

        // 이메일 발송 - 이메일 주소가 있는 경우
        if (StringUtils.isNotEmpty(applcntTbComPerson.getEmailAdres())) {
            StringBuilder message = new StringBuilder();

            message.append("제목: [전주대학교 Open API Center]\n");
            message.append("\n");
            message.append("안녕하세요.\n");
            message.append("\n");
            message.append("전주대학교 Open API Center입니다.\n");
            message.append("\n");
            message.append("신청하신 키 [" + tbApiSvcKey.getApiKey() + "] 에 대하여 아래의 검토결과에 의해 " + tbApiSvcKey.getSttusNm() + "되었습니다.\n");
            message.append("\n");

            if (StringUtils.equals(keySttus, "C")) {
                message.append("사용 기간 : " +  beginDate + " ~ " + endDate);
                message.append("\n\n");
                message.append("호출 횟수(일) : " + tbApiSvcKey.getCallCoPday() + " 건");
                message.append("\n\n");
            }

            message.append("----- 검토 결과 -----\n\n");
            message.append(tbApiSvcKey.getExmntResult());

            FreeMarkerTemplateEmail email = new FreeMarkerTemplateEmail();
            email.setEmailSj("[전주대학교 Open Api Center 키 요청 "+ tbApiSvcKey.getSttusNm() + "]");
            email.setSender("oepnapi@jj.ac.kr", "전주대학교");
            email.addRecptn(RecipientType.TO, applcntTbComPerson.getEmailAdres(),
                    applcntTbComPerson.getKoreanNm(), applcntTbComPerson.getPersonId());
            email.setTemplateData("message", message);
            email.processTemplate("/keymanage/KeyManageEmail.html");
            email.getTbComEmail().setRelateId(tbApiSvcKey.getSvcKeyId());

            emailService.create(email);
        }

    }

    /**
     * 삭제
     *
     * @param model
     */
    public void delete(String svcKeyId) {
        tbApiSvcKeyMapper.delete(svcKeyId);
    }

}
