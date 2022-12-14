package kr.ac.jj.survey.application.common.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.application.common.user.controller.SharedUserController;

/**
 * 사용자 Controller
 */
@Controller
@RequestMapping(path = "/common/user")
public class UserController extends SharedUserController {

    /**
     * 로그인 화면
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/viewLogin.do")
    public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) {
        return super.viewLogin(request, response, "tiles2-login:/common/user/UserLoginView");
    }

}
