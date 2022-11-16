package kr.ac.jj.shared.domain.main.mapper.bbs.faq;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 게시판 - FAQ Mapper
 */
@SharedMainSqlMapper
public interface TbBbsFaqMapper extends TbBbsFaqEntityMapper {

    /**
     * 수정 - 조회수 증가
     *
     * @param bbscttId
     * @return
     */
    public int updateRdcnt(@Param("bbscttId") String bbscttId);

    /**
     * 수정 - 삭제 여부
     *
     * @param bbscttId
     * @param deleteYn
     * @return
     */
    public int updateDeleteYn(@Param("bbscttId") String bbscttId, @Param("deleteYn") boolean deleteYn);

}