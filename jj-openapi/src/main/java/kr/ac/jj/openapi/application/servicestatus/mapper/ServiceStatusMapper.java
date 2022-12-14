package kr.ac.jj.openapi.application.servicestatus.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 서비스 관리 Mapper
 */
@MainSqlMapper
public interface ServiceStatusMapper {

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
     * 변수 중복 여부 조회
     *
     * @param tbApiSvc
     * @return
     */
    public int selectVariable(@Param("tbApiSvc") TbApiSvc tbApiSvc);

    /**
     * 결과변수 중복 여부 조회
     *
     * @param tbApiSvc
     * @return
     */
    public int selectResult(@Param("tbApiSvc") TbApiSvc tbApiSvc);

}
