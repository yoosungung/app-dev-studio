package kr.ac.jj.shared.infrastructure.framework.web.security.util;

import java.util.Collection;
import java.util.List;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.util.ServletUtil;

public class SecurityContextUtil {

    private static RoleHierarchy roleHierarchy;

    private static RoleHierarchy getRoleHierarchy() {
        if (SecurityContextUtil.roleHierarchy == null) {
            SecurityContextUtil.roleHierarchy = ApplicationContextUtil.getBean(RoleHierarchy.class);
        }

        return SecurityContextUtil.roleHierarchy;
    }

    public static Collection<? extends GrantedAuthority> getUserAuthorities() {
        UserDetails userDetails = ServletUtil.getAttribute().getLoginUser();

        if (userDetails == null) {
            return null;
        }

        return userDetails.getAuthorities();
    }

    public static Collection<? extends GrantedAuthority> getUserReachableAuthorities() {
        RoleHierarchy roleHierarchy = getRoleHierarchy();

        if (roleHierarchy == null) {
            return null;
        }

        Collection<? extends GrantedAuthority> authorities = getUserAuthorities();

        if (authorities == null) {
            return null;
        }

        return roleHierarchy.getReachableGrantedAuthorities(authorities);
    }

    public static boolean isUserReachableAuthority(String... authorityCodes) {
        if (authorityCodes == null || authorityCodes.length == 0) {
            return false;
        }

        Collection<? extends GrantedAuthority> userAuthorities = getUserReachableAuthorities();

        if (userAuthorities == null) {
            return false;
        }

        for (GrantedAuthority grantedAuthority : userAuthorities) {
            for (String authorityCode : authorityCodes) {
                if (authorityCode.equals(grantedAuthority.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Collection<? extends GrantedAuthority> getReachableAuthorities(String... authorityCodes) {
        RoleHierarchy roleHierarchy = getRoleHierarchy();

        if (roleHierarchy == null) {
            return null;
        }

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(authorityCodes);

        return roleHierarchy.getReachableGrantedAuthorities(authorityList);
    }

}
