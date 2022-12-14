package kr.ac.jj.survey.application.qestnrmanage.service;

import org.apache.ibatis.session.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.com.dept.TbComDeptMapper;
import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.survey.application.qestnrmanage.model.QuestionnairePublishModel;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonToPersonMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPersonToPerson;

/**
 * 설문지 관리 Service
 */
@Service
public class QuestionnaireManageServiceNoTxImpl {

    private @Autowired QuestionnaireManageServiceImpl questionnaireManageService;
    private @Autowired TbSurveyQestnrPersonToPersonMapper tbSurveyQestnrPersonToPersonMapper;
    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;
    private @Autowired TbComDeptMapper tbComDeptMapper;

    /**
     * 설문 미응답자 사람 목록 조회
     *
     * @param surveyQestnrId
     * @param publishModel
     */
    public void readSurveySendTargetUnAnswered(String surveyQestnrId, QuestionnairePublishModel publishModel) {
        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.select(surveyQestnrId);
        TbComPerson registPerson = tbComPersonMapper.select(tbSurveyQestnr.getRegistPsnId());
        TbComDept registPersonDept = tbComDeptMapper.select(registPerson.getDeptId());

        tbSurveyQestnrPersonToPersonMapper.selectSurveySendTargetUnAnswered(surveyQestnrId,
                new DataResultHandler<TbSurveyQestnrPersonToPerson>() {

                    @Override
                    public void handleResult(ResultContext<? extends TbSurveyQestnrPersonToPerson> resultContext) {
                        questionnaireManageService.createSurveySend(tbSurveyQestnr, resultContext.getResultObject(),
                                registPerson, registPersonDept, publishModel);
                    }

                });
    }

}
