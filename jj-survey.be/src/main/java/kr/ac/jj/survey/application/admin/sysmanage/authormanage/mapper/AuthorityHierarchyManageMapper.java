package kr.ac.jj.survey.application.admin.sysmanage.authormanage.mapper;

import java.util.List;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.sys.rolehierarchy.TbSysRoleHierarchy;

/**
 * 권한 계층 관리 Mapper
 */
@MainSqlMapper
public interface AuthorityHierarchyManageMapper {
    /**
     * 목록 조회
     */
    public List<TbSysRoleHierarchy> selectList();

    /**
     * 목록 삭제
     */
    public int deleteList();
}
