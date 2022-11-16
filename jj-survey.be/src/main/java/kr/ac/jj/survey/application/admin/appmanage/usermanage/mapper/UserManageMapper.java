package kr.ac.jj.survey.application.admin.appmanage.usermanage.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 사용자 관리 Mapper
 */
@MainSqlMapper
public interface UserManageMapper {

    /**
     * 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 기본 인원 조회
     *
     * @return
     */
    public Map<String, Object> selectDefaultPerson();
}
