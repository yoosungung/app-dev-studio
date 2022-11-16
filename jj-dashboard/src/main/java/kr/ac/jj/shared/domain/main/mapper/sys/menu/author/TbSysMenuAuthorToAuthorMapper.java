package kr.ac.jj.shared.domain.main.mapper.sys.menu.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToAuthor;

/**
 * 시스템 - 메뉴별 권한 TO 시스템 - 권한 Mapper
 */
@SharedMainSqlMapper
public interface TbSysMenuAuthorToAuthorMapper {

    /**
     * 목록 조회 - MENU_ID(으)로
     *
     * @param menuId
     * @return
     */
    public List<TbSysMenuAuthorToAuthor> selectListByMenuId(@Param("menuId") String menuId);

}
