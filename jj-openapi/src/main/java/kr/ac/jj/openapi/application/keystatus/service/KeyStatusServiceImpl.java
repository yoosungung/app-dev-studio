package kr.ac.jj.openapi.application.keystatus.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.keystatus.mapper.KeyStatusMapper;
import kr.ac.jj.openapi.application.keystatus.model.KeyStatusModel;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.key.TbApiSvcKeyMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 키 신청 현황 Service
 */
@Service
public class KeyStatusServiceImpl {

    private @Autowired KeyStatusMapper keyStatusMapper;
    private @Autowired TbApiSvcKeyMapper tbApiSvcKeyMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        keyStatusMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch(),
                !SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN"));

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public KeyStatusModel read() {
        KeyStatusModel model = new KeyStatusModel();

        TbApiSvcKey tbApiSvcKey = new TbApiSvcKey();

        LoginUser loginUser = SessionContextUtil.getLoginUser();
        TbComPerson tbComPerson = loginUser.getTbComPerson();

        Date date = new Date();

        tbApiSvcKey.setKeyUsePdBegin(date);
        tbApiSvcKey.setKeyUsePdEnd(DateUtils.addMonths(date, 1));

        model.setTbApiSvcKey(tbApiSvcKey);
        model.setTbComPerson(tbComPerson);

        return model;
    }

    /**
     * 조회
     *
     * @param svckeyId
     * @return
     */
    public KeyStatusModel read(String svckeyId) {
        KeyStatusModel model = new KeyStatusModel();

        TbApiSvcKey tbApiSvcKey = tbApiSvcKeyMapper.select(svckeyId);
        TbComPerson tbComPerson = tbComPersonMapper.select(tbApiSvcKey.getApplcntId());

        model.setTbApiSvcKey(tbApiSvcKey);
        model.setTbComPerson(tbComPerson);

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(KeyStatusModel model) {
        TbApiSvcKey tbApiSvcKey = model.getTbApiSvcKey().newId();

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        tbApiSvcKey.setApplcntId(loginUser.getPersonId());
        tbApiSvcKey.setRqstdt(new Date());
        tbApiSvcKey.setApiKey("JJ" + IdGenerationUtil.createUid("API_KEY"));
        tbApiSvcKey.setSttus("A");
        tbApiSvcKey.setCallCoPday(10000);

        if (tbApiSvcKeyMapper.insert(tbApiSvcKey) == 0) {
            throw new BizException("서비스 키를 생성 할 수 없습니다.");
        }

        return tbApiSvcKey.getSvcKeyId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(KeyStatusModel model) {
        if (!this.read(model.getTbApiSvcKey().getSvcKeyId()).isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        TbApiSvcKey tbApiSvcKey = model.getTbApiSvcKey();

        tbApiSvcKeyMapper.update(tbApiSvcKey);
    }

    /**
     * 삭제
     *
     * @param svckeyId
     */
    public void delete(String svckeyId) {
        if (!this.read(svckeyId).isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
        }

        tbApiSvcKeyMapper.delete(svckeyId);
    }

}
