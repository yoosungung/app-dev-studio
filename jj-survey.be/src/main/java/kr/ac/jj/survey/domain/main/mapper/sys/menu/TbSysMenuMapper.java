package kr.ac.jj.survey.domain.main.mapper.sys.menu;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * 시스템 - 메뉴 Mapper
 */
@MainSqlMapper
public interface TbSysMenuMapper extends TbSysMenuEntityMapper {
    /**
     * 삭제 - 하위메뉴가 존재하지 않는 건
     *
     * @param menuId
     * @return
     */
    public int deleteNotExistsChildMenu(@Param("menuId") String menuId);
}
