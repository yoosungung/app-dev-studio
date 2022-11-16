package kr.ac.jj.shared.domain.main.mapper.sys.menu;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;

/**
 * 시스템 - 메뉴 Entity Mapper
 */
abstract interface TbSysMenuEntityMapper {

    /**
     * 조회
     *
     * @param menuId
     * @return
     */
    public TbSysMenu select(@Param("menuId") String menuId);

    /**
     * 생성
     *
     * @param tbSysMenu
     * @return
     */
    public int insert(@Param("tbSysMenu") TbSysMenu tbSysMenu);

    /**
     * 수정
     *
     * @param tbSysMenu
     * @return
     */
    public int update(@Param("tbSysMenu") TbSysMenu tbSysMenu);

    /**
     * 삭제
     *
     * @param menuId
     * @return
     */
    public int delete(@Param("menuId") String menuId);

}
