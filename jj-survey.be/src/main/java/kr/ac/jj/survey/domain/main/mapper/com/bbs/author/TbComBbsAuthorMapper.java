package kr.ac.jj.survey.domain.main.mapper.com.bbs.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.com.bbs.author.TbComBbsAuthorToAuthor;

/**
 * 공통 - 게시판별 권한 Mapper
 */
@MainSqlMapper
public interface TbComBbsAuthorMapper extends TbComBbsAuthorEntityMapper {
    /**
     * 목록 조회 - BBS_ID(으)로
     *
     * @return
     */
    public List<TbComBbsAuthorToAuthor> selectListByBbsId2();

    /**
     * 목록 조회 - BBS_ID(으)로
     *
     * @param bbsId
     * @return
     */
    public List<TbComBbsAuthorToAuthor> selectListByBbsId2(@Param("bbsId") String bbsId);
}
