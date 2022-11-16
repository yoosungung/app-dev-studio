package kr.ac.jj.shared.application.bbs.gnrl.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 게시판(일반) Mapper
 */
@SharedMainSqlMapper
public interface BbsGeneralMapper {

    /**
     * 목록 조회
     *
     * @param bbsCode
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectList(String bbsCode, GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

}
