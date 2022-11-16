package kr.ac.jj.shared.domain.main.mapper.sys.resrce.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;

/**
 * 시스템 - 리소스별 권한 TO 시스템 - 리소스 Mapper
 */
@SharedMainSqlMapper
public interface TbSysResrceAuthorToResrceMapper {

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbSysResrceAuthorToResrce> selectListByAuthorId(@Param("authorId") String authorId);

}
