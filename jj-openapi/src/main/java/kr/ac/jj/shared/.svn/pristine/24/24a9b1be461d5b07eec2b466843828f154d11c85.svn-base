package kr.ac.jj.shared.domain.main.mapper.com.person.dept;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.person.dept.TbComPersonDept;

/**
 * 공통 - 사람별 부서 Entity Mapper
 */
abstract interface TbComPersonDeptEntityMapper {

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbComPersonDept> selectListByPersonId(@Param("personId") String personId);

    /**
     * 목록 조회 - DEPT_ID(으)로
     *
     * @param deptId
     * @return
     */
    public List<TbComPersonDept> selectListByDeptId(@Param("deptId") String deptId);

    /**
     * 조회
     *
     * @param personId
     * @param deptId
     * @return
     */
    public TbComPersonDept select(@Param("personId") String personId, @Param("deptId") String deptId);

    /**
     * 생성
     *
     * @param tbComPersonDept
     * @return
     */
    public int insert(@Param("tbComPersonDept") TbComPersonDept tbComPersonDept);

    /**
     * 수정
     *
     * @param tbComPersonDept
     * @return
     */
    public int update(@Param("tbComPersonDept") TbComPersonDept tbComPersonDept);

    /**
     * 삭제
     *
     * @param personId
     * @param deptId
     * @return
     */
    public int delete(@Param("personId") String personId, @Param("deptId") String deptId);

    /**
     * 목록 삭제 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public int deleteListByPersonId(@Param("personId") String personId);

    /**
     * 목록 삭제 - DEPT_ID(으)로
     *
     * @param deptId
     * @return
     */
    public int deleteListByDeptId(@Param("deptId") String deptId);

}
