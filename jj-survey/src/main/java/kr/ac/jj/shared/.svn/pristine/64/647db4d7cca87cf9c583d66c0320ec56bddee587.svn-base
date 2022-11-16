package kr.ac.jj.shared.domain.main.mapper.com.dty.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthorToDty;

/**
 * 공통 - 직무별 권한 TO 공통 - 직무 Mapper
 */
@SharedMainSqlMapper
public interface TbComDtyAuthorToDtyMapper {

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbComDtyAuthorToDty> selectListByAuthorId(@Param("authorId") String authorId);

}
