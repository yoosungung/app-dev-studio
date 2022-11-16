package kr.ac.jj.shared.application.admin.sysmanage.authormanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

/**
 * 권한 관리 Mapper
 */
@SharedMainSqlMapper
public interface AuthorityManageMapper {

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
     * 부서 트리 목록 조회
     *
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> selectDeptTreeList(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 사용자 검색 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectUserSearchList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 직무 검색 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectDutySearchList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

}
