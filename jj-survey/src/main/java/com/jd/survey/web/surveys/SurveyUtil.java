package com.jd.survey.web.surveys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.jd.survey.domain.survey.SurveyPage;

public class SurveyUtil {

    private static final Logger log = LoggerFactory.getLogger(SurveyUtil.class);

    /**
     * Helper method that computes the next page
     *
     * @param survey
     * @param thisPageOrder
     * @return
     */
    public static Short getNextPageOrder(List<SurveyPage> surveyPages, Short thisPageOrder) {
        try {

            if (thisPageOrder.equals((short) surveyPages.size())) {
                return -1; // go to the submit page
            }
            // The next page is the first visible page after this order
            for (SurveyPage surveyPage : surveyPages) {
                if (surveyPage.getOrder() > thisPageOrder) {
                    if (surveyPage.getVisible()) {
                        log.info("getNextPageOrder=" + surveyPage.getOrder());
                        return surveyPage.getOrder();
                    }
                }
            }
            return 0; // go to the submit page

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    /**
     * Helper method that computes the previous page
     *
     * @param survey
     * @param thisPageOrder
     * @return
     */
    public static Short getPreviousPageOrder(List<SurveyPage> surveyPages, Short thisPageOrder) {
        try {
            if (thisPageOrder.equals((short) 0)) {
                return -1;
            }
            if (thisPageOrder.equals((short) 1)) {
                return 0;
            }
            // The previous page is the first visible page before this order
            for (int i = thisPageOrder - 1; i >= 0; i--) {
                SurveyPage surveyPage = surveyPages.get(i);
                if (surveyPage.getOrder() < thisPageOrder) {
                    if (surveyPage.getVisible()) {
                        log.info("getPreviousPageOrder=" + surveyPage.getOrder());
                        return surveyPage.getOrder();
                    }
                }
            }

            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        log.info("encodeUrlPathSegment()");
        try {
            String enc = httpServletRequest.getCharacterEncoding();
            if (enc == null) {
                enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
            }
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
            return pathSegment;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

}
