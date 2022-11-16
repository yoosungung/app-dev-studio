package kr.ac.jj.survey.application.admin.sysmanage.resrcemanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 리소스 관리 Mapper
 */
@MainSqlMapper
public interface ResourceManageMapper {
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
     * 순서 저장
     *
     * @param resourceManageOrdr
     * @return
     */
    public int updateOrdr(@Param("resourceManageOrdr") BaseMap resourceManageOrdr);
}
