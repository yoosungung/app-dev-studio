package kr.ac.jj.shared.domain.main.mapper.sys.menu;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;

/**
 * 시스템 - 메뉴 Mapper
 */
@SharedSqlMapper
public interface TbSysMenuMapper extends TbSysMenuEntityMapper {

    /**
     * 삭제 - 하위메뉴가 존재하지 않는 건
     *
     * @param menuId
     * @return
     */
    public int deleteNotExistsChildMenu(@Param("menuId") String menuId);

}
