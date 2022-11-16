package kr.ac.jj.survey.domain.main.mapper.sys.log.menu;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.log.menu.TbSysLogMenu;

/**
 * 시스템 - 메뉴 로그 Entity Mapper
 */
abstract interface TbSysLogMenuEntityMapper {
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
}
