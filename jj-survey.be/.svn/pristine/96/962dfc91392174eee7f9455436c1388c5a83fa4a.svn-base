package kr.ac.jj.survey.domain.main.mapper.com.bbs.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.com.bbs.author.TbComBbsAuthor;

/**
 * 공통 - 게시판별 권한 Entity Mapper
 */
abstract interface TbComBbsAuthorEntityMapper {
    /**
     * 목록 조회 - BBS_ID(으)로
     *
     * @param bbsId
     * @return
     */
    public List<TbComBbsAuthor> selectListByBbsId(@Param("bbsId") String bbsId);

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbComBbsAuthor> selectListByAuthorId(@Param("authorId") String authorId);

    /**
     * 조회
     *
     * @param bbsId
     * @param authorId
     * @return
     */
    public TbComBbsAuthor select(@Param("bbsId") String bbsId, @Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbComBbsAuthor
     * @return
     */
    public int insert(@Param("tbComBbsAuthor") TbComBbsAuthor tbComBbsAuthor);

    /**
     * 수정
     *
     * @param tbComBbsAuthor
     * @return
     */
    public int update(@Param("tbComBbsAuthor") TbComBbsAuthor tbComBbsAuthor);

    /**
     * 삭제
     *
     * @param bbsId
     * @param authorId
     * @return
     */
    public int delete(@Param("bbsId") String bbsId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - BBS_ID(으)로
     *
     * @param bbsId
     * @return
     */
    public int deleteListByBbsId(@Param("bbsId") String bbsId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);
}
