package kr.ac.jj.survey.domain.main.mapper.sys.author;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * 시스템 - 권한 Mapper
 */
@MainSqlMapper
public interface TbSysAuthorMapper extends TbSysAuthorEntityMapper {
    /**
     * 수정 - 정렬 순서
     *
     * @param authorId
     * @param sortOrdr
     * @return
     */
    public int updateSortOrdr(@Param("authorId") String authorId, @Param("sortOrdr") Integer sortOrdr);
}
