package kr.ac.jj.survey.application.qestnrmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.email.model.FreeMarkerTemplateEmail;
import kr.ac.jj.shared.application.common.email.service.EmailServiceImpl;
import kr.ac.jj.shared.application.common.sms.model.BaseSms;
import kr.ac.jj.shared.application.common.sms.service.SmsServiceImpl;
import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.mapper.com.dept.TbComDeptMapper;
import kr.ac.jj.shared.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.qestnrmanage.mapper.QuestionnaireManageMapper;
import kr.ac.jj.survey.application.qestnrmanage.model.QuestionnaireManageModel;
import kr.ac.jj.survey.application.qestnrmanage.model.QuestionnairePublishModel;
import kr.ac.jj.survey.application.templatemanage.model.TemplateManageModel;
import kr.ac.jj.survey.application.templatemanage.service.TemplateManageServiceImpl;
import kr.ac.jj.survey.config.props.SurveyConfigProperties;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.JdSurveyMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.definition.JdSurveyDefinitionMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.document.JdSurveyDocumentMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.cmmnrspns.TbSurveyCmmnRspnsMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.cmmn.TbSurveyQestnrCmmnMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonToPersonMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn.TbSurveyQestnrCmmn;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPersonToPerson;
import kr.ac.jj.survey.domain.main.model.survey.tmplat.TbSurveyTmplat;
import kr.ac.jj.survey.domain.main.service.jd.survey.definition.JdSurveyDefinitionServiceImpl;

/**
 * ????????? ?????? Service
 */
@Service
public class QuestionnaireManageServiceImpl {

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired SurveyConfigProperties surveyConfig;
    private @Autowired QuestionnaireManageMapper questionnaireManageMapper;
    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired TbSurveyQestnrCmmnMapper tbSurveyQestnrCmmnMapper;
    private @Autowired JdSurveyDefinitionMapper jdSurveyDefinitionMapper;
    private @Autowired JdSurveyDefinitionServiceImpl jdSurveyDefinitionService;
    private @Autowired TemplateManageServiceImpl templateManageService;
    private @Autowired TbSurveyQestnrPersonMapper tbSurveyQestnrPersonMapper;
    private @Autowired TbSurveyQestnrPersonToPersonMapper tbSurveyQestnrPersonToPersonMapper;
    private @Autowired TbSurveyCmmnRspnsMapper tbSurveyCmmnRspnsMapper;
    private @Autowired JdSurveyDocumentMapper jdSurveyDocumentMapper;
    private @Autowired JdSurveyMapper jdSurveyMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;
    private @Autowired TbComDeptMapper tbComDeptMapper;
    private @Autowired EmailServiceImpl emailService;
    private @Autowired SmsServiceImpl smsService;

    /**
     * ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        questionnaireManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ?????? - ?????????
     *
     * @return
     */
    public QuestionnaireManageModel read() {
        QuestionnaireManageModel model = new QuestionnaireManageModel();

        TbSurveyQestnr tbSurveyQestnr = new TbSurveyQestnr();
        TbSurveyQestnrCmmn tbSurveyQestnrCmmn = new TbSurveyQestnrCmmn();
        JdSurveyDefinition jdSurveyDefinition = new JdSurveyDefinition();

        model.setTbSurveyQestnr(tbSurveyQestnr);
        model.setTbSurveyQestnrCmmn(tbSurveyQestnrCmmn);
        model.setJdSurveyDefinition(jdSurveyDefinition);
        model.setBackgroundColors(surveyConfig.getBackgroundColors());

        return model;
    }

