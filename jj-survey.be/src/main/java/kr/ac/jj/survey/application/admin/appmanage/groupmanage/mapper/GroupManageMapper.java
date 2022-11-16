package kr.ac.jj.survey.application.admin.appmanage.groupmanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 그룹 관리 Mapper
 */
@MainSqlMapper
public interface GroupManageMapper {
    public List<Map<String, Object>> selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    public Map<String, Object> selectDefaultPerson();
}
