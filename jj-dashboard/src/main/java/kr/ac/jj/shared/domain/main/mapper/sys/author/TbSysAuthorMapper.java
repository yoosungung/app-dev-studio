package kr.ac.jj.shared.domain.main.mapper.sys.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;

/**
 * 시스템 - 권한 Mapper
 */
@SharedMainSqlMapper
public interface TbSysAuthorMapper extends TbSysAuthorEntityMapper {

    /**
     * 조회 - MAX 정렬 순서
     *
     * @return
     */
    public int selectMaxSortOrdr();

    /**
     * 조회 - 정렬 순서 목록
     *
     * @return
     */
    public List<TbSysAuthor> selectSortOrdrList();

    /**
     * 수정 - 정렬 순서
     *
     * @param tbSysAuthor
     * @return
     */
    public int updateSortOrdr(@Param("tbSysAuthor") TbSysAuthor tbSysAuthor);

}
