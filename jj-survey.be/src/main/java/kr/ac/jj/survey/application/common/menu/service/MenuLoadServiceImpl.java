package kr.ac.jj.survey.application.common.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.common.menu.mapper.MenuLoadMapper;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 메뉴 로딩 Service
 */
@Service
public class MenuLoadServiceImpl {
    private @Autowired MenuLoadMapper menuLoadMapper;

    /**
     * 전체 목록 조회
     *
     * @param menuKnd
     * @return
     */
    public BaseMapList readAllList(String menuKnd) {
        return menuLoadMapper.readAllList(menuKnd, SecurityContextUtil.getUserReachableAuthorities());
    }
}
