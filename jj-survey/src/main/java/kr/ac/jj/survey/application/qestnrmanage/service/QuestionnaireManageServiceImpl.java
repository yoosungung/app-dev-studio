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
 * 설문지 관리 Service
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
     * 목록 조회
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
     * 조회 - 생성용
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
     * 조회
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
     * 생성
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
     * 수정
     *
     * @param model
     */
    public void update(QuestionnaireManageModel model) {
        TbSurveyQestnr tbSurveyQestnr = model.getTbSurveyQestnr();
        TbSurveyQestnrCmmn tbSurveyQestnrCmmn = model.getTbSurveyQestnrCmmn();
        JdSurveyDefinition jdSurveyDefinition = model.getJdSurveyDefinition();

        QuestionnaireManageModel modelDb = this.read(model.getTbSurveyQestnr().getSurveyQestnrId());

        if (!modelDb.isEditable()) {
            throw new BizException("수정할 수 없는 설문지입니다.");
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
     * 삭제
     *
     * @param surveyQestnrId
     */
    public void delete(String surveyQestnrId) {
        QuestionnaireManageModel modelDb = this.read(surveyQestnrId);

        if (!modelDb.isEditable() || !StringUtils.equals(modelDb.getJdSurveyDefinition().getStatus(), "I")) {
            throw new BizException("삭제할 수 없는 설문지입니다.");
        }

        tbSurveyQestnrPersonMapper.deleteListBySurveyQestnrId(surveyQestnrId);
        tbSurveyQestnrCmmnMapper.delete(surveyQestnrId);
        tbSurveyQestnrMapper.delete(surveyQestnrId);
        jdSurveyDefinitionService.delete(modelDb.getTbSurveyQestnr().getSurveyDefinitionId());
    }

    /**
     * 제출(발송)
     *
     * @param surveyQestnrId
     * @param publishModel
     */
    public void publish(String surveyQestnrId, QuestionnairePublishModel publishModel) {
        QuestionnaireManageModel modelDb = this.read(surveyQestnrId);

        if (!StringUtils.equals(modelDb.getJdSurveyDefinition().getStatus(), "P")) {
            throw new BizException("개시 상태의 설문만 제출(발송)할 수 있습니다.");
        }

        if (tbSurveyQestnrPersonMapper.selectCountBySurveyQestnrId(surveyQestnrId) == 0) {
            throw new BizException("설문 대상자를 먼저 추가하세요.");
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
     * 응답내용 전체 삭제 - SURVEY_DEFINITION_ID로
     *
     * @param surveyDefinitionId
     */
    public void deleteAllBySurveyDefinitionId(Long surveyDefinitionId) {
        tbSurveyCmmnRspnsMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyDocumentMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
        jdSurveyMapper.deleteListBySurveyDefinitionId(surveyDefinitionId);
    }

    /**
     * 템플릿 검색 목록 조회
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
     * 템플릿 조회 - 생성용
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
     * 이전 설문지 검색 목록 조회
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
     * 이전 설문지 조회 - 생성용
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
     * 사람(대상자) 목록 조회
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
     * 사람(대상자) 그룹 검색
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
     * 그룹별 사람(대상자) 목록 생성
     *
     * @param surveyQestnrId
     * @param surveyGroupId
     * @return
     */
    public int createGroupPersonList(String surveyQestnrId, String surveyGroupId) {
        return questionnaireManageMapper.insertPersonListFromGroupId(surveyQestnrId, surveyGroupId);
    }

    /**
     * 사람(대상자) 목록 검색
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
     * 사람(대상자) 추가
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
     * 사람(대상자) 삭제
     *
     * @param surveyPersonId
     */
    public void deletePerson(String surveyPersonId) {
        tbSurveyQestnrPersonMapper.delete(surveyPersonId);
    }

    /**
     * 조직 트리 조회
     *
     * @param surveyQestnrId
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> readDeptTree(String surveyQestnrId, String deptSe) {
        return questionnaireManageMapper.selectDeptTreeList(surveyQestnrId, DeptSeEnums.valueOf(deptSe));
    }

    /**
     * 조직별 사람(대상자) 목록 생성
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
     * 설문 발송 대상 목록 조회
     *
     * @return
     */
    public List<TbSurveyQestnr> readSurveySendTargetList() {
        return tbSurveyQestnrMapper.selectSurveySendTargetList();
    }

    /**
     * 설문 발송 대상 사람 목록 조회
     *
     * @param surveyQestnrId
     * @param resultHandler
     */
    public void readSurveySendTargetPersonList(String surveyQestnrId,
            DataResultHandler<TbSurveyQestnrPersonToPerson> resultHandler) {
        tbSurveyQestnrPersonToPersonMapper.selectSurveySendTargetPersonList(surveyQestnrId, resultHandler);
    }

    /**
     * 설문 발송 생성
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

        // 이메일 발송 - 이메일 발송이 선택되었고, 이메일 주소가 있는 경우
        if (BooleanUtils.isTrue(publishModel.getEmailSndngYn())
                && StringUtils.isNotEmpty(tbSurveyQestnrPersonToPerson.getEmailAdres())) {

            FreeMarkerTemplateEmail email = new FreeMarkerTemplateEmail();
            email.setEmailSj("[설문요청] " + jdSurveyDefinition.getName());
            email.setSender("survey@jj.ac.kr", "전주대학교");
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

        // 문자메시지 발송 - SMS 발송이 선택되었고, 전화번호가 있는 경우
        if (BooleanUtils.isTrue(publishModel.getSmsSndngYn())
                && StringUtils.isNotEmpty(tbSurveyQestnrPersonToPerson.getTlphonNo())
                && StringUtils.isNotEmpty(registPerson.getTlphonNo())
                && StringUtils.startsWithAny(tbSurveyQestnrPersonToPerson.getTlphonNo(), "010")) {

            StringBuilder message = new StringBuilder();

            message.append("제목: [전주대학교 " + StringUtils.defaultString(registDeptNm) + "] "
                    + StringUtils.defaultString(jdSurveyDefinition.getName()) + "\n");
            message.append("\n");
            message.append("안녕하세요! 전주대학교 " + StringUtils.defaultString(registDeptNm) + "입니다.\n");
            message.append(jdSurveyDefinition.getName() + " 설문조사를 진행하고 있습니다.\n");
            message.append("바쁘시더라도 잠시 시간을 내주셔서 귀중한 의견을 주시면 대단히 감사하겠습니다.\n");
            message.append("\n");
            message.append("* 응답기간: " + startDate + ".(" + startDay + ")~" + endDate + ".(" + endDay + ")");

            if (StringUtils.equals(tbSurveyQestnrPersonToPerson.getHffcSttus(), "S")) {
                message.append("\n* 참여혜택: StarT SP 50 (조사완료시)");
            }

            message.append("\n\n아래 링크를 통해 설문에 참여해 주시기를 부탁드립니다.");
            message.append("\n\n" + tbSurveyQestnrPerson.getSmsShrtenUrl());

            BaseSms sms = new BaseSms();
            // sms.setDeptCode(registPersonDept.getDeptCode());
            sms.setDeptCode("50548200"); // 실제 부서코드로는 매칭되는 데이터가 없음
            sms.setTranPhone(tbSurveyQestnrPersonToPerson.getTlphonNo()); // 수신자 전화번호
            sms.setTranCallback(registPerson.getTlphonNo()); // 송신자 전화번호
            sms.setTranMsg(message.toString()); // 전송 메시지
            sms.setTranEtc1("전주대학교 설문"); // 메시지 제목
            sms.setTranEtc3(tbSurveyQestnrPersonToPerson.getKoreanNm()); // 수신자명

            smsService.create(sms);
        }
    }

    /**
     * 대상 메일 발송
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
