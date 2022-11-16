package kr.ac.jj.shared.domain.main.mapper.com.dept;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;

/**
 * 공통 - 부서 Entity Mapper
 */
abstract interface TbComDeptEntityMapper {

    /**
     * 목록 조회 - UPPER_DEPT_ID(으)로
     *
     * @param deptSe
     * @param upperDeptId
     * @return
     */
    public List<TbComDept> selectListByUpperDeptId(@Param("deptSe") String deptSe,
            @Param("upperDeptId") String upperDeptId);

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

    /**
     * 목록 삭제 - UPPER_DEPT_ID(으)로
     *
     * @param deptSe
     * @param upperDeptId
     * @return
     */
    public int deleteListByUpperDeptId(@Param("deptSe") String deptSe, @Param("upperDeptId") String upperDeptId);

}
