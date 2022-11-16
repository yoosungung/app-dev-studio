package kr.ac.jj.shared.domain.main.mapper.sys.resrce.author;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthor;

/**
 * 시스템 - 리소스별 권한 Entity Mapper
 */
abstract interface TbSysResrceAuthorEntityMapper {

    /**
     * 조회
     *
     * @param resrceId
     * @param authorId
     * @return
     */
    public TbSysResrceAuthor select(@Param("resrceId") String resrceId, @Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbSysResrceAuthor
     * @return
     */
    public int insert(@Param("tbSysResrceAuthor") TbSysResrceAuthor tbSysResrceAuthor);

    /**
     * 수정
     *
     * @param tbSysResrceAuthor
     * @return
     */
    public int update(@Param("tbSysResrceAuthor") TbSysResrceAuthor tbSysResrceAuthor);

    /**
     * 삭제
     *
     * @param resrceId
     * @param authorId
     * @return
     */
    public int delete(@Param("resrceId") String resrceId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - RESRCE_ID(으)로
     *
     * @param resrceId
     * @return
     */
    public int deleteListByResrceId(@Param("resrceId") String resrceId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);

}
