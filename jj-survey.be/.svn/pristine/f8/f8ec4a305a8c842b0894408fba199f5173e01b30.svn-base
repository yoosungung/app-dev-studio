package kr.ac.jj.survey.domain.main.mapper.sys.rolehierarchy;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.rolehierarchy.TbSysRoleHierarchy;

/**
 * 시스템 - 역할 계층 Entity Mapper
 */
abstract interface TbSysRoleHierarchyEntityMapper {
    /**
     * 조회
     *
     * @param roleHierarchyId
     * @return
     */
    public TbSysRoleHierarchy select(@Param("roleHierarchyId") String roleHierarchyId);

    /**
     * 생성
     *
     * @param tbSysRoleHierarchy
     * @return
     */
    public int insert(@Param("tbSysRoleHierarchy") TbSysRoleHierarchy tbSysRoleHierarchy);

    /**
     * 수정
     *
     * @param tbSysRoleHierarchy
     * @return
     */
    public int update(@Param("tbSysRoleHierarchy") TbSysRoleHierarchy tbSysRoleHierarchy);

    /**
     * 삭제
     *
     * @param roleHierarchyId
     * @return
     */
    public int delete(@Param("roleHierarchyId") String roleHierarchyId);
}
