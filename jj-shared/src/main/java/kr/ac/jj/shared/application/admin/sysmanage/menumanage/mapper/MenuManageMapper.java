package kr.ac.jj.shared.application.admin.sysmanage.menumanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;

/**
 * 메뉴 관리 Mapper
 */
@SharedSqlMapper
public interface MenuManageMapper {

    /**
     * 트리 조회
     *
     * @param menuKnd
     * @return
     */
    public List<Map<String, Object>> selectTree(@Param("menuKnd") String menuKnd);

    /**
     * 목록 조회
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public List<Map<String, Object>> selectList(@Param("menuKnd") String menuKnd,
            @Param("upperMenuId") String upperMenuId);

    /**
     * 순서 저장
     *
     * @param menuManageOrdr
     * @return
     */
    public int updateOrdr(@Param("menuManageOrdr") BaseMap menuManageOrdr);

    /**
     * 하위 목록 조회
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public List<TbSysMenu> selectSubList(@Param("menuKnd") String menuKnd, @Param("upperMenuId") String upperMenuId);

    /**
     * 하위 레벨 수정
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public int updateSubLevel(@Param("menuKnd") String menuKnd, @Param("upperMenuId") String upperMenuId);

}
