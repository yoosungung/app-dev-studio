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

import java.io.StringWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jd.survey.GlobalSettings;
import com.jd.survey.dao.interfaces.survey.QuestionStatisticDAO;
import com.jd.survey.domain.settings.Question;
import com.jd.survey.domain.settings.QuestionOption;
import com.jd.survey.domain.settings.SurveyDefinition;
import com.jd.survey.domain.survey.QuestionAnswer;
import com.jd.survey.domain.survey.QuestionStatistic;
import com.jd.survey.domain.survey.Survey;
import com.jd.survey.domain.survey.SurveyDocument;
import com.jd.survey.domain.survey.SurveyPage;
import com.jd.survey.domain.survey.SurveyStatus;
import com.jd.survey.service.security.UserService;
import com.jd.survey.service.settings.ApplicationSettingsService;
import com.jd.survey.service.settings.SurveySettingsService;
import com.jd.survey.service.survey.SurveyService;

@RequestMapping({ "/surveys/public" })
@Controller
public class PublicSurveyController {

    private static final Log log = LogFactory.getLog(PublicSurveyController.class);
    private static final String DATE_FORMAT = "date_format";

    private static final String FROM_IP_WARNING_MESSAGE = " from IP Address:";
    private static final String SURVEY_NOT_PUBLIC_WARNING_MESSAGE = "Attempt to access private survey definition from the following public open url:";
    private static final String IP_ADDRESS_USED_ALREADY_WARNING_MESSAGE = "Attempt to create a new survey from a previously used IP address url:";
    private static final String UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE = "Attempt to access public survey from a different IP Address than the one used to create the survey. url:";
    private static final String UNAUTHORIZED_ATTEMPT_TO_EDIT_SUBMITTED_SURVEY_WARNING_MESSAGE = "Unauthorized Attempt to edit a submitted survey:";

    private static final String POLICY_FILE_LOCATION = "/antisamy-tinymce-1-4-4.xml";

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveySettingsService surveySettingsService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationSettingsService applicationSettingsService;

    @Autowired
    private QuestionStatisticDAO questionStatisticDAO;

