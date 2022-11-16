package kr.ac.jj.survey.infrastructure.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.security.model.SecuredResourceAuthor;
import kr.ac.jj.survey.infrastructure.security.model.SecuredRoleHierarchy;

@MainSqlMapper
public interface SecuredResourceMapper {
    public List<SecuredResourceAuthor> selectAuthorsAndUrlList(@Param("resrceTy") String resrceTy);

    public List<SecuredRoleHierarchy> selectRoleHierarchyList();
}
