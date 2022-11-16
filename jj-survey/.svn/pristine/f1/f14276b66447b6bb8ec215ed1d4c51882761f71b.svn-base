package com.jd.survey.web.surveys;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.survey.domain.settings.SurveyDefinition;
import com.jd.survey.domain.survey.Survey;
import com.jd.survey.domain.survey.SurveyStatus;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.service.survey.SurveyService;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

/**
 * 설문 Controller
 */
@Controller
@RequestMapping(path = "/survey")
public class SurveyController {

    private @Autowired SurveySettingsService surveySettingsService;
    private @Autowired SurveyService surveyService;
    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired TbSurveyQestnrPersonMapper tbSurveyQestnrPersonMapper;

    /**
     * 화면
     *
     * @param surveyDefinitionId
     * @param conectLink
     * @param request
     * @return
     */
    @GetMapping(value = "/{surveyDefinitionId}")
    public String viewBySurveyDefinitionId(@PathVariable Long surveyDefinitionId,
            @RequestParam(required = false) String conectLink, HttpServletRequest request) {

        TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.selectBySurveyDefinitionId(surveyDefinitionId);

        if (tbSurveyQestnr == null) {
            throw new BizException("존재하지 않는 설문입니다.");
        }

        boolean isAdminUser = SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SURVEY_ADMIN");
        LoginUser loginUser = SessionContextUtil.getLoginUser();
        String personId = loginUser.getPersonId();

        TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper
                .selectBySurveyQestnrIdAndPersonId(tbSurveyQestnr.getSurveyQestnrId(), personId);

        if (!isAdminUser && tbSurveyQestnrPerson == null && BooleanUtils.isTrue(tbSurveyQestnr.getSndngYn())) {
            throw new BizException("등록되지 않은 설문 참가자입니다.");
        }

        SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
        Survey survey = surveyService.getSurveyBySurveyDefinitionId(surveyDefinition, tbSurveyQestnrPerson);

        if (tbSurveyQestnrPerson != null) {
            if (tbSurveyQestnrPerson.getSurveyId() == null || tbSurveyQestnrPerson.getCurrPgeNo() == null
                    || tbSurveyQestnrPerson.getStrePgeNo() == null || tbSurveyQestnrPerson.getConectLink() == null) {
                tbSurveyQestnrPerson.setSurveyId(survey.getId());
                tbSurveyQestnrPerson.setCurrPgeNo((short) 0);
                tbSurveyQestnrPerson.setStrePgeNo((short) -1);
                tbSurveyQestnrPerson.setConectDevice(CommonUtil.getConnectionDevice(request));
                tbSurveyQestnrPerson.setConectLink(conectLink);
                tbSurveyQestnrPersonMapper.update(tbSurveyQestnrPerson);
            } else if (StringUtils.isAllEmpty(tbSurveyQestnrPerson.getConectDevice())) {
                tbSurveyQestnrPerson.setConectDevice(CommonUtil.getConnectionDevice(request));
                tbSurveyQestnrPersonMapper.update(tbSurveyQestnrPerson);
            }
        }

        String surveyPath = (surveyDefinition.getIsPublic() ? "public" : "private");
        String surveyIdParam = SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), request);

        if (survey.getStatus() == SurveyStatus.D) {
            throw new BizException("삭제된 설문입니다.");
        }

        String viewName = "redirect:/surveys/" + surveyPath + "/" + surveyIdParam;

        if (survey.getStatus() == SurveyStatus.S) {
            return viewName;
        }

        if (tbSurveyQestnrPerson != null && tbSurveyQestnrPerson.getCurrPgeNo() != null) {
            return viewName + "/" + tbSurveyQestnrPerson.getCurrPgeNo();
        }

        return viewName;
    }

}
