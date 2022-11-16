package kr.ac.jj.shared.domain.main.mapper.sys.menu.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthor;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToAuthor;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;

/**
 * 시스템 - 메뉴별 권한 Entity Mapper
 */
abstract interface TbSysMenuAuthorEntityMapper {

    /**
     * 목록 조회 - MENU_ID(으)로
     *
     * @param menuId
     * @return
     */
    public List<TbSysMenuAuthorToAuthor> selectListByMenuId(@Param("menuId") String menuId);

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbSysMenuAuthorToMenu> selectListByAuthorId(@Param("authorId") String authorId);

    /**
     * 조회
     *
     * @param menuId
     * @param authorId
     * @return
     */
    public TbSysMenuAuthor select(@Param("menuId") String menuId, @Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbSysMenuAuthor
     * @return
     */
    public int insert(@Param("tbSysMenuAuthor") TbSysMenuAuthor tbSysMenuAuthor);

    /**
     * 수정
     *
     * @param tbSysMenuAuthor
     * @return
     */
    public int update(@Param("tbSysMenuAuthor") TbSysMenuAuthor tbSysMenuAuthor);

    /**
     * 삭제
     *
     * @param menuId
     * @param authorId
     * @return
     */
    public int delete(@Param("menuId") String menuId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - MENU_ID(으)로
     *
     * @param menuId
     * @return
     */
    public int deleteListByMenuId(@Param("menuId") String menuId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);

}
