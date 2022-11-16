package kr.ac.jj.survey.application.admin.sysmanage.titlemanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 타이틀 관리 Mapper
 */
@MainSqlMapper
public interface TitleManageMapper {
    /**
     * 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     * @param titleLocales
     */
    public void selectList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search, @Param("titleLocales") String[] titleLocales);
}
