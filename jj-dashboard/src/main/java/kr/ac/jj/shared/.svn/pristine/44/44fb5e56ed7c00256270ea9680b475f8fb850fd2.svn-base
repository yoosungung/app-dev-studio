package kr.ac.jj.shared.domain.main.mapper.com.dept.author;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dept.author.TbComDeptAuthor;

/**
 * 공통 - 부서별 권한 Entity Mapper
 */
abstract interface TbComDeptAuthorEntityMapper {

    /**
     * 조회
     *
     * @param deptId
     * @param authorId
     * @return
     */
    public TbComDeptAuthor select(@Param("deptId") String deptId, @Param("authorId") String authorId);

    /**
     * 생성
     *
     * @param tbComDeptAuthor
     * @return
     */
    public int insert(@Param("tbComDeptAuthor") TbComDeptAuthor tbComDeptAuthor);

    /**
     * 수정
     *
     * @param tbComDeptAuthor
     * @return
     */
    public int update(@Param("tbComDeptAuthor") TbComDeptAuthor tbComDeptAuthor);

    /**
     * 삭제
     *
     * @param deptId
     * @param authorId
     * @return
     */
    public int delete(@Param("deptId") String deptId, @Param("authorId") String authorId);

    /**
     * 목록 삭제 - DEPT_ID(으)로
     *
     * @param deptId
     * @return
     */
    public int deleteListByDeptId(@Param("deptId") String deptId);

    /**
     * 목록 삭제 - AUTHOR_ID(으)로
     *
     * @param authorId
     * @return
     */
    public int deleteListByAuthorId(@Param("authorId") String authorId);

}
