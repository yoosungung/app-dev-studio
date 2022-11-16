package kr.ac.jj.shared.domain.main.mapper.sys.menu.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;

/**
 * 시스템 - 메뉴별 권한 Mapper
 */
@SharedSqlMapper
public interface TbSysMenuAuthorMapper extends TbSysMenuAuthorEntityMapper {

    /**
     * 목록 조회 - AUTHOR_ID(으)로2
     *
     * @param authorId
     * @param menuKnd
     * @return
     */
    public List<TbSysMenuAuthorToMenu> selectListByAuthorId2(@Param("authorId") String authorId,
            @Param("menuKnd") String menuKnd);

}
