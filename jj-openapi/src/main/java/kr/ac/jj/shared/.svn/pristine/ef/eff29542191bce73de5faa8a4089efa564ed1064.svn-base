package kr.ac.jj.shared.application.common.email.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.email.model.BaseEmail;
import kr.ac.jj.shared.application.common.email.sender.EmailSender;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.mapper.com.email.TbComEmailMapper;
import kr.ac.jj.shared.domain.main.mapper.com.email.atchfile.TbComEmailAtchFileMapper;
import kr.ac.jj.shared.domain.main.mapper.com.email.recptn.TbComEmailRecptnMapper;
import kr.ac.jj.shared.domain.main.mapper.com.email.sndng.TbComEmailSndngMapper;
import kr.ac.jj.shared.domain.main.model.com.email.TbComEmail;
import kr.ac.jj.shared.domain.main.model.com.email.atchfile.TbComEmailAtchFile;
import kr.ac.jj.shared.domain.main.model.com.email.recptn.TbComEmailRecptn;
import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.SysException;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

@Service
public class EmailServiceImpl {

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired TbComEmailMapper tbComEmailMapper;
    private @Autowired TbComEmailRecptnMapper tbComEmailRecptnMapper;
    private @Autowired TbComEmailAtchFileMapper tbComEmailAtchFileMapper;
    private @Autowired TbComEmailSndngMapper tbComEmailSndngMapper;

    /**
     * 생성
     *
     * @param email
     * @return
     */
    public String create(BaseEmail email) {
        TbComEmail tbComEmail = email.getTbComEmail();
        tbComEmail.newId();
        tbComEmailMapper.insert(tbComEmail);

        String emailId = tbComEmail.getEmailId();

        if (email.getTbComEmailRecptnList() != null) {
            for (TbComEmailRecptn tbComEmailRecptn : email.getTbComEmailRecptnList()) {
                tbComEmailRecptn.newId();
                tbComEmailRecptn.setEmailId(emailId);
                tbComEmailRecptnMapper.insert(tbComEmailRecptn);
            }
        }

        if (email.getTbComEmailAtchFileList() != null) {
            for (TbComEmailAtchFile tbComEmailAtchFile : email.getTbComEmailAtchFileList()) {
                tbComEmailAtchFile.newId();
                tbComEmailAtchFile.setEmailId(emailId);
                tbComEmailAtchFileMapper.insert(tbComEmailAtchFile);
            }
        }

        TbComEmailSndng tbComEmailSndng = email.getTbComEmailSndng();

        if (tbComEmailSndng == null) {
            this.createSend(emailId);
        } else {
            tbComEmailSndng.newId();
            tbComEmailSndng.setEmailId(emailId);
            tbComEmailSndngMapper.insert(tbComEmailSndng);
        }

        return emailId;
    }

    /**
     * 발송 생성
     *
     * @param emailId
     */
    public void createSend(String emailId) {
        this.createSend(emailId, false);
    }

    /**
     * 발송 생성
     *
     * @param emailId
     * @param byScheduler
     */
    public void createSend(String emailId, boolean byScheduler) {
        TbComEmailSndng tbComEmailSndng = new TbComEmailSndng();
        tbComEmailSndng.newId();
        tbComEmailSndng.setEmailId(emailId);
        tbComEmailSndng.setTestEmailAdres(sharedConfig.getEmail().getTestEmailAddress());

        if (byScheduler) {
            tbComEmailSndngMapper.insertByScheduler(tbComEmailSndng);
        } else {
            tbComEmailSndngMapper.insert(tbComEmailSndng);
        }
    }

    /**
     * 발송중으로 수정
     *
     * @return
     */
    public int updateSndngSttusWorking() {
        return tbComEmailSndngMapper.updateSndngSttusWorking(new TbComEmailSndng());
    }

    /**
     * 발송대상 목록 조회
     *
     * @return
     */
    public List<TbComEmailSndng> readSendList() {
        return tbComEmailSndngMapper.selectSndngSttusWorkingList();
    }

    /**
     * 발송
     *
     * @param tbComEmailSndng
     */
    public void send(TbComEmailSndng tbComEmailSndng) {
        BaseEmail email = new BaseEmail();

        String emailId = tbComEmailSndng.getEmailId();

        email.setTbComEmail(tbComEmailMapper.select(emailId));

        if (StringUtils.isEmpty(tbComEmailSndng.getTestEmailAdres())) {
            email.setTbComEmailRecptnList(tbComEmailRecptnMapper.selectListByEmailId(emailId));

            if (email.getTbComEmailRecptnList() == null || email.getTbComEmailRecptnList().size() == 0) {
                throw new SysException("수신자 없음");
            }
        } else {
            InternetAddress[] addresses;

            try {
                addresses = InternetAddress.parse(tbComEmailSndng.getTestEmailAdres());
            } catch (AddressException e) {
                throw new BaseException(e);
            }

            List<TbComEmailRecptn> tbComEmailRecptnList = new ArrayList<TbComEmailRecptn>();

            for (InternetAddress address : addresses) {
                TbComEmailRecptn tbComEmailRecptn = new TbComEmailRecptn();
                tbComEmailRecptn.setRecptnTyEnum(RecipientType.TO);
                tbComEmailRecptn.setRcverEmailAdres(address.getAddress());
                tbComEmailRecptn.setRcverNm(address.getPersonal());

                tbComEmailRecptnList.add(tbComEmailRecptn);
            }

            email.setTbComEmailRecptnList(tbComEmailRecptnList);
        }

        email.setTbComEmailAtchFileList(tbComEmailAtchFileMapper.selectListByEmailId(emailId));

        this.send(email);
    }

    /**
     * 발송결과 저장
     *
     * @param tbComEmailSndng
     */
    public void updateSendResult(TbComEmailSndng tbComEmailSndng) {
        tbComEmailSndngMapper.updateSendResult(tbComEmailSndng);
    }

    /**
     * 발송
     *
     * @param email
     */
    public void send(BaseEmail email) {
        EmailSender emailSender = ApplicationContextUtil
                .getBean("emailSender." + sharedConfig.getEmail().getSenderName());

        emailSender.send(email);
    }

}
