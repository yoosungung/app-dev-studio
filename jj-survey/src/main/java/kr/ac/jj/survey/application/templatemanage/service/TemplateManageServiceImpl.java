package kr.ac.jj.survey.application.templatemanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.templatemanage.mapper.TemplateManageMapper;
import kr.ac.jj.survey.application.templatemanage.model.TemplateManageModel;
import kr.ac.jj.survey.config.props.SurveyConfigProperties;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.JdSurveyDefinitionMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.tmplat.TbSurveyTmplatMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.tmplat.TbSurveyTmplat;
import kr.ac.jj.survey.domain.main.service.jd.survey.definition.JdSurveyDefinitionServiceImpl;

/**
 * 템플릿 관리 Service
 */
@Service
public class TemplateManageServiceImpl {

    private @Autowired SurveyConfigProperties surveyConfig;
    private @Autowired TemplateManageMapper templateManageMapper;
    private @Autowired TbSurveyTmplatMapper tbSurveyTmplatMapper;
    private @Autowired JdSurveyDefinitionMapper jdSurveyDefinitionMapper;
    private @Autowired JdSurveyDefinitionServiceImpl jdSurveyDefinitionService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        templateManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public TemplateManageModel read() {
        TemplateManageModel model = new TemplateManageModel();

        TbSurveyTmplat tbSurveyTmplat = new TbSurveyTmplat();
        JdSurveyDefinition jdSurveyDefinition = new JdSurveyDefinition();

        tbSurveyTmplat.setTmplatSttus("0");

        model.setTbSurveyTmplat(tbSurveyTmplat);
        model.setJdSurveyDefinition(jdSurveyDefinition);
        model.setBackgroundColors(surveyConfig.getBackgroundColors());

        return model;
    }

    /**
     * 조회
     *
     * @param surveyTmplatId
     * @return
     */
    public TemplateManageModel read(String surveyTmplatId) {
        TemplateManageModel model = new TemplateManageModel();

        TbSurveyTmplat tbSurveyTmplat = tbSurveyTmplatMapper.select(surveyTmplatId);

        model.setTbSurveyTmplat(tbSurveyTmplat);
        model.setJdSurveyDefinition(jdSurveyDefinitionMapper.select(tbSurveyTmplat.getSurveyDefinitionId()));
        model.setBackgroundColors(surveyConfig.getBackgroundColors());

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(TemplateManageModel model) {
        TbSurveyTmplat tbSurveyTmplat = model.getTbSurveyTmplat().newId();
        LoginUser loginUser = SessionContextUtil.getLoginUser();

        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();
        jdSurveyDefinition.setIsPublic(false);
        jdSurveyDefinition.setAllowMultipleSubmissions(false);
        jdSurveyDefinition.setAutoRemindersDayOfMonth(1);
        jdSurveyDefinition.setRemindersFrequency("WEEKLY");
        jdSurveyDefinition.setAutoRemindersMonthlyOccur(1);
        jdSurveyDefinition.setAutoRemindersWeeklyOccur(1);
        jdSurveyDefinition.setCompletedSurveyTemplate("completedSurveyTemplate");
        jdSurveyDefinition.setEmailInvitationTemplate("emailInvitationTemplate");
        jdSurveyDefinition.setSendAutoReminders(0);
        jdSurveyDefinition.setStatus("I");
        jdSurveyDefinition.setSurveyTheme("STANDARD");
        jdSurveyDefinition.setRegistDt(new Date());
        jdSurveyDefinition.setDepartmentId(1L);
        jdSurveyDefinition.setVersion(0);
        jdSurveyDefinitionMapper.insert(jdSurveyDefinition);

        tbSurveyTmplat.setSurveyDefinitionId(jdSurveyDefinition.getId());
        tbSurveyTmplat.setTmplatSttus("0");
        tbSurveyTmplat.setRegistPsnId(loginUser.getPersonId());
        tbSurveyTmplat.setRegistDt(new Date());
        tbSurveyTmplatMapper.insert(tbSurveyTmplat);

        return tbSurveyTmplat.getSurveyTmplatId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(TemplateManageModel model) {
        TbSurveyTmplat tbSurveyTmplat = model.getTbSurveyTmplat();
        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();

        jdSurveyDefinition.setStatus("I");

        tbSurveyTmplatMapper.update(tbSurveyTmplat);
        jdSurveyDefinitionMapper.update(jdSurveyDefinition);
    }

    /**
     * 수정 - 상태값
     *
     * @param surveyTmplatId
     * @param tmplatSttus
     */
    public void updateStatus(String surveyTmplatId, String tmplatSttus) {
        TbSurveyTmplat tbSurveyTmplat = tbSurveyTmplatMapper.select(surveyTmplatId);

        tbSurveyTmplatMapper.updateTmplatSttus(surveyTmplatId, tmplatSttus);

        jdSurveyDefinitionMapper.updateStatus(tbSurveyTmplat.getSurveyDefinitionId(),
                StringUtils.equals(tmplatSttus, "1") ? "T" : "I");
    }

    /**
     * 삭제
     *
     * @param surveyTmplatId
     */
    public void delete(String surveyTmplatId) {
        TbSurveyTmplat tbSurveyTmplatDb = tbSurveyTmplatMapper.select(surveyTmplatId);

        tbSurveyTmplatMapper.delete(surveyTmplatId);
        jdSurveyDefinitionService.delete(tbSurveyTmplatDb.getSurveyDefinitionId());
    }

}
