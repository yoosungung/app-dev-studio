package kr.ac.jj.survey.domain.main.mapper.sys.user.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthor;
import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthorToAuthor;
import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthorToUser;

/**
 * 시스템 - 사용자별 권한 Entity Mapper
 */
abstract interface TbSysUserAuthorEntityMapper {
    /**
     * 목록 조회 - USER_ID(으)로
     *
     * @param userId
     * @return
     */
    public List<TbSysUserAuthorToAuthor> selectListByUserId(@Param("userId") String userId);

    /**
     * 목록 조회 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public List<TbSysUserAuthorToUser> selectListByAuthorId(@Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbSysUserAuthor
     * @return
     */
    public int insert(@Param("tbSysUserAuthor") TbSysUserAuthor tbSysUserAuthor);

    /**
     * 삭제
     *
     * @param userId
     * @param authorId
     * @return
     */
    public int delete(@Param("userId") String userId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - USER_ID(으)로
     *
     * @param userId
     * @return
     */
    public int deleteListByUserId(@Param("userId") String userId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);
}
