package kr.ac.jj.survey.application.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.survey.application.common.user.exception.InvalidSessionException;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestTypes;

/**
 * 사용자 Controller
 */
@Controller
@RequestMapping(path = "/common/user")
public class UserController {
    /**
     * 로그인 화면
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(path = "/viewLogin.do")
    public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) {
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
        mav.setViewName("common/user/UserLogin");
        mav.addObject("targetUrl", targetUrl);

        return mav;
    }
}
