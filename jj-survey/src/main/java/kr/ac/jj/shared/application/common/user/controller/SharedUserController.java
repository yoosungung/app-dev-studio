package kr.ac.jj.shared.application.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.application.common.user.exception.InvalidSessionException;
import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestTypes;

/**
 * 사용자 Controller
 */
public class SharedUserController {

    private @Autowired SharedConfigProperties sharedConfig;

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
            throw new InvalidSessionException("Invalid Session!");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject("loginPath", request.getRequestURI());

        if (CommonUtil.isAdminLoginAvail(StringUtils.defaultString(request.getParameter("login")))) {
            mav.addObject("adminLoginPassword", sharedConfig.getLogin().getAdmin().getPassword());
            mav.addObject("adminLoginAvail", true);
        }

        String referer = request.getHeader("Referer");

        if (StringUtils.isNotEmpty(referer) && StringUtils.startsWith(referer, RequestContextUtil.getContextURL())) {
            mav.addObject("targetUrl", referer);
        }

        return mav;
    }

}
