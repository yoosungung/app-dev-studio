package kr.ac.jj.shared.application.admin.logmanage.emaillog.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 이메일 로그 관리 Mapper
 */
@SharedMainSqlMapper
public interface EmailLogManageMapper {

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
