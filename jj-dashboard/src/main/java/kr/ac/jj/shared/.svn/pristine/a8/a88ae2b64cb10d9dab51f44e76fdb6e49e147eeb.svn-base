package kr.ac.jj.shared.domain.main.mapper.sys.author;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;

/**
 * 시스템 - 권한 Entity Mapper
 */
abstract interface TbSysAuthorEntityMapper {

    /**
     * 조회
     *
     * @param authorId
     * @return
     */
    public TbSysAuthor select(@Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbSysAuthor
     * @return
     */
    public int insert(@Param("tbSysAuthor") TbSysAuthor tbSysAuthor);

    /**
     * 수정
     *
     * @param tbSysAuthor
     * @return
     */
    public int update(@Param("tbSysAuthor") TbSysAuthor tbSysAuthor);

    /**
     * 삭제
     *
     * @param authorId
     * @return
     */
    public int delete(@Param("authorId") String authorId);

}
