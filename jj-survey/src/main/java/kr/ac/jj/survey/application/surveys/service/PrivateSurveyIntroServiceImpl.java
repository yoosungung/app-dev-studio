package kr.ac.jj.survey.application.surveys.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.survey.application.surveys.model.PrivateSurveyIntroModel;
import kr.ac.jj.survey.domain.main.mapper.survey.cmmnrspns.TbSurveyCmmnRspnsMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.cmmn.TbSurveyQestnrCmmnMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonMapper;
import kr.ac.jj.survey.domain.main.model.survey.cmmnrspns.TbSurveyCmmnRspns;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

/**
 * 비공개 설문 시작 Service
 */
@Service
public class PrivateSurveyIntroServiceImpl {

    private @Autowired TbSurveyCmmnRspnsMapper tbSurveyCmmnRspnsMapper;
    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired TbSurveyQestnrCmmnMapper tbSurveyQestnrCmmnMapper;
    private @Autowired TbSurveyQestnrPersonMapper tbSurveyQestnrPersonMapper;

    /**
     * 조회
     *
     * @param surveyId
     * @return
     */
    public PrivateSurveyIntroModel read(Long surveyId) {
        PrivateSurveyIntroModel model = new PrivateSurveyIntroModel();

        String surveyQestnrId = tbSurveyQestnrMapper.selectSurveyQestnrIdBySurveyId(surveyId);

        // 설문지 기본 정보 조회
        model.setTbSurveyQestnr(tbSurveyQestnrMapper.select(surveyQestnrId));

        model.setTbSurveyQestnrCmmn(tbSurveyQestnrCmmnMapper.select(surveyQestnrId));

        String cmmnRspnsId = tbSurveyCmmnRspnsMapper.selectCmmnRspnsIdBySurveyId(surveyId);

        if (StringUtils.isEmpty(cmmnRspnsId)) {
            TbSurveyCmmnRspns tbSurveyCmmnRspns = tbSurveyCmmnRspnsMapper.selectCmmnRspnsByMaxRspnsOdr();

            if (tbSurveyCmmnRspns == null) {
                tbSurveyCmmnRspns = new TbSurveyCmmnRspns();
                tbSurveyCmmnRspns.setSurveyId(surveyId);
            }

            model.setTbSurveyCmmnRspns(tbSurveyCmmnRspns);
        } else {
            model.setTbSurveyCmmnRspns(tbSurveyCmmnRspnsMapper.select(cmmnRspnsId));
        }

        return model;
    }

    /**
     * 저장
     *
     * @param surveyId
     * @param model
     */
    public void update(Long surveyId, PrivateSurveyIntroModel model) {
        LoginUser loginUser = SessionContextUtil.getLoginUser();
        TbSurveyCmmnRspns tbSurveyCmmnRspns = model.getTbSurveyCmmnRspns();

        tbSurveyCmmnRspns.setSurveyId(surveyId);
        tbSurveyCmmnRspns.setPersonId(loginUser.getPersonId());

        if (tbSurveyCmmnRspnsMapper.update(tbSurveyCmmnRspns) == 0) {
            tbSurveyCmmnRspns.newId();
            tbSurveyCmmnRspnsMapper.insert(tbSurveyCmmnRspns);
        }

        TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper.selectBySurveyId(surveyId);

        if (tbSurveyQestnrPerson != null && tbSurveyQestnrPerson.getStrePgeNo() < 0) {
            tbSurveyQestnrPersonMapper.updateStrePgeNo(tbSurveyQestnrPerson.getSurveyPersonId(), (short) 0);
        }
    }

}