    /**
     * ??????
     *
     * @param surveyQestnrId
     * @return
     */
    public QuestionnaireManageModel read(String surveyQestnrId) {
        QuestionnaireManageModel model = new QuestionnaireManageModel();

        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.select(surveyQestnrId);

        model.setTbSurveyQestnr(tbSurveyQestnr);
        model.setTbSurveyQestnrCmmn(tbSurveyQestnrCmmnMapper.select(surveyQestnrId));
        model.setJdSurveyDefinition(jdSurveyDefinitionMapper.select(tbSurveyQestnr.getSurveyDefinitionId()));
        model.setBackgroundColors(surveyConfig.getBackgroundColors());

        return model;
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    public String create(QuestionnaireManageModel model) {
        TbSurveyQestnr tbSurveyQestnr = model.getTbSurveyQestnr().newId();
        LoginUser loginUser = SessionContextUtil.getLoginUser();

        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();
        jdSurveyDefinition.setId(null);
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

        tbSurveyQestnr.setSurveyDefinitionId(jdSurveyDefinition.getId());
        tbSurveyQestnr.setRegistPsnId(loginUser.getPersonId());
        tbSurveyQestnr.setRegistDt(new Date());
        tbSurveyQestnr.setSndngYn(false);
        tbSurveyQestnr.setEmailSurveyUrl(null);
        tbSurveyQestnr.setEmailShrtenUrl(null);
        tbSurveyQestnr.setSmsSurveyUrl(null);
        tbSurveyQestnr.setSmsShrtenUrl(null);
        tbSurveyQestnrMapper.insert(tbSurveyQestnr);

        TbSurveyQestnrCmmn tbSurveyQestnrCmmn = model.getTbSurveyQestnrCmmn();
        tbSurveyQestnrCmmn.setSurveyQestnrId(tbSurveyQestnr.getSurveyQestnrId());
        tbSurveyQestnrCmmnMapper.insert(tbSurveyQestnrCmmn);

        jdSurveyDefinitionService.createCopy(model.getSurveyDefinitionId(), jdSurveyDefinition);

        return tbSurveyQestnr.getSurveyQestnrId();
    }

    /**
     * ??????
     *
     * @param model
     */
    public void update(QuestionnaireManageModel model) {
        TbSurveyQestnr tbSurveyQestnr = model.getTbSurveyQestnr();
        TbSurveyQestnrCmmn tbSurveyQestnrCmmn = model.getTbSurveyQestnrCmmn();
        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();

        QuestionnaireManageModel modelDb = this.read(model.getTbSurveyQestnr().getSurveyQestnrId());

        if (!modelDb.isEditable()) {
            throw new BizException("????????? ??? ?????? ??????????????????.");
        }

        tbSurveyQestnrMapper.update(tbSurveyQestnr);

        if (tbSurveyQestnrCmmnMapper.update(tbSurveyQestnrCmmn) == 0) {
            tbSurveyQestnrCmmn.setSurveyQestnrId(tbSurveyQestnr.getSurveyQestnrId());
            tbSurveyQestnrCmmnMapper.insert(tbSurveyQestnrCmmn);
        }

        jdSurveyDefinition.setIsPublic(false);
        jdSurveyDefinitionMapper.update(jdSurveyDefinition);
    }

    /**
     * ??????
     *
     * @param surveyQestnrId
     */
    public void delete(String surveyQestnrId) {
        QuestionnaireManageModel modelDb = this.read(surveyQestnrId);

        if (!modelDb.isEditable() || !StringUtils.equals(modelDb.getJdSurveyDefinition().getStatus(), "I")) {
            throw new BizException("????????? ??? ?????? ??????????????????.");
        }

        tbSurveyQestnrPersonMapper.deleteListBySurveyQestnrId(surveyQestnrId);
        tbSurveyQestnrCmmnMapper.delete(surveyQestnrId);
        tbSurveyQestnrMapper.delete(surveyQestnrId);
        jdSurveyDefinitionService.delete(modelDb.getTbSurveyQestnr().getSurveyDefinitionId());
    }

    /**
     * ??????(??????)
     *
     * @param surveyQestnrId
     * @param publishModel
     */
    public void publish(String surveyQestnrId, QuestionnairePublishModel publishModel) {
        QuestionnaireManageModel modelDb = this.read(surveyQestnrId);

        if (!StringUtils.equals(modelDb.getJdSurveyDefinition().getStatus(), "P")) {
            throw new BizException("?????? ????????? ????????? ??????(??????)??? ??? ????????????.");
        }

        if (tbSurveyQestnrPersonMapper.selectCountBySurveyQestnrId(surveyQestnrId) == 0) {
            throw new BizException("?????? ???????????? ?????? ???????????????.");
        }

        Long surveyDefinitionId = modelDb.getJdSurveyDefinition().getId();

        questionnaireManageMapper.deleteSurveyDataAll(surveyDefinitionId);

        this.deleteAllBySurveyDefinitionId(surveyDefinitionId);

        TbSurveyQestnr tbSurveyQestnr = modelDb.getTbSurveyQestnr();
        tbSurveyQestnr.setSndngYn(true);
        tbSurveyQestnr.setEmailSndngYn(publishModel.getEmailSndngYn());
        tbSurveyQestnr.setSmsSndngYn(publishModel.getSmsSndngYn());
        tbSurveyQestnrMapper.updateSndngYn(tbSurveyQestnr);
    }

    /**
     * ???????????? ?????? ?????? - SURVEY_DEFINITION_ID???
     *
     * @param surveyDefinitionId
     */
    public void deleteAllBySurveyDefinitionId(Long surveyDefinitionId) {
        tbSurveyCmmnRspnsMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyDocumentMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
    }

    /**
     * ????????? ?????? ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readTemplateSearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        questionnaireManageMapper.selectTemplateSearchList(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ????????? ?????? - ?????????
     *
     * @param surveyTmplatId
     * @return
     */
    public QuestionnaireManageModel readTemplate(String surveyTmplatId) {
        TemplateManageModel templateManageModel = templateManageService.read(surveyTmplatId);
        TbSurveyTmplat tbSurveyTmplat = templateManageModel.getTbSurveyTmplat();

        TbSurveyQestnr tbSurveyQestnr = new TbSurveyQestnr();
        tbSurveyQestnr.setSurveyDefinitionId(tbSurveyTmplat.getSurveyDefinitionId());
        tbSurveyQestnr.setSurveyRealmId(tbSurveyTmplat.getSurveyRealmId());
        tbSurveyQestnr.setSurveyRealmNm(tbSurveyTmplat.getSurveyRealmNm());
        tbSurveyQestnr.setCnrsYn(null);
        tbSurveyQestnr.setQestnrGdcc(tbSurveyTmplat.getQestnrGdcc());
        tbSurveyQestnr.setQestnrEndc(tbSurveyTmplat.getQestnrEndc());
        tbSurveyQestnr.setQestnrBcrnColor(tbSurveyTmplat.getQestnrBcrnColor());

        QuestionnaireManageModel model = new QuestionnaireManageModel();
        TbSurveyQestnrCmmn tbSurveyQestnrCmmn = new TbSurveyQestnrCmmn();

        model.setTbSurveyQestnr(tbSurveyQestnr);
        model.setTbSurveyQestnrCmmn(tbSurveyQestnrCmmn);
        model.setJdSurveyDefinition(templateManageModel.getJdSurveyDefinition());
        model.setSurveyDefinitionId(tbSurveyTmplat.getSurveyDefinitionId());
        model.setBackgroundColors(surveyConfig.getBackgroundColors());

        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();
        jdSurveyDefinition.setStartDt(null);
        jdSurveyDefinition.setEndDt(null);
        jdSurveyDefinition.setStatus("I");

        return model;
    }

    /**
     * ?????? ????????? ?????? ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPrevSurveySearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        questionnaireManageMapper.selectPrevSurveySearchList(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ?????? ????????? ?????? - ?????????
     *
     * @param surveyQestnrId
     * @return
     */
    public QuestionnaireManageModel readPrevSurvey(String surveyQestnrId) {
        QuestionnaireManageModel model = this.read(surveyQestnrId);
        TbSurveyQestnr tbSurveyQestnr = model.getTbSurveyQestnr();

        tbSurveyQestnr.setSndngYn(false);

        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();
        jdSurveyDefinition.setStartDt(null);
        jdSurveyDefinition.setEndDt(null);
        jdSurveyDefinition.setStatus("I");

        model.setSurveyDefinitionId(tbSurveyQestnr.getSurveyDefinitionId());

        return model;
    }

    /**
     * ??????(?????????) ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPersonList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        tbSurveyQestnrPersonToPersonMapper.selectGridListBySurveyQestnrId(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ??????(?????????) ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPersonGroupList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        questionnaireManageMapper.selectPersonGroupList(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ????????? ??????(?????????) ?????? ??????
     *
     * @param surveyQestnrId
     * @param surveyGroupId
     * @return
     */
    public int createGroupPersonList(String surveyQestnrId, String surveyGroupId) {
        return questionnaireManageMapper.insertPersonListFromGroupId(surveyQestnrId, surveyGroupId);
    }

    /**
     * ??????(?????????) ?????? ??????
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPersonSearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        questionnaireManageMapper.selectPersonSearchList(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * ??????(?????????) ??????
     *
     * @param surveyQestnrId
     * @param personId
     */
    public void createPerson(String surveyQestnrId, String personId) {
        TbSurveyQestnrPerson tbSurveyQestnrPerson = new TbSurveyQestnrPerson().newId();
        tbSurveyQestnrPerson.setSurveyQestnrId(surveyQestnrId);
        tbSurveyQestnrPerson.setPersonId(personId);
        tbSurveyQestnrPerson.setSndngYn(false);
        tbSurveyQestnrPerson.setEmailSurveyUrl(null);
        tbSurveyQestnrPerson.setEmailShrtenUrl(null);
        tbSurveyQestnrPerson.setSmsSurveyUrl(null);
        tbSurveyQestnrPerson.setSmsShrtenUrl(null);
        tbSurveyQestnrPerson.setSurveyId(null);

        questionnaireManageMapper.insertPersonNotExists(tbSurveyQestnrPerson);
    }

    /**
     * ??????(?????????) ??????
     *
     * @param surveyPersonId
     */
    public void deletePerson(String surveyPersonId) {
        tbSurveyQestnrPersonMapper.delete(surveyPersonId);
    }

    /**
     * ?????? ?????? ??????
     *
     * @param surveyQestnrId
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> readDeptTree(String surveyQestnrId, String deptSe) {
        return questionnaireManageMapper.selectDeptTreeList(surveyQestnrId, DeptSeEnums.valueOf(deptSe));
    }

    /**
     * ????????? ??????(?????????) ?????? ??????
     *
     * @param surveyQestnrId
     * @param tbComDeptList
     */
    public void createDeptPersonList(String surveyQestnrId, List<TbComDept> tbComDeptList) {
        for (TbComDept tbComDept : tbComDeptList) {
            questionnaireManageMapper.insertPersonListFromDeptId(surveyQestnrId, tbComDept.getDeptId());
        }
    }

    /**
     * ?????? ?????? ?????? ?????? ??????
     *
     * @return
     */
    public List<TbSurveyQestnr> readSurveySendTargetList() {
        return tbSurveyQestnrMapper.selectSurveySendTargetList();
    }

    /**
     * ?????? ?????? ?????? ?????? ?????? ??????
     *
     * @param surveyQestnrId
     * @param resultHandler
     */
    public void readSurveySendTargetPersonList(String surveyQestnrId,
            DataResultHandler<TbSurveyQestnrPersonToPerson> resultHandler) {
        tbSurveyQestnrPersonToPersonMapper.selectSurveySendTargetPersonList(surveyQestnrId, resultHandler);
    }

    /**
     * ?????? ?????? ??????
     *
     * @param tbSurveyQestnr
     * @param tbSurveyQestnrPersonToPerson
     * @param registPerson
     * @param registPersonDept
     * @param publishModel
     */
    public void createSurveySend(TbSurveyQestnr tbSurveyQestnr,
            TbSurveyQestnrPersonToPerson tbSurveyQestnrPersonToPerson, TbComPerson registPerson,
            TbComDept registPersonDept, QuestionnairePublishModel publishModel) {

        if (StringUtils.isAnyEmpty(tbSurveyQestnr.getEmailSurveyUrl(), tbSurveyQestnr.getEmailShrtenUrl(),
                tbSurveyQestnr.getSmsSurveyUrl(), tbSurveyQestnr.getSmsShrtenUrl())) {

            tbSurveyQestnr.resetSurveyUrl(sharedConfig.getServerUrl());
            tbSurveyQestnrMapper.update(tbSurveyQestnr);
        }

        String surveyPersonId = tbSurveyQestnrPersonToPerson.getSurveyPersonId();

        TbSurveyQestnrPerson tbSurveyQestnrPerson = new TbSurveyQestnrPerson();
        tbSurveyQestnrPerson.setSurveyPersonId(tbSurveyQestnrPersonToPerson.getSurveyPersonId());
        tbSurveyQestnrPerson.setSurveyQestnrId(tbSurveyQestnr.getSurveyQestnrId());
        tbSurveyQestnrPerson.setPersonId(tbSurveyQestnrPersonToPerson.getPersonId());
        tbSurveyQestnrPerson.setSndngYn(true);
        tbSurveyQestnrPerson.setSurveyId(tbSurveyQestnrPersonToPerson.getSurveyId());

        if (StringUtils.isAnyEmpty(tbSurveyQestnrPerson.getEmailSurveyUrl(), tbSurveyQestnrPerson.getEmailShrtenUrl(),
                tbSurveyQestnrPerson.getSmsSurveyUrl(), tbSurveyQestnrPerson.getSmsShrtenUrl())) {

            tbSurveyQestnrPerson.resetSurveyUrl(tbSurveyQestnr, tbSurveyQestnrPersonToPerson.getUserYn());
            tbSurveyQestnrPersonMapper.update(tbSurveyQestnrPerson);
        }

        JdSurveyDefinition jdSurveyDefinition = jdSurveyDefinitionMapper.select(tbSurveyQestnr.getSurveyDefinitionId());

        String startDate = StringUtils.replace(DateFormatUtils.format(jdSurveyDefinition.getStartDt(), "yyyy.MM.dd"),
                ".0", ". ");
        String startDay = CommonUtil.getDayOfWeekName(jdSurveyDefinition.getStartDt());
        String endDate = StringUtils.replace(DateFormatUtils.format(jdSurveyDefinition.getEndDt(), "yyyy.MM.dd"), ".0",
                ". ");
        String endDay = CommonUtil.getDayOfWeekName(jdSurveyDefinition.getEndDt());

        String registDeptNm = tbSurveyQestnr.getRegistDeptNm();

        if (StringUtils.isEmpty(registDeptNm)) {
            registDeptNm = tbSurveyQestnrPersonToPerson.getDeptNames();
        }

        if (publishModel == null) {
            publishModel = new QuestionnairePublishModel();
            publishModel.setEmailSndngYn(tbSurveyQestnr.getEmailSndngYn());
            publishModel.setSmsSndngYn(tbSurveyQestnr.getSmsSndngYn());
        }

        // ????????? ?????? - ????????? ????????? ???????????????, ????????? ????????? ?????? ??????
        if (BooleanUtils.isTrue(publishModel.getEmailSndngYn())
                && StringUtils.isNotEmpty(tbSurveyQestnrPersonToPerson.getEmailAdres())) {

            FreeMarkerTemplateEmail email = new FreeMarkerTemplateEmail();
            email.setEmailSj("[????????????] " + jdSurveyDefinition.getName());
            email.setSender("survey@jj.ac.kr", "???????????????");
            email.addRecptn(RecipientType.TO, tbSurveyQestnrPersonToPerson.getEmailAdres(),
                    tbSurveyQestnrPersonToPerson.getKoreanNm(), tbSurveyQestnrPersonToPerson.getPersonId());
            email.setTemplateData("registDeptNm", registDeptNm);
            email.setTemplateData("tbSurveyQestnr", tbSurveyQestnr);
            email.setTemplateData("jdSurveyDefinition", jdSurveyDefinition);
            email.setTemplateData("startDate", startDate);
            email.setTemplateData("startDay", startDay);
            email.setTemplateData("endDate", endDate);
            email.setTemplateData("endDay", endDay);
            email.setTemplateData("urlLink", tbSurveyQestnrPerson.getEmailShrtenUrl());
            email.processTemplate("/qestnrmanage/SurveyRequestEmail.html");
            email.getTbComEmail().setRelateId(surveyPersonId);

            emailService.create(email);
        }

        // ??????????????? ?????? - SMS ????????? ???????????????, ??????????????? ?????? ??????
        if (BooleanUtils.isTrue(publishModel.getSmsSndngYn())
                && StringUtils.isNotEmpty(tbSurveyQestnrPersonToPerson.getTlphonNo())
                && StringUtils.isNotEmpty(registPerson.getTlphonNo())
                && StringUtils.startsWithAny(tbSurveyQestnrPersonToPerson.getTlphonNo(), "010")) {

            StringBuilder message = new StringBuilder();

            message.append("??????: [??????????????? " + StringUtils.defaultString(registDeptNm) + "] "
                    + StringUtils.defaultString(jdSurveyDefinition.getName()) + "\n");
            message.append("\n");
            message.append("???????????????! ??????????????? " + StringUtils.defaultString(registDeptNm) + "?????????.\n");
            message.append(jdSurveyDefinition.getName() + " ??????????????? ???????????? ????????????.\n");
            message.append("?????????????????? ?????? ????????? ???????????? ????????? ????????? ????????? ????????? ?????????????????????.\n");
            message.append("\n");
            message.append("* ????????????: " + startDate + ".(" + startDay + ")~" + endDate + ".(" + endDay + ")");

            if (StringUtils.equals(tbSurveyQestnrPersonToPerson.getHffcSttus(), "S")) {
                message.append("\n* ????????????: StarT SP 50 (???????????????)");
            }

            message.append("\n\n?????? ????????? ?????? ????????? ????????? ???????????? ??????????????????.");
            message.append("\n\n" + tbSurveyQestnrPerson.getSmsShrtenUrl());

            BaseSms sms = new BaseSms();
            // sms.setDeptCode(registPersonDept.getDeptCode());
            sms.setDeptCode("50548200"); // ?????? ?????????????????? ???????????? ???????????? ??????
            sms.setTranPhone(tbSurveyQestnrPersonToPerson.getTlphonNo()); // ????????? ????????????
            sms.setTranCallback(registPerson.getTlphonNo()); // ????????? ????????????
            sms.setTranMsg(message.toString()); // ?????? ?????????
            sms.setTranEtc1("??????????????? ??????"); // ????????? ??????
            sms.setTranEtc3(tbSurveyQestnrPersonToPerson.getKoreanNm()); // ????????????

            smsService.create(sms);
        }
    }

    /**
     * ?????? ?????? ??????
     *
     * @param surveyPersonId
     * @param publishModel
     */
    public void sendMailTarget(String surveyPersonId, QuestionnairePublishModel publishModel) {
        TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper.select(surveyPersonId);
        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.select(tbSurveyQestnrPerson.getSurveyQestnrId());
        TbComPerson registPerson = tbComPersonMapper.select(tbSurveyQestnr.getRegistPsnId());
        TbComDept registPersonDept = tbComDeptMapper.select(registPerson.getDeptId());

        TbSurveyQestnrPersonToPerson tbSurveyQestnrPersonToPerson = tbSurveyQestnrPersonToPersonMapper
                .selectSurveySendTargetPerson(tbSurveyQestnrPerson.getSurveyQestnrId(),
                        tbSurveyQestnrPerson.getSurveyPersonId());

        this.createSurveySend(tbSurveyQestnr, tbSurveyQestnrPersonToPerson, registPerson, registPersonDept,
                publishModel);
    }

}
