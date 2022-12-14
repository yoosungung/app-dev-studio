package kr.ac.jj.survey.application.realmmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문 분야 관리 Mapper
 */
@MainSqlMapper
public interface RealmManageMapper {

    /**
     * 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

}
