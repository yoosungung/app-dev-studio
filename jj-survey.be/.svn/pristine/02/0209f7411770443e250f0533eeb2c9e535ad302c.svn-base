package kr.ac.jj.survey.application.common.user.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.common.user.exception.UsernameEmptyValueException;
import kr.ac.jj.survey.application.common.user.mapper.UserMapper;
import kr.ac.jj.survey.application.common.user.model.LoginUser;
import kr.ac.jj.survey.config.props.ConfigProperties;
import kr.ac.jj.survey.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.survey.domain.main.mapper.com.person.user.TbComPersonUserMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.user.TbSysUserMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.user.loginfailr.TbSysUserLoginFailrMapper;
import kr.ac.jj.survey.domain.main.model.sys.log.login.TbSysLogLogin;
import kr.ac.jj.survey.domain.main.model.sys.user.TbSysUser;
import kr.ac.jj.survey.domain.main.model.sys.user.loginfailr.TbSysUserLoginFailr;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.survey.infrastructure.logging.service.LoggingServiceImpl;

/**
 * 사용자 로그인 Service
 */
@Service
public class UserLoginServiceImpl implements UserDetailsService {
    private @Autowired ConfigProperties config;
    private @Autowired UserMapper userMapper;
    private @Autowired TbSysUserMapper tbSysUserMapper;
    private @Autowired TbComPersonUserMapper tbComPersonUserMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;
    private @Autowired TbSysUserLoginFailrMapper tbSysUserLoginFailrMapper;
    private @Autowired LoggingServiceImpl loggingService;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        String loginTy = "COMMON";

        this.setTbSysLogLoginAttribute(username, loginTy);

        String userId = userMapper.selectUserIdByLoginNm(username);

        if (StringUtils.isEmpty(userId)) {
            if (!this.isAccountNonLocked(username)) {
                throw new LockedException(
                        MessageUtil.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "사용자 계정이 잠겼습니다."));
            }

            throw new UsernameNotFoundException(MessageUtil
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "로그인 정보가 일치하지 않습니다."));
        }

        return this.getLoginUser(userId, loginTy);
    }

    public LoginUser loadUserByAdmin(String username) {
        String loginTy = "ADMIN";

        this.setTbSysLogLoginAttribute(username, loginTy);

        String userId = userMapper.selectUserIdByLoginNm(username);

        if (StringUtils.isEmpty(userId)) {
            throw new BadCredentialsException(MessageUtil
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "로그인 정보가 일치하지 않습니다."));
        }

        return this.getLoginUser(userId, loginTy);
    }

    public LoginUser loadUserBySso(String username) {
        String loginTy = "SSO";

        this.setTbSysLogLoginAttribute(username, loginTy);

        if (StringUtils.isEmpty(username)) {
            throw new UsernameEmptyValueException("SSO Username is not found.");
        }

        String userId = userMapper.selectUserIdBySsoKey(username);

        if (StringUtils.isEmpty(userId)) {
            throw new UsernameNotFoundException(
                    "SSO User \"" + StringUtils.defaultIfEmpty(username, "") + "\" is not found.");
        }

        return this.getLoginUser(userId, loginTy);
    }

    private void setTbSysLogLoginAttribute(String loginNm, String loginTy) {
        HttpServletRequest request = RequestContextUtil.getRequest();

        TbSysLogLogin tbSysLogLogin = new TbSysLogLogin();
        tbSysLogLogin.setLoginNm(loginNm);
        tbSysLogLogin.setLoginTy(loginTy);

        request.setAttribute(TbSysLogLogin.class.getName(), tbSysLogLogin);
    }

    private TbSysLogLogin getTbSysLogLoginAttribute() {
        HttpServletRequest request = RequestContextUtil.getRequest();

        return (TbSysLogLogin) request.getAttribute(TbSysLogLogin.class.getName());
    }

    private LoginUser getLoginUser(String userId, String loginTy) {
        TbSysUser tbSysUser = tbSysUserMapper.select(userId);
        List<String> authorCodeList = userMapper.selectAuthorCodeList(userId);

        if (authorCodeList.isEmpty()) {
            authorCodeList.add(config.getLogin().getDefaultAuthorCode());
        }

        String username = tbSysUser.getLoginNm();
        String password = tbSysUser.getLoginPassword();
        boolean enabled = BooleanUtils.isTrue(tbSysUser.getUseYn());
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = this.isAccountNonLocked(tbSysUser.getLoginNm());
        String[] authorCodes = authorCodeList.toArray(new String[authorCodeList.size()]);
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authorCodes);

        LoginUser loginUser = new LoginUser(username, password, enabled //
                , accountNonExpired, credentialsNonExpired, accountNonLocked //
                , authorities //
                , userId, loginTy);

        return loginUser;
    }

    private boolean isAccountNonLocked(String loginNm) {
        TbSysUserLoginFailr tbSysUserLoginFailr = tbSysUserLoginFailrMapper.select(loginNm);

        return tbSysUserLoginFailr == null
                || tbSysUserLoginFailr.getFailrCo() < config.getLogin().getFail().getAvailCount();
    }

    public void updateLoginUser(LoginUser loginUser) {
        loginUser.setPersonId(tbComPersonUserMapper.selectPersonId(loginUser.getUserId()));

        if (StringUtils.isNotEmpty(loginUser.getPersonId())) {
            loginUser.setTbComPerson(tbComPersonMapper.select(loginUser.getPersonId()));
        }

        tbSysUserMapper.updateLoginDt(loginUser.getUserId());
    }

    public void updateLoginErrorCountToZero(String loginNm) {
        if (StringUtils.isEmpty(loginNm)) {
            return;
        }

        tbSysUserLoginFailrMapper.updateZero(loginNm);
    }

    public void updateLoginErrorCountAdd(String loginNm) {
        if (StringUtils.isEmpty(loginNm)) {
            return;
        }

        TbSysUserLoginFailr tbSysUserLoginFailr = new TbSysUserLoginFailr();
        tbSysUserLoginFailr.setLoginNm(loginNm);

        if (tbSysUserLoginFailrMapper.update(tbSysUserLoginFailr) == 0) {
            tbSysUserLoginFailrMapper.insert(tbSysUserLoginFailr);
        }
    }

    public void createLoginLog(boolean succesYn) {
        if (!config.getLog().getLogin().isEnable()) {
            return;
        }

        TbSysLogLogin tbSysLogLogin = this.getTbSysLogLoginAttribute();
        tbSysLogLogin.setSuccesYn(succesYn);

        loggingService.createLogin(tbSysLogLogin);
    }
}
