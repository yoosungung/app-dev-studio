package kr.ac.jj.survey.infrastructure.security.intercept;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import kr.ac.jj.survey.infrastructure.security.service.SecuredResourceServiceImpl;

@Component
public class ReloadableFilterInvocationSecurityMetadataSource
        implements FilterInvocationSecurityMetadataSource, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(ReloadableFilterInvocationSecurityMetadataSource.class);

    private @Autowired SecuredResourceServiceImpl securedResourceService;
    private @Autowired RoleHierarchyImpl roleHierarchy;

    private Map<RequestMatcher, List<ConfigAttribute>> requestMap;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> result = null;

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : this.requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                result = entry.getValue();
                break;
            }
        }

        return result;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : this.requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.requestMap = securedResourceService.getAuthorsAndUrl();
    }

    public void reloadAll() {
        this.reloadRequestMap();
        this.reloadHierarchy();
    }

    public void reloadRequestMap() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = securedResourceService.getAuthorsAndUrl();

        this.requestMap.clear();
        this.requestMap.putAll(requestMap);

        log.info("Secured URL Resources - Role Mappings reloaded at Runtime!");
    }

    public void reloadHierarchy() {
        this.roleHierarchy.setHierarchy(securedResourceService.getRoleHierarchyStringRepresentation());
    }
}
