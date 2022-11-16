package kr.ac.jj.shared.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import kr.ac.jj.shared.application.common.user.handler.UserAccessDeniedHandler;
import kr.ac.jj.shared.application.common.user.handler.UserLoginFailureHandler;
import kr.ac.jj.shared.application.common.user.handler.UserLoginSuccessHandler;
import kr.ac.jj.shared.application.common.user.handler.UserLogoutSuccessHandler;
import kr.ac.jj.shared.application.common.user.provider.UserLoginProvider;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.security.intercept.ReloadableFilterInvocationSecurityMetadataSource;
import kr.ac.jj.shared.infrastructure.security.service.SecuredResourceServiceImpl;

@Lazy
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SharedSecurityConfig extends WebSecurityConfigurerAdapter {

    private @Autowired ReloadableFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private @Autowired SecuredResourceServiceImpl securedResourceService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        http.csrf().csrfTokenRepository(csrfTokenRepository);

        http.exceptionHandling().accessDeniedHandler(new UserAccessDeniedHandler());

        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());

        List<AccessDecisionVoter<? extends Object>> accessDecisionVoterList = new ArrayList<AccessDecisionVoter<? extends Object>>();
        accessDecisionVoterList.add(roleHierarchyVoter);

        AffirmativeBased accessDecisionManager = new AffirmativeBased(accessDecisionVoterList);

        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);

        try {
            filterSecurityInterceptor.setAuthenticationManager(super.authenticationManagerBean());
        } catch (Exception e) {
            throw new BaseException(e);
        }

        http.addFilterAt(filterSecurityInterceptor, FilterSecurityInterceptor.class);

        http.formLogin().loginPage("/common/user/viewLogin.do") //
                .loginProcessingUrl("/common/user/login.do") //
                .successHandler(new UserLoginSuccessHandler()) //
                .failureHandler(new UserLoginFailureHandler()) //
                .usernameParameter("loginNm") //
                .passwordParameter("loginPassword");

        http.logout().logoutUrl("/common/user/logout.do") //
                .logoutSuccessHandler(new UserLogoutSuccessHandler());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/error", "/static/**") //
                .antMatchers("/**/*.jsp", "/**/*.js", "/**/*.css", "/**/*.html", "/**/*.htm") //
                .antMatchers("/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/**/*.png") //
                .antMatchers("/**/*.woff", "/**/*.eot");

        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());

        web.expressionHandler(defaultWebSecurityExpressionHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(securedResourceService.getRoleHierarchyStringRepresentation());

        return roleHierarchy;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        return new UserLoginProvider();
    }

}
