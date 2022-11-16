package kr.ac.jj.shared.domain.main.mapper.sys.author.relatecode;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 시스템 - 권한별 관련 코드 Mapper
 */
@SharedMainSqlMapper
public interface TbSysAuthorRelateCodeMapper extends TbSysAuthorRelateCodeEntityMapper {

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);

}
