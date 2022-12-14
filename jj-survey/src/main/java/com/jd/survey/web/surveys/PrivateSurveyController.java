/*Copyright (C) 2014  JD Software, Inc.

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.

  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.jd.survey.web.surveys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jd.survey.GlobalSettings;
import com.jd.survey.domain.security.User;
import com.jd.survey.domain.settings.QuestionOption;
import com.jd.survey.domain.settings.SurveyDefinition;
import com.jd.survey.domain.survey.QuestionAnswer;
import com.jd.survey.domain.survey.Survey;
import com.jd.survey.domain.survey.SurveyDocument;
import com.jd.survey.domain.survey.SurveyPage;
import com.jd.survey.domain.survey.SurveyStatus;
import com.jd.survey.pub.service.SurveyDefinitionService;
import com.jd.survey.service.security.SecurityService;
import com.jd.survey.service.security.UserService;
import com.jd.survey.service.settings.ApplicationSettingsService;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.service.survey.SurveyService;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.survey.domain.main.mapper.survey.cmmnrspns.TbSurveyCmmnRspnsMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.TbSurveyQestnrMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.cmmn.TbSurveyQestnrCmmnMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person.TbSurveyQestnrPersonMapper;
import kr.ac.jj.survey.domain.main.model.survey.cmmnrspns.TbSurveyCmmnRspns;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn.TbSurveyQestnrCmmn;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

@RequestMapping({ "/surveys/private" })
@Controller
public class PrivateSurveyController {

    private static final Logger log = LoggerFactory.getLogger(PrivateSurveyController.class);

    private static final String DATE_FORMAT = "date_format";

    private static final String REQUEST_PATH_WARNING_MESSAGE = " request path:";
    private static final String FROM_IP_WARNING_MESSAGE = " from IP Address:";
    private static final String FROM_USER_LOGIN_WARNING_MESSAGE = " attempted with user login:";

    private static final String UNAUTHORIZED_ATTEMPT_TO_CREATE_SURVEY_WARNING_MESSAGE = "Unauthorized Attempt to access survey definition id:";
    private static final String UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE = "Unauthorized Attempt to access survey id:";
    private static final String UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE = "Unauthorized Attempt to edit a submitted survey:";

    private static final String POLICY_FILE_LOCATION = "/antisamy-tinymce-1-4-4.xml";

    private @Autowired MessageSource messageSource;
    private @Autowired SurveyService surveyService;
    private @Autowired SurveySettingsService surveySettingsService;
    private @Autowired UserService userService;
    private @Autowired SecurityService securityService;
    private @Autowired ApplicationSettingsService applicationSettingsService;
    private @Autowired SurveyDefinitionService surveyDefinitionService;

    private @Autowired TbSurveyQestnrMapper tbSurveyQestnrMapper;
    private @Autowired TbSurveyQestnrPersonMapper tbSurveyQestnrPersonMapper;
    private @Autowired TbSurveyQestnrCmmnMapper tbSurveyQestnrCmmnMapper;
    private @Autowired TbSurveyCmmnRspnsMapper tbSurveyCmmnRspnsMapper;

    /**
     * Returns the survey logo image binary
     *
     * @param departmentId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/logo/{id}", produces = "text/html")
    public void getSurveyLogo(@PathVariable("id") Long surveyDefinitionId, Model uiModel,
            HttpServletRequest httpServletRequest, HttpServletResponse response) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            uiModel.asMap().clear();
            User user = userService.user_findByLogin(loginUser.getPersonId());
            // Check if the user is authorized
            if (!securityService.userIsAuthorizedToCreateSurvey(surveyDefinitionId, user)) {
                log.warn("Unauthorized access to url path " + httpServletRequest.getPathInfo()
                        + " attempted by user login:" + loginUser.getPersonId() + "from IP:"
                        + httpServletRequest.getLocalAddr());
                throw (new RuntimeException("Unauthorized access to logo"));
            } else {
                SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
                // response.setContentType("image/png");
                ServletOutputStream servletOutputStream = response.getOutputStream();
                servletOutputStream.write(surveyDefinition.getLogo());
                servletOutputStream.flush();
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Lists all the available survey types
     *
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(produces = "text/html", method = RequestMethod.GET)
    public String listSurveys(Model uiModel, HttpServletRequest httpServletRequest) {
        System.out.println(
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            User user = userService.user_findByLogin(loginUser.getPersonId());
            Set<SurveyDefinition> surveyDefinitions = surveySettingsService
                    .surveyDefinition_findAllPublishedExternal(user);
            uiModel.addAttribute("survey_base_path", "/surveys/private");
            uiModel.addAttribute("surveyDefinitions", surveyDefinitions);
            return "surveys/surveys";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Shows the survey submissions page if not entries found it will create a new
     * one. if only one entry found will redirect to the survey show page
     *
     * @param surveyDefinitionId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/{id}", params = "list", produces = "text/html")
    public String listSurveyEntries(@PathVariable("id") Long surveyDefinitionId, Model uiModel,
            HttpServletRequest httpServletRequest) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            User user = userService.user_findByLogin(loginUser.getPersonId());
            // Check if the user is authorized
            if (!securityService.userIsAuthorizedToCreateSurvey(surveyDefinitionId, user)) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_CREATE_SURVEY_WARNING_MESSAGE + surveyDefinitionId
                        + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                        + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }

            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
            Set<Survey> userSurveyEntries = surveyService.survey_findUserEntriesByTypeIdAndLogin(surveyDefinitionId,
                    loginUser.getPersonId());

            if (userSurveyEntries == null || userSurveyEntries.size() == 0) {
                // No User entries for this survey, create a new one
                Survey survey = surveyService.survey_create(surveyDefinitionId, loginUser.getPersonId(),
                        httpServletRequest.getRemoteAddr());
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";
            }

            // entries found
            if (userSurveyEntries.size() == 1) {
                // only on entry found
                Iterator<Survey> it = userSurveyEntries.iterator();
                Survey survey = it.next(); // get the first and only element in the set

                if (survey.getStatus() == SurveyStatus.I || survey.getStatus() == SurveyStatus.R) {
                    // survey is incomplete or reopened
                    return "redirect:/surveys/private/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";
                }

                // List all entries
                uiModel.addAttribute("survey_base_path", "surveys/private");
                uiModel.addAttribute("surveyDefinition", surveyDefinition);
                uiModel.addAttribute("userSurveyEntries", userSurveyEntries);
                return "surveys/entries";
            }

            // multiple entries found
            uiModel.addAttribute("survey_base_path", "surveys/private");
            uiModel.addAttribute("surveyDefinition", surveyDefinition);
            uiModel.addAttribute("userSurveyEntries", userSurveyEntries);
            return "surveys/entries";

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Creates a new survey based on the passed surveyDefintionId
     *
     * @param surveyDefinitionId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/{id}", params = "create", produces = "text/html")
    public String createSurvey(@PathVariable("id") Long surveyDefinitionId, Model uiModel,
            HttpServletRequest httpServletRequest) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            User user = userService.user_findByLogin(loginUser.getPersonId());
            // Check if the user is authorized
            if (!securityService.userIsAuthorizedToCreateSurvey(surveyDefinitionId, user)) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_CREATE_SURVEY_WARNING_MESSAGE + surveyDefinitionId
                        + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                        + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }
            Survey survey = surveyService.survey_create(surveyDefinitionId, loginUser.getPersonId(),
                    httpServletRequest.getRemoteAddr());
            return "redirect:/surveys/private/"
                    + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * ???????????? ??????
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/{id}", params = "modify", produces = "text/html")
    public String modSurvey(@PathVariable("id") Long surveyId, HttpServletRequest httpServletRequest) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {

            if (surveyService.modSurveyStatus(surveyId, loginUser.getPersonId()) > 0) {
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(surveyId.toString(), httpServletRequest) + "/0";
            } else {
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(surveyId.toString(), httpServletRequest) + "?list";
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Shows the submit page
     *
     * @param surveyId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "submit/{id}", produces = "text/html")
    public String preparesubmitSurvey(@PathVariable("id") Long surveyId, Model uiModel,
            HttpServletRequest httpServletRequest) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            Survey survey = surveyService.survey_findById(surveyId);
            // Check if the user is authorized
            if (!survey.getLogin().equals(loginUser.getPersonId())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + surveyId + REQUEST_PATH_WARNING_MESSAGE
                        + httpServletRequest.getPathInfo() + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId()
                        + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }

            // Check that the survey was not submitted
            if (!(survey.getStatus().equals(SurveyStatus.I) || survey.getStatus().equals(SurveyStatus.R))) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE + surveyId
                        + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                        + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

            TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper.selectBySurveyId(surveyId);

            Short lastPageorder = surveyPages.get(surveyPages.size() - 1).getOrder();

            if (tbSurveyQestnrPerson != null && tbSurveyQestnrPerson.getStrePgeNo() < lastPageorder) {
                log.error("????????? ????????? ?????? => ?????? ?????????: {}, ?????? ?????????: ??????", tbSurveyQestnrPerson.getStrePgeNo());

                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";
            }

            TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.selectBySurveyDefinitionId(survey.getTypeId());
            TbSurveyQestnrCmmn tbSurveyQestnrCmmn = tbSurveyQestnrCmmnMapper.select(tbSurveyQestnr.getSurveyQestnrId());
            TbSurveyCmmnRspns tbSurveyCmmnRspns = tbSurveyCmmnRspnsMapper.selectBySurveyId(survey.getId());

            uiModel.addAttribute("survey_base_path", "surveys/private");
            uiModel.addAttribute("survey", survey);
            uiModel.addAttribute("surveyDefinition",
                    surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
            uiModel.addAttribute("surveyPages", surveyPages);

            if (tbSurveyQestnrPerson != null) {
                uiModel.addAttribute("strePgeNo", tbSurveyQestnrPerson.getStrePgeNo());
            }

            uiModel.addAttribute("order", surveyPages.size() + 1);
            uiModel.addAttribute("tbSurveyQestnr", tbSurveyQestnr);
            uiModel.addAttribute("tbSurveyQestnrCmmn", tbSurveyQestnrCmmn);
            uiModel.addAttribute("tbSurveyCmmnRspns", tbSurveyCmmnRspns);

            return "surveys/submit";

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Handles the post from the submit page
     *
     * @param proceed
     * @param survey
     * @param bindingResult
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/submit", method = RequestMethod.POST, produces = "text/html")
    public String submitSurvey(@RequestParam(value = "id", required = true) Long surveyId,
            @RequestParam(value = "_submit", required = false) String proceedAction, Model uiModel,
            HttpServletRequest httpServletRequest) {

        log.info("submitPost(): id= " + surveyId);

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {

            // Check if the user is authorized
            if (!surveyService.survey_findById(surveyId).getLogin().equals(loginUser.getPersonId())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + surveyId + REQUEST_PATH_WARNING_MESSAGE
                        + httpServletRequest.getPathInfo() + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId()
                        + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }

            // Check that the survey was not submitted
            Survey dbSurvey = surveyService.survey_findById(surveyId);
            if (!(dbSurvey.getStatus().equals(SurveyStatus.I) || dbSurvey.getStatus().equals(SurveyStatus.R))) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE + surveyId
                        + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                        + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";

            }

            if (proceedAction != null) { // submit button
                uiModel.asMap().clear();
                Survey survey = surveyService.survey_submit(surveyId);
//                return "redirect:/surveys/private/" + SurveyControllerUtil.encodeUrlPathSegment(survey.getTypeId().toString(), httpServletRequest) + "?list";
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest);
            } else {
                uiModel.asMap().clear();
                Survey survey = surveyService.survey_findById(surveyId);
                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                Short order = (short) surveyPages.size();
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/"
                        + SurveyUtil.encodeUrlPathSegment(order.toString(), httpServletRequest);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }

    }

    /**
     * Shows a previously submitted survey as read only
     *
     * @param surveyId
     * @param principal
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String showSurvey(@PathVariable("id") Long surveyId, Model uiModel, HttpServletRequest httpServletRequest) {
        log.info("showSurvey surveyId=" + surveyId + " no pageOrder");

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            Survey survey = surveyService.survey_findById(surveyId);

            // Check if the user is authorized
            if (!survey.getLogin().equals(loginUser.getPersonId())) {
                boolean isAdminUser = SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SURVEY_ADMIN");

                if (!isAdminUser || survey.getStatus() != SurveyStatus.S) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + surveyId
                            + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                            + httpServletRequest.getLocalAddr());
                    return "accessDenied";
                }
            }

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            if (survey.getStatus() == SurveyStatus.I) {
                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(surveyId.toString(), httpServletRequest) + "/0";
            } else {

                String status = null;
                for (com.jd.survey.pub.domain.SurveyDefinition surveyDefinition : surveyDefinitionService
                        .getRealSurveyDefinitionList()) {
                    if (survey.getTypeId().equals(surveyDefinition.getSurveyDefinitionId())) {
                        status = surveyDefinition.getStatus();
                    }
                }

                TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.selectBySurveyDefinitionId(survey.getTypeId());
                TbSurveyQestnrCmmn tbSurveyQestnrCmmn = tbSurveyQestnrCmmnMapper
                        .select(tbSurveyQestnr.getSurveyQestnrId());
                TbSurveyCmmnRspns tbSurveyCmmnRspns = tbSurveyCmmnRspnsMapper.selectBySurveyId(survey.getId());

                uiModel.addAttribute("survey_base_path", "surveys/private");
                uiModel.addAttribute("survey", survey);
                uiModel.addAttribute("surveyDefinition",
                        surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
                uiModel.addAttribute("surveyPages", surveyPages);
                uiModel.addAttribute("surveyDefinitionStatus", status);
                uiModel.addAttribute("tbSurveyQestnr", tbSurveyQestnr);
                uiModel.addAttribute("tbSurveyQestnrCmmn", tbSurveyQestnrCmmn);
                uiModel.addAttribute("tbSurveyCmmnRspns", tbSurveyCmmnRspns);

                return "surveys/survey";
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Prepares to edit a survey page
     *
     * @param surveyId
     * @param order
     * @param uiModel
     * @param principal
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(value = "/{id}/{po}", produces = "text/html")
    public String editSurveyPage(@PathVariable("id") Long surveyId, @PathVariable("po") Short order, Model uiModel,
            HttpServletRequest httpServletRequest) {
        log.info("editSurveyPage surveyId=" + surveyId + " pageOrder" + order);

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            Survey survey = surveyService.survey_findById(surveyId);
            // Check if the user is authorized
            if (!survey.getLogin().equals(loginUser.getPersonId())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + surveyId + REQUEST_PATH_WARNING_MESSAGE
                        + httpServletRequest.getPathInfo() + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId()
                        + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            // Check that the survey was not submitted
            if (!(survey.getStatus().equals(SurveyStatus.I) || survey.getStatus().equals(SurveyStatus.R))) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE + surveyId
                        + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                        + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper.selectBySurveyId(surveyId);

            if (tbSurveyQestnrPerson != null && tbSurveyQestnrPerson.getStrePgeNo() < order - 1) {
                log.error("????????? ????????? ?????? => ?????? ?????????: {}, ?????? ?????????: {}", tbSurveyQestnrPerson.getStrePgeNo(), order);

                return "redirect:/surveys/private/"
                        + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";
            }

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

            uiModel.addAttribute("survey_base_path", "surveys/private");
            uiModel.addAttribute("survey", survey);
            uiModel.addAttribute("surveyDefinition",
                    surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
            uiModel.addAttribute("surveyPages", surveyPages);

            if (tbSurveyQestnrPerson != null) {
                uiModel.addAttribute("strePgeNo", tbSurveyQestnrPerson.getStrePgeNo());
            }

            // ????????? ??????
            com.jd.survey.pub.domain.SurveyDefinition surveyDefinition = surveyDefinitionService
                    .getSurveyDefinition(survey.getTypeId());

            // ?????? ????????? ??????
            if ("E".equals(surveyDefinition.getStatus())) {
                return "surveys/deadline";
            }

            TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.selectBySurveyDefinitionId(survey.getTypeId());
            TbSurveyQestnrCmmn tbSurveyQestnrCmmn = tbSurveyQestnrCmmnMapper.select(tbSurveyQestnr.getSurveyQestnrId());

            uiModel.addAttribute("tbSurveyQestnr", tbSurveyQestnr);
            uiModel.addAttribute("tbSurveyQestnrCmmn", tbSurveyQestnrCmmn);

            if (order == 0) {
                if (StringUtils.isEmpty(tbSurveyQestnr.getQestnrGdcc()) && !tbSurveyQestnrCmmn.isIntroEnable()) {
                    return "redirect:/surveys/private/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/1";
                }

                Map<String, Object> surveyPage = new LinkedHashMap<String, Object>();
                surveyPage.put("order", 0);

                uiModel.addAttribute("surveyPage", surveyPage);

                if (tbSurveyQestnrPerson != null) {
                    tbSurveyQestnrPersonMapper.updateCurrPgeNo(tbSurveyQestnrPerson.getSurveyPersonId(), (short) 0);
                }

                return "surveys/intro";
            }

            SurveyPage surveyPage = surveyService.surveyPage_get(surveyId, order,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

            // randomize the questions order
            if (surveyPage.getRandomizeQuestions()) {
                Collections.shuffle(surveyPage.getQuestionAnswers(), new Random(System.nanoTime()));
            }

            // randomize the questions options orders
            for (QuestionAnswer questionAnswer : surveyPage.getQuestionAnswers()) {
                questionAnswer.getQuestion()
                        .setOptionsList(new ArrayList<QuestionOption>(questionAnswer.getQuestion().getOptions()));
                if (questionAnswer.getQuestion().getRandomizeOptions()) {
                    Collections.shuffle(questionAnswer.getQuestion().getOptionsList(), new Random(System.nanoTime()));
                }
            }

            // ????????? ?????? ??? ?????? ?????????
            long questionCnt = 0;

            for (SurveyPage sp : surveyPages) {
                for (QuestionAnswer questionAnswer : sp.getQuestionAnswers()) {
                    questionCnt++;
                }
            }

            uiModel.addAttribute("curPage", order);
            uiModel.addAttribute("pageCnt", surveyPages.size());
            uiModel.addAttribute("questionCnt", questionCnt);
            uiModel.addAttribute("surveyPage", surveyPage);

            if (tbSurveyQestnrPerson != null) {
                tbSurveyQestnrPersonMapper.updateCurrPgeNo(tbSurveyQestnrPerson.getSurveyPersonId(), order);
            }

            return "surveys/page";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Updates a survey page
     *
     * @param surveyPage
     * @param backAction
     * @param proceedAction
     * @param bindingResult
     * @param uiModel
     * @param principal
     * @param httpServletRequest
     * @return
     */
    @Secured({ "ROLE_ADMIN", "ROLE_SURVEY_ADMIN", "ROLE_SURVEY_PARTICIPANT" })
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String updateSurveyPage(@Valid SurveyPage surveyPage,
            @RequestParam(value = "_back", required = false) String backAction,
            @RequestParam(value = "_proceed", required = false) String proceedAction, BindingResult bindingResult,
            Model uiModel, HttpServletRequest httpServletRequest) {

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        try {
            Short order = surveyPage.getOrder();
            if (proceedAction != null) { // next button
                Survey survey = surveyService.survey_findById(surveyPage.getSurvey().getId());
                // Check if the user is authorized
                if (!survey.getLogin().equals(loginUser.getPersonId())) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + survey.getId()
                            + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                            + httpServletRequest.getLocalAddr());
                    return "accessDenied";

                }

                // Check that the survey was not submitted
                if (!(survey.getStatus().equals(SurveyStatus.I) || survey.getStatus().equals(SurveyStatus.R))) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE + survey.getId()
                            + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                            + httpServletRequest.getLocalAddr());
                    return "accessDenied";

                }

                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                surveyPage.setSurvey(survey);
                surveyPage = surveyService.surveyPage_updateSettings(surveyPage);

                // populate the uploaded files
                MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest) httpServletRequest;
                Iterator<String> fileNames = multiPartRequest.getFileNames();
                while (fileNames.hasNext()) {
                    String fileName = fileNames.next();
                    Long questionId = Long.parseLong(fileName.toUpperCase().replace("FILE", ""));
                    for (QuestionAnswer questionAnswer : surveyPage.getQuestionAnswers()) {
                        if (questionAnswer.getQuestion().getId().equals(questionId)
                                && multiPartRequest.getFile(fileName).getBytes().length > 0) {
                            questionAnswer.setSurveyDocument(new SurveyDocument(survey.getId(), questionId,
                                    multiPartRequest.getFile(fileName).getName(),
                                    multiPartRequest.getFile(fileName).getContentType(),
                                    multiPartRequest.getFile(fileName).getBytes()));
                        }
                    }
                }

                Policy policy = Policy.getInstance(this.getClass().getResource(POLICY_FILE_LOCATION));
                AntiSamy as = new AntiSamy();
                for (QuestionAnswer questionAnswer : surveyPage.getQuestionAnswers()) {
                    if (questionAnswer.getQuestion().getType().getIsTextInput()) {
                        CleanResults cr = as.scan(questionAnswer.getStringAnswerValue(), policy);
                        questionAnswer.setStringAnswerValue(cr.getCleanHTML());
                    }
                }

                GlobalSettings globalSettings = applicationSettingsService.getSettings();

                SurveyPageValidator validator = new SurveyPageValidator(
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()),
                        globalSettings.getValidContentTypes(), globalSettings.getValidImageTypes(),
                        globalSettings.getMaximunFileSize(), globalSettings.getInvalidContentMessage(),
                        globalSettings.getInvalidFileSizeMessage());
                validator.validate(surveyPage, bindingResult);
                if (bindingResult.hasErrors()) {
                    /*
                    for (ObjectError err :bindingResult.getAllErrors()) {
                        log.info("getObjectName:" + err.getObjectName() + " getCode:" + err.getCode() + " getDefaultMessage:" + err.getDefaultMessage());
                        log.info("toString:" + err.toString());
                    }
                    */

                    TbSurveyQestnr tbSurveyQestnr = tbSurveyQestnrMapper.selectBySurveyDefinitionId(survey.getTypeId());
                    TbSurveyQestnrCmmn tbSurveyQestnrCmmn = tbSurveyQestnrCmmnMapper.select(tbSurveyQestnr.getSurveyQestnrId());

                    uiModel.addAttribute("tbSurveyQestnr", tbSurveyQestnr);
                    uiModel.addAttribute("tbSurveyQestnrCmmn", tbSurveyQestnrCmmn);

                    uiModel.addAttribute("survey_base_path", "surveys/private");
                    uiModel.addAttribute("survey", survey);
                    uiModel.addAttribute("surveyPage", surveyPage);
                    uiModel.addAttribute("surveyDefinition",
                            surveySettingsService.surveyDefinition_findById(surveyPage.getSurvey().getTypeId()));
                    uiModel.addAttribute("surveyPages", surveyPages);

                    return "surveys/page";
                }

                surveyService.surveyPage_update(surveyPage,
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                // get the survey pages from the database again, prvious call updates visibility
                // when there is branching logic
                surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

                TbSurveyQestnrPerson tbSurveyQestnrPerson = tbSurveyQestnrPersonMapper.selectBySurveyId(survey.getId());

                if (tbSurveyQestnrPerson != null && tbSurveyQestnrPerson.getStrePgeNo() < order) {
                    tbSurveyQestnrPersonMapper.updateStrePgeNo(tbSurveyQestnrPerson.getSurveyPersonId(), order);
                }

                order = SurveyUtil.getNextPageOrder(surveyPages, order);

                if (order.equals((short) -1)) {
                    // Submit page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/private/submit/" + SurveyUtil
                            .encodeUrlPathSegment(surveyPage.getSurvey().getId().toString(), httpServletRequest);
                } else {
                    // go to the next page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/private/"
                            + SurveyUtil.encodeUrlPathSegment(surveyPage.getSurvey().getId().toString(),
                                    httpServletRequest)
                            + "/" + SurveyUtil.encodeUrlPathSegment(order.toString(), httpServletRequest);
                }

            } else {// back button
                Survey survey = surveyService.survey_findById(surveyPage.getSurvey().getId());
                // Check if the user is authorized
                if (!survey.getLogin().equals(loginUser.getPersonId())) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + survey.getId()
                            + REQUEST_PATH_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_USER_LOGIN_WARNING_MESSAGE + loginUser.getPersonId() + FROM_IP_WARNING_MESSAGE
                            + httpServletRequest.getLocalAddr());
                    return "accessDenied";

                }
                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                order = SurveyUtil.getPreviousPageOrder(surveyPages, order);
                if (order.equals((short) -1)) {
                    // Go to the surveyEntries page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/private/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getTypeId().toString(), httpServletRequest)
                            + "?list";
                } else {
                    // go to previous page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/private/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/"
                            + SurveyUtil.encodeUrlPathSegment(order.toString(), httpServletRequest);

                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }

    }

}
