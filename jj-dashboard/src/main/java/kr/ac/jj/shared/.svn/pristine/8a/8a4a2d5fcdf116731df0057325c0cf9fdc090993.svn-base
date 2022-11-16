package kr.ac.jj.shared.application.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.application.common.user.exception.InvalidSessionException;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestTypes;

/**
 * 사용자 Controller
 */
public class SharedUserController {

    /**
     * 로그인 화면
     *
     * @param request
     * @param response
     * @param viewName
     * @return
     */
    public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response, String viewName) {
        if (RequestContextUtil.getRequestType() == RequestTypes.AJAX) {
            response.setHeader("X-Invalid-Session", Boolean.toString(true));
            throw new InvalidSessionException("Invalid Session!");
        }

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl;

        if (savedRequest == null) {
            String referer = request.getHeader("Referer");

            if (StringUtils.isEmpty(referer)) {
                targetUrl = request.getContextPath() + "/";
            } else {
                targetUrl = referer;
            }
        } else {
            targetUrl = savedRequest.getRedirectUrl();
        }

        if (targetUrl == null || targetUrl.startsWith(request.getRequestURL().toString())) {
            targetUrl = request.getContextPath() + "/";
        }

        requestCache.removeRequest(request, response);

        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject("targetUrl", targetUrl);

        return mav;
    }

}
