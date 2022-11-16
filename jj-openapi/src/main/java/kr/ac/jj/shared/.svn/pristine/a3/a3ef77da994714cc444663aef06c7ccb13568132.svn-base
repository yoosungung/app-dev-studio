package kr.ac.jj.shared.domain.main.mapper.sys.resrce.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthor;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToAuthor;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;

/**
 * 시스템 - 리소스별 권한 Entity Mapper
 */
abstract interface TbSysResrceAuthorEntityMapper {

    /**
     * 목록 조회 - RESRCE_ID(으)로
     *
     * @param resrceId
     * @return
     */
    public List<TbSysResrceAuthorToAuthor> selectListByResrceId(@Param("resrceId") String resrceId);

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbSysResrceAuthorToResrce> selectListByAuthorId(@Param("authorId") String authorId);

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
