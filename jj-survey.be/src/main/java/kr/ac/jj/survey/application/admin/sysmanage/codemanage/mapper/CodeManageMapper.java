package kr.ac.jj.survey.application.admin.sysmanage.codemanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.sys.code.group.TbSysCodeGroup;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 코드 관리 Mapper
 */
@MainSqlMapper
public interface CodeManageMapper {
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
     * 코드 그룹 중복 여부 조회
     *
     * @param tbSysCodeGroup
     * @return
     */
    public int selectCodeGroup(@Param("tbSysCodeGroup") TbSysCodeGroup tbSysCodeGroup);
}
