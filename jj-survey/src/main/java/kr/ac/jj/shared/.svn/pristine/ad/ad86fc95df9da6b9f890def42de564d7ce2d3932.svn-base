package kr.ac.jj.shared.application.common.user.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.infrastructure.user.BaseLoginUser;

/**
 * 로그인 사용자
 */
public class LoginUser extends BaseLoginUser {

    private static final long serialVersionUID = -2628246581532147229L;

    private final String loginTy;

    private String personId;
    private TbComPerson tbComPerson;

    public LoginUser(String username, String password, boolean enabled //
            , boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked //
            , Collection<? extends GrantedAuthority> authorities //
            , String userId, String loginTy) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities,
                userId);

        this.loginTy = loginTy;
    }

    public String getLoginTy() {
        return this.loginTy;
    }

    @Override
    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public TbComPerson getTbComPerson() {
        return this.tbComPerson;
    }

    public void setTbComPerson(TbComPerson tbComPerson) {
        this.tbComPerson = tbComPerson;
    }

    public String getPersonNm() {
        if (this.tbComPerson != null) {
            return this.tbComPerson.getPersonNm();
        }

        return this.getUsername();
    }

}
