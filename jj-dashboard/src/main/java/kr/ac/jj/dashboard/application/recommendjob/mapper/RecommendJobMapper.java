package kr.ac.jj.dashboard.application.recommendjob.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 직업추천 Mapper
 *
 */
@DwSqlMapper
public interface RecommendJobMapper {

    /**
     * 조회 - 직업추천 리스트
     *
     * @param search
     * @return
     */
    public void readList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

}
