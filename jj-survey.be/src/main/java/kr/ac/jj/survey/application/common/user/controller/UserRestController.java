package kr.ac.jj.survey.application.common.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.survey.application.common.user.model.UserModel;

/**
 * 사용자 RestController
 */
@RestController
@RequestMapping(path = "/common/user", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class UserRestController {
    /**
     * 사용자 조회
     *
     * @return
     */
    @GetMapping(path = "/readUser.do")
    public UserModel readUser(HttpServletRequest request) {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);

        UserModel userModel = new UserModel();
        userModel.setCsrfToken(csrfToken);

        return userModel;
    }
}
