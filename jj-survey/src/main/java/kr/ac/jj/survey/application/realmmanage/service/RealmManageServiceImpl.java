package kr.ac.jj.survey.application.realmmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.realmmanage.mapper.RealmManageMapper;
import kr.ac.jj.survey.application.realmmanage.model.RealmManageModel;
import kr.ac.jj.survey.application.realmmanage.model.RealmUsedCountModel;
import kr.ac.jj.survey.domain.main.mapper.survey.realm.TbSurveyRealmMapper;
import kr.ac.jj.survey.domain.main.model.survey.realm.TbSurveyRealm;

/**
 * 설문 분야 관리 Service
 */
@Service
public class RealmManageServiceImpl {

    private @Autowired RealmManageMapper realmManageMapper;
    private @Autowired TbSurveyRealmMapper tbSurveyRealmMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        realmManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public RealmManageModel read() {
        RealmManageModel model = new RealmManageModel();

        TbSurveyRealm tbSurveyRealm = new TbSurveyRealm();

        model.setTbSurveyRealm(tbSurveyRealm);

        return model;
    }

    /**
     * 조회
     *
     * @param surveyRealmId
     * @return
     */
    public RealmManageModel read(String surveyRealmId) {
        RealmManageModel model = new RealmManageModel();

        TbSurveyRealm tbSurveyRealm = tbSurveyRealmMapper.select(surveyRealmId);

        model.setTbSurveyRealm(tbSurveyRealm);

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(RealmManageModel model) {
        TbSurveyRealm tbSurveyRealm = model.getTbSurveyRealm().newId();

        if (tbSurveyRealmMapper.insert(tbSurveyRealm) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.sysmanage.codemanage.message.codeGroupCreateNotAvail",
                    "설문 분야 \"{0}\" 를 생성할 수가 없습니다."));
        }

        return tbSurveyRealm.getSurveyRealmId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(RealmManageModel model) {
        TbSurveyRealm tbSurveyRealm = model.getTbSurveyRealm();

        tbSurveyRealmMapper.update(tbSurveyRealm);
    }

    /**
     * 삭제
     *
     * @param surveyRealmId
     */
    public void delete(String surveyRealmId) {
        try {
            tbSurveyRealmMapper.delete(surveyRealmId);
        } catch (RuntimeException e) {
            RealmUsedCountModel usedCount = tbSurveyRealmMapper.selectUsedCo(surveyRealmId);

            if (usedCount.getTmplatUsedCo() == 0 && usedCount.getQestnrUsedCo() == 0) {
                throw e;
            }

            StringBuilder msg = new StringBuilder();
            msg.append("이미 사용중인 설문분야 입니다.\n");

            if (usedCount.getTmplatUsedCo() > 0) {
                msg.append("\n템플릿 : " + usedCount.getTmplatUsedCo() + " 개");
            }

            if (usedCount.getQestnrUsedCo() > 0) {
                msg.append("\n설문지 : " + usedCount.getQestnrUsedCo() + " 개");
            }

            throw new BizException(msg.toString());
        }
    }

}
