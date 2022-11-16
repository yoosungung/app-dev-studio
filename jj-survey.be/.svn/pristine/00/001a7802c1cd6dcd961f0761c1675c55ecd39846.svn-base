package kr.ac.jj.survey.infrastructure.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 기본 로그인 사용자
 */
public abstract class BaseLoginUser extends User {
    private static final long serialVersionUID = 4759931757816853698L;

    private final String userId;

    public BaseLoginUser(String username, String password, boolean enabled //
            , boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked //
            , Collection<? extends GrantedAuthority> authorities //
            , String userId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
}