    /**
     * Returns the survey logo image binary
     *
     * @param departmentId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/logo/{id}", produces = "text/html")
    public void getSurveyLogo(@PathVariable("id") Long surveyDefinitionId, Model uiModel, Principal principal,
            HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try {
            uiModel.asMap().clear();
            // Check if the user is authorized
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
            if (!surveyDefinition.getIsPublic()) {// survey definition not open to the public
                // attempt to access a private survey definition from a public open url
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                throw (new RuntimeException("Unauthorized access to logo"));
            } else {
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
     * Checks that the user does not have a pending survey entry otherwise creates a
     * new one
     *
     * @param surveyDefinitionId
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/{id}", params = "list", produces = "text/html")
    public String listSurveyEntries(@PathVariable("id") Long surveyDefinitionId, Model uiModel, Principal principal,
            HttpServletRequest httpServletRequest) {
        log.info("listSurveyEntries of type id=" + surveyDefinitionId);
        try {
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
            if (!surveyDefinition.getIsPublic()) {// survey definition not open to the public
                // attempt to access a private survey definition from a public open url
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            // Set<Survey> userSurveyEntries=
            // surveyService.survey_findUserEntriesByTypeIdAndIpAddress(surveyDefinitionId,httpServletRequest.getRemoteAddr());
            // if (surveyDefinition.getAllowMultipleSubmissions()) {//allow multiple
            // submissions of this survey from the same client IP Address
            // if (userSurveyEntries == null || userSurveyEntries.size() == 0) { //No User
            // entries for this survey, create a new one
            Survey survey = surveyService.survey_create(surveyDefinitionId, null, httpServletRequest.getRemoteAddr());
            return "redirect:/surveys/public/"
                    + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/0";

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

    @RequestMapping(value = "/{id}", params = "create", produces = "text/html")
    public String createSurvey(@PathVariable("id") Long surveyDefinitionId, Model uiModel, Principal principal,
            HttpServletRequest httpServletRequest) {
        log.info("create a new survey of type id=" + surveyDefinitionId);
        try {
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(surveyDefinitionId);
            if (!surveyDefinition.getIsPublic()) {// survey definition not open to the public
                // attempt to access a private survey definition from a public open url
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            Survey survey = surveyService.survey_create(surveyDefinitionId, null, httpServletRequest.getRemoteAddr());

            return "redirect:/surveys/public/"
                    + SurveyUtil.encodeUrlPathSegment(survey.getId().toString(), httpServletRequest) + "/1";

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
    @RequestMapping(value = "submit/{id}", produces = "text/html")
    public String prepareSubmitSurvey(@PathVariable("id") Long surveyId, Model uiModel,
            HttpServletRequest httpServletRequest) {
        log.info("Submit survey  id=" + surveyId);
        try {
            Survey survey = surveyService.survey_findById(surveyId);
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(survey.getTypeId());
            if (!surveyDefinition.getIsPublic()) {// survey definition not open to the public
                // attempt to access a private survey definition from a public open url
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            /*if (!survey.getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                //Attempt to access a survey from different IP Address
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }*/

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            uiModel.addAttribute("survey_base_path", "surveys/public");
            uiModel.addAttribute("survey", survey);
            uiModel.addAttribute("surveyDefinition",
                    surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
            uiModel.addAttribute("surveyPages", surveyPages);
            uiModel.addAttribute("order", surveyPages.size() + 1);
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
    @RequestMapping(value = "/submit", method = RequestMethod.POST, produces = "text/html")
    public String submitSurvey(@RequestParam(value = "id", required = true) Long surveyId,
            @RequestParam(value = "_submit", required = false) String proceedAction, Model uiModel,
            HttpServletRequest httpServletRequest) {

        log.info("submitPost(open): id= " + surveyId);

        try {
            if (proceedAction != null) { // submit button
                uiModel.asMap().clear();
                Survey survey = surveyService.survey_submit(surveyId);
                SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(survey.getTypeId());
                // survey definition not open to the public
                if (!surveyDefinition.getIsPublic()) {
                    // attempt to access a private survey definition from a public open url
                    log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                    return "accessDenied";
                }

                // Attempt to access a survey from different IP Address
                /*if (!survey.getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                    return "accessDenied";
                }*/

                if (surveyDefinition.getAllowMultipleSubmissions()) {
                    return "redirect:/surveys/public/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getTypeId().toString(), httpServletRequest)
                            + "?list";
                } else {
                    uiModel.asMap().clear();
                    StringWriter sw = new StringWriter();
                    VelocityContext velocityContext = new VelocityContext();
                    Velocity.evaluate(velocityContext, sw, "velocity-log",
                            surveyDefinition.getCompletedSurveyTemplate());
                    uiModel.addAttribute("completedMessage", sw.toString().trim());
                    return "surveys/submitted";
                }
            } else {
                uiModel.asMap().clear();
                Survey survey = surveyService.survey_findById(surveyId);
                SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(survey.getTypeId());
                // survey definition not open to the public
                if (!surveyDefinition.getIsPublic()) {
                    // attempt to access a private survey definition from a public open url
                    log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo()
                            + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                    return "accessDenied";
                }

                // Attempt to access a survey from different IP Address
                /*if (!survey.getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                    log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                    return "accessDenied";
                }*/
                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                Short order = (short) surveyPages.size();
                return "redirect:/surveys/public/"
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
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String showSurvey(@PathVariable("id") Long surveyId, Model uiModel, HttpServletRequest httpServletRequest) {
        log.info("showSurvey surveyId=" + surveyId + " no pageOrder");
        try {
            Survey survey = surveyService.survey_findById(surveyId);
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(survey.getTypeId());
            // survey definition not open to the public
            if (!surveyDefinition.getIsPublic()) {
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }

            // Attempt to access a survey from different IP Address
            /*if (!survey.getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }*/

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            if (survey.getStatus() == SurveyStatus.I) {
                return "redirect:/surveys/public/"
                        + SurveyUtil.encodeUrlPathSegment(surveyId.toString(), httpServletRequest) + "/0";
            } else {
                uiModel.addAttribute("survey_base_path", "surveys/public");
                uiModel.addAttribute("survey", survey);
                uiModel.addAttribute("surveyDefinition",
                        surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
                uiModel.addAttribute("surveyPages", surveyPages);
                return "surveys/survey";
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Prepares the edit survey page
     *
     * @param surveyId
     * @param order
     * @param uiModel
     * @param principal
     * @return
     */
    @RequestMapping(value = "/{id}/{po}", produces = "text/html")
    public String editSurveyPage(@PathVariable("id") Long surveyId, @PathVariable("po") Short order, Model uiModel,
            HttpServletRequest httpServletRequest) {
        log.info("editSurveyPage surveyId=" + surveyId + " pageOrder" + order);
        try {
            if (order == 0) {
                Map<String, Object> surveyPage = new LinkedHashMap<String, Object>();

                uiModel.addAttribute("surveyPage", surveyPage);

                return "surveys/intro";
            }

            SurveyPage surveyPage = surveyService.surveyPage_get(surveyId, order,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            SurveyDefinition surveyDefinition = surveySettingsService
                    .surveyDefinition_findById(surveyPage.getSurvey().getTypeId());

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

            // survey definition not open to the public
            if (!surveyDefinition.getIsPublic()) {
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }
            // Attempt to access a survey from different IP Address

            System.out.println("====================================================");
            System.out.println(surveyPage.getSurvey().getIpAddress() + " : " + httpServletRequest.getLocalAddr());

            /*if (!surveyPage.getSurvey().getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }*/

            List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyId,
                    messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
            uiModel.addAttribute("survey_base_path", "surveys/public");
            uiModel.addAttribute("survey", surveyPage.getSurvey());
            uiModel.addAttribute("surveyPage", surveyPage);
            uiModel.addAttribute("surveyDefinition",
                    surveySettingsService.surveyDefinition_findById(surveyPage.getSurvey().getTypeId()));
            uiModel.addAttribute("surveyPages", surveyPages);
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
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String updateSurveyPage(@Valid SurveyPage surveyPage,
            @RequestParam(value = "_back", required = false) String backAction,
            @RequestParam(value = "_proceed", required = false) String proceedAction, BindingResult bindingResult,
            Model uiModel, HttpServletRequest httpServletRequest) {
        try {

            Short order = surveyPage.getOrder();
            Survey survey = surveyService.survey_findById(surveyPage.getSurvey().getId());
            SurveyDefinition surveyDefinition = surveySettingsService.surveyDefinition_findById(survey.getTypeId());
            if (!surveyDefinition.getIsPublic()) {// survey definition not open to the public
                // attempt to access a private survey definition from a public open url
                log.warn(SURVEY_NOT_PUBLIC_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE
                        + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }
            // Attempt to access a survey from different IP Address
            /*if (!survey.getIpAddress().equalsIgnoreCase(httpServletRequest.getLocalAddr())) {
                log.warn(UNAUTHORIZED_ATTEMPT_TO_ACCESS_SURVEY_WARNING_MESSAGE + httpServletRequest.getPathInfo() + FROM_IP_WARNING_MESSAGE + httpServletRequest.getLocalAddr());
                return "accessDenied";
            }*/

            if (proceedAction != null) { // next button
                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));

                for (SurveyPage page : surveyPages) {

                    for (QuestionAnswer answer : page.getQuestionAnswers()) {
                        Question question = answer.getQuestion();
                        if (question.getDuplicate()) {
                            for (QuestionAnswer answer2 : surveyPage.getQuestionAnswers()) {
                                if (question.getOrder() == answer2.getQuestion().getOrder()) {
                                    List<QuestionStatistic> values = questionStatisticDAO.getValues(question);
                                    for (QuestionStatistic value : values) {
                                        if (answer2.getStringAnswerValue() != null) {
                                            if (answer2.getStringAnswerValue().trim().equals(value.getEntry().trim())) {
                                                // 설문 중복검사
                                                return "surveyAlreadyTaken";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                surveyPage.setOrder(surveyPages.get(surveyPage.getOrder() - 1).getOrder());
                surveyPage.setTitle(surveyPages.get(surveyPage.getOrder() - 1).getTitle());
                surveyPage.setInstructions(surveyPages.get(surveyPage.getOrder() - 1).getInstructions());
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
                                    multiPartRequest.getFile(fileName).getOriginalFilename(),
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
                    uiModel.addAttribute("survey_base_path", "surveys/public");
                    uiModel.addAttribute("survey", survey);
                    uiModel.addAttribute("surveyDefinition",
                            surveySettingsService.surveyDefinition_findById(survey.getTypeId()));
                    uiModel.addAttribute("surveyPage", surveyPage);
                    uiModel.addAttribute("surveyPages", surveyPages);
                    return "surveys/page";
                }

                surveyService.surveyPage_update(surveyPage,
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                // get the survey pages from the database again, prvious call updates visibility
                // when there is branching logic
                surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                order = SurveyUtil.getNextPageOrder(surveyPages, order);
                if (order.equals((short) 0)) {
                    // Submit page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/public/submit/" + SurveyUtil
                            .encodeUrlPathSegment(surveyPage.getSurvey().getId().toString(), httpServletRequest);
                } else {
                    // go to the next page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/public/"
                            + SurveyUtil.encodeUrlPathSegment(surveyPage.getSurvey().getId().toString(),
                                    httpServletRequest)
                            + "/" + SurveyUtil.encodeUrlPathSegment(order.toString(), httpServletRequest);
                }

            } else {// back button
                List<SurveyPage> surveyPages = surveyService.surveyPage_getAll(surveyPage.getSurvey().getId(),
                        messageSource.getMessage(DATE_FORMAT, null, LocaleContextHolder.getLocale()));
                order = SurveyUtil.getPreviousPageOrder(surveyPages, order);
                if (order.equals((short) 0)) {
                    // Go to the surveyEntries page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/public/"
                            + SurveyUtil.encodeUrlPathSegment(survey.getTypeId().toString(), httpServletRequest)
                            + "?list";
                } else {
                    // go to previous page
                    uiModel.asMap().clear();
                    return "redirect:/surveys/public/"
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
