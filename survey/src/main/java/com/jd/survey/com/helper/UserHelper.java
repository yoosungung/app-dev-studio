package com.jd.survey.com.helper;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jd.survey.domain.security.User;

/**
 * 회원 인증정보 유틸
 */
public class UserHelper {
	
	//~ Static fields/initializers =========================================================
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	//private static final String ROLE_SURVEY_ADMIN = "ROLE_SURVEY_ADMIN";
	
	private static final String ROLE_SURVEY_PARTICIPANT = "ROLE_SURVEY_PARTICIPANT";
	
	//~ Methods ============================================================================
	
	/**
	 * 인증토큰 조회
	 */
	private static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * 인증여부 검사
	 */
	private static boolean isAuthenticated() {
		if(getAuthentication() == null) return false;
		else return !(getAuthentication() instanceof AnonymousAuthenticationToken);
	}
	
	/**
	 * 로그인정보 조회
	 */
	public static User getUser() {
		if(isAuthenticated()) return (User) getAuthentication().getPrincipal();
		else return null;
	}
	
	/**
	 * 로그인 아이디 조회
	 */
	public static Long getUserId() {
		if(isAuthenticated()) return getUser().getId();
		else return null;
	}
	
	/**
	 * 로그인 사용자 동일여부 조회
	 */
	public static boolean isEqualUser(long id) {
		if(isAuthenticated()) return getUser().getId().equals(id);
		else return false;
	}
	
	/**
	 * 권한 검사
	 */
	public static boolean hasRole(String role) {
		if(isAuthenticated()) {
			for(GrantedAuthority authority : getAuthentication().getAuthorities()) {
				if(authority.getAuthority().equals(role)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 권한 검사 : 사용자
	 */
	public static Boolean isUser() {
		return hasRole(ROLE_SURVEY_PARTICIPANT) || hasRole(ROLE_ADMIN);
	}
	
	/**
	 * 권한 검사 : 관리자
	 */
	public static Boolean isAdmin() {
		return hasRole(ROLE_ADMIN);
	}
	
}
