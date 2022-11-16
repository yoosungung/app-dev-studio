package kr.ac.jj.shared.domain.main.mapper.sys.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;

/**
 * 시스템 - 메뉴 Mapper
 */
@SharedMainSqlMapper
public interface TbSysMenuMapper extends TbSysMenuEntityMapper {

    /**
     * 하위 메뉴 ID 목록 조회
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public List<String> selectChildMenuIdList(@Param("menuKnd") String menuKnd,
            @Param("upperMenuId") String upperMenuId);

    /**
     * 수정 - 하위 레벨
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public int updateChildLevel(@Param("menuKnd") String menuKnd, @Param("upperMenuId") String upperMenuId);

    /**
     * 수정 - 메뉴 순서
     *
     * @param tbSysMenu
     * @return
     */
    public int updateMenuOrdr(@Param("tbSysMenu") TbSysMenu tbSysMenu);

    /**
     * 삭제 - 하위메뉴가 존재하지 않는 건
     *
     * @param menuId
     * @return
     */
    public int deleteNotExistsChildMenu(@Param("menuId") String menuId);

}
