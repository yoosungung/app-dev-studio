package kr.ac.jj.shared.domain.main.mapper.sys.log.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.log.menu.TbSysLogMenu;

/**
 * 시스템 - 메뉴 로그 Entity Mapper
 */
abstract interface TbSysLogMenuEntityMapper {

    /**
     * 목록 조회 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public List<TbSysLogMenu> selectListByLogId(@Param("logId") String logId);

    /**
     * 목록 조회 - MENU_ID(으)로
     *
     * @param menuId
     * @return
     */
    public List<TbSysLogMenu> selectListByMenuId(@Param("menuId") String menuId);

    /**
     * 조회
     *
     * @param logId
     * @param menuId
     * @return
     */
    public TbSysLogMenu select(@Param("logId") String logId, @Param("menuId") String menuId);

    /**
     * 생성
     *
     * @param tbSysLogMenu
     * @return
     */
    public int insert(@Param("tbSysLogMenu") TbSysLogMenu tbSysLogMenu);

    /**
     * 수정
     *
     * @param tbSysLogMenu
     * @return
     */
    public int update(@Param("tbSysLogMenu") TbSysLogMenu tbSysLogMenu);

    /**
     * 삭제
     *
     * @param logId
     * @param menuId
     * @return
     */
    public int delete(@Param("logId") String logId, @Param("menuId") String menuId);

    /**
     * 목록 삭제 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public int deleteListByLogId(@Param("logId") String logId);

    /**
     * 목록 삭제 - MENU_ID(으)로
     *
     * @param menuId
     * @return
     */
    public int deleteListByMenuId(@Param("menuId") String menuId);

}
