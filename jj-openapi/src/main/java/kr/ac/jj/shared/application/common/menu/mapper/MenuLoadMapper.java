package kr.ac.jj.shared.application.common.menu.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;

/**
 * 메뉴 로딩 Mapper
 */
@SharedMainSqlMapper
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

    /**
     * 권한 건수 조회
     *
     * @param menuId
     * @param authorities
     * @return
     */
    public int selectAuthCount(@Param("menuId") String menuId,
            @Param("authorities") Collection<? extends GrantedAuthority> authorities);

    /**
     * 메뉴 ID 조회 - By 메뉴 경로
     *
     * @param menuPath
     * @return
     */
    public String[] selectMenuIdsByMenuPath(@Param("menuPath") String menuPath);

}
