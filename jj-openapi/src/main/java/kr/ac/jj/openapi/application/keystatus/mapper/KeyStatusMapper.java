package kr.ac.jj.openapi.application.keystatus.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 키 신청 현황 Mapper
 */
@MainSqlMapper
public interface KeyStatusMapper {

    /**
     * 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     * @param isLoginUserOnly
     */
    public void selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search, @Param("isLoginUserOnly") boolean isLoginUserOnly);

}
