package kr.ac.jj.shared.application.bbs.faq.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 게시판(FAQ) Mapper
 */
@SharedSqlMapper
public interface BbsFaqMapper {

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
