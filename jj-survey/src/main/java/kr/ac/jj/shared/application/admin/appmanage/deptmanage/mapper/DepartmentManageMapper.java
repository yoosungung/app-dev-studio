package kr.ac.jj.shared.application.admin.appmanage.deptmanage.mapper;

import java.util.List;
import java.util.Map;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 부서 관리 Mapper
 */
@SharedMainSqlMapper
public interface DepartmentManageMapper {

    /**
     * 트리 조회
     *
     * @return
     */
    public List<Map<String, Object>> selectTree();

}
