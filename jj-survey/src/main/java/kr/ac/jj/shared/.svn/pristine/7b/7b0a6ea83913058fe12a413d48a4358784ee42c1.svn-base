package kr.ac.jj.shared.infrastructure.security.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.infrastructure.security.mapper.SecuredResourceMapper;
import kr.ac.jj.shared.infrastructure.security.model.SecuredResourceAuthor;
import kr.ac.jj.shared.infrastructure.security.model.SecuredRoleHierarchy;

@Service
public class SecuredResourceServiceImpl {

    private @Autowired SecuredResourceMapper securedResourceMapper;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getAuthorsAndUrl() {
        List<SecuredResourceAuthor> securedResourceAuthorList = securedResourceMapper.selectAuthorsAndUrlList("URL");
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();

        for (SecuredResourceAuthor securedResourceAuthor : securedResourceAuthorList) {
            AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(securedResourceAuthor.getResrcePttrn());
            List<ConfigAttribute> configList;

            if (requestMap.containsKey(requestMatcher)) {
                configList = requestMap.get(requestMatcher);
            } else {
                configList = new LinkedList<ConfigAttribute>();
                requestMap.put(requestMatcher, configList);
            }

            configList.add(new SecurityConfig(securedResourceAuthor.getAuthorCode()));
        }

        return requestMap;
    }

    public String getRoleHierarchyStringRepresentation() {
        List<SecuredRoleHierarchy> sysRoleHierarchyList = securedResourceMapper.selectRoleHierarchyList();
        List<String> hierarchyStringList = new ArrayList<String>();

        for (SecuredRoleHierarchy sysRoleHierarchy : sysRoleHierarchyList) {
            hierarchyStringList.add(sysRoleHierarchy.getHierarchyString());
        }

        return StringUtils.join(hierarchyStringList, "\n");
    }

}
