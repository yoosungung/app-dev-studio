package kr.ac.jj.shared.infrastructure.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;
import kr.ac.jj.shared.infrastructure.security.model.SecuredResourceAuthor;
import kr.ac.jj.shared.infrastructure.security.model.SecuredRoleHierarchy;

@SharedSqlMapper
public interface SecuredResourceMapper {

    public List<SecuredResourceAuthor> selectAuthorsAndUrlList(@Param("resrceTy") String resrceTy);

    public List<SecuredRoleHierarchy> selectRoleHierarchyList();

}
