package kr.ac.jj.survey.application.common.user.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.application.common.user.service.UserLoginSuccessAfterService;
import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user.JdSurveySecUserMapper;
import kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user.group.JdSurveySecUserGroupMapper;
import kr.ac.jj.survey.domain.main.model.jd.survey.sec.user.JdSurveySecUser;

/**
 * 로그인 성공 후처리 Service
 */
@Service
public class UserLoginSuccessAfterServiceImpl implements UserLoginSuccessAfterService {

    private @Autowired JdSurveySecUserMapper jdSurveySecUserMapper;
    private @Autowired JdSurveySecUserGroupMapper jdSurveySecUserGroupMapper;

    @Override
    public void execute() {
        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SURVEY_ADMIN")) {
            return;
        }

        LoginUser loginUser = CommonUtil.getLoginUser();

        if (StringUtils.equals(loginUser.getLoginTy(), "SURVEY")) {
            return;
        }

        if (loginUser.getTbComPerson() == null) {
            return;
        }

        String login = loginUser.getUsername();

        Long userId = jdSurveySecUserMapper.selectIdByLogin(login);

        JdSurveySecUser jdSurveySecUser = new JdSurveySecUser();
        jdSurveySecUser.setId(userId);
        jdSurveySecUser.setLogin(login);
        jdSurveySecUser.setPassword(loginUser.getUserId());
        jdSurveySecUser.setSalt(loginUser.getUserId());
        jdSurveySecUser.setFirstName(loginUser.getTbComPerson().getKoreanNm());
        jdSurveySecUser.setMiddleName(null);
        jdSurveySecUser.setLastName(null);
        jdSurveySecUser.setDateOfBirth(new Date());
        jdSurveySecUser.setEmail(loginUser.getTbComPerson().getEmailAdres());
        jdSurveySecUser.setUserType("I");
        jdSurveySecUser.setServiceType(null);
        jdSurveySecUser.setEnabled(1);
        jdSurveySecUser.setCreationDate(new Date());
        jdSurveySecUser.setLastUpdateDate(new Date());
        jdSurveySecUser.setVersion(0);

        if (userId == null) {
            jdSurveySecUserMapper.insert(jdSurveySecUser);
            userId = jdSurveySecUser.getId();
        } else {
            jdSurveySecUserMapper.update(jdSurveySecUser);
        }

        if (SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN")) {
            jdSurveySecUserGroupMapper.insertNotExists(userId, 1L);
        } else {
            jdSurveySecUserGroupMapper.delete(userId, 1L);
        }

        if (SecurityContextUtil.isUserReachableAuthority("ROLE_SURVEY_ADMIN")) {
            jdSurveySecUserGroupMapper.insertNotExists(userId, 2L);
        } else {
            jdSurveySecUserGroupMapper.delete(userId, 2L);
        }
    }

}
