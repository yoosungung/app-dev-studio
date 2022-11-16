package kr.ac.jj.shared.domain.main.mapper.sys.rolehierarchy;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 시스템 - 역할 계층 Mapper
 */
@SharedMainSqlMapper
public interface TbSysRoleHierarchyMapper extends TbSysRoleHierarchyEntityMapper {

    /**
     * 목록 삭제 - 역할 코드로
     *
     * @param roleCode
     * @return
     */
    public int deleteListByRoleCode(@Param("roleCode") String roleCode);

}
