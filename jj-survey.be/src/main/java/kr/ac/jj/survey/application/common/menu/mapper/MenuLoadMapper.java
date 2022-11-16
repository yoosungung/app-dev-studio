package kr.ac.jj.survey.application.common.menu.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;

/**
 * 메뉴 로딩 Mapper
 */
@MainSqlMapper
public interface MenuLoadMapper {
    /**
     * 전체 목록 조회
     *
     * @param menuKnd
     * @param authorities
     * @return
     */
    public BaseMapList readAllList(@Param("menuKnd") String menuKnd,
            @Param("authorities") Collection<? extends GrantedAuthority> authorities);
}
