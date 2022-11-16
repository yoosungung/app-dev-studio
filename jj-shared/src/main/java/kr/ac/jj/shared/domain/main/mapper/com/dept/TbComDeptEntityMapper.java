package kr.ac.jj.shared.domain.main.mapper.com.dept;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;

/**
 * 공통 - 부서 Entity Mapper
 */
abstract interface TbComDeptEntityMapper {

    /**
     * 조회
     *
     * @param deptId
     * @return
     */
    public TbComDept select(@Param("deptId") String deptId);

    /**
     * 생성
     *
     * @param tbComDept
     * @return
     */
    public int insert(@Param("tbComDept") TbComDept tbComDept);

    /**
     * 수정
     *
     * @param tbComDept
     * @return
     */
    public int update(@Param("tbComDept") TbComDept tbComDept);

    /**
     * 삭제
     *
     * @param deptId
     * @return
     */
    public int delete(@Param("deptId") String deptId);

}
