package kr.ac.jj.shared.domain.main.mapper.com.dty.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthor;

/**
 * 공통 - 직무별 권한 Entity Mapper
 */
abstract interface TbComDtyAuthorEntityMapper {

    /**
     * 목록 조회 - DTY_ID(으)로
     *
     * @param dtyId
     * @return
     */
    public List<TbComDtyAuthor> selectListByDtyId(@Param("dtyId") String dtyId);

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbComDtyAuthor> selectListByAuthorId(@Param("authorId") String authorId);

    /**
     * 조회
     *
     * @param dtyId
     * @param authorId
     * @return
     */
    public TbComDtyAuthor select(@Param("dtyId") String dtyId, @Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbComDtyAuthor
     * @return
     */
    public int insert(@Param("tbComDtyAuthor") TbComDtyAuthor tbComDtyAuthor);

    /**
     * 수정
     *
     * @param tbComDtyAuthor
     * @return
     */
    public int update(@Param("tbComDtyAuthor") TbComDtyAuthor tbComDtyAuthor);

    /**
     * 삭제
     *
     * @param dtyId
     * @param authorId
     * @return
     */
    public int delete(@Param("dtyId") String dtyId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - DTY_ID(으)로
     *
     * @param dtyId
     * @return
     */
    public int deleteListByDtyId(@Param("dtyId") String dtyId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);

}
