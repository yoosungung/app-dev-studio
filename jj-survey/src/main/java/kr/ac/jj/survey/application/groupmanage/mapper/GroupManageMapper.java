package kr.ac.jj.survey.application.groupmanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.group.person.TbSurveyGroupPerson;

/**
 * 그룹 관리 Mapper
 */
@MainSqlMapper
public interface GroupManageMapper {

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
     * 사람(대상자) 목록 검색
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectPersonSearchList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 사람(대상자) 생성 - 존재하지 않는 경우
     *
     * @param tbSurveyGroupPerson
     * @return
     */
    public int insertPersonNotExists(@Param("tbSurveyGroupPerson") TbSurveyGroupPerson tbSurveyGroupPerson);

    /**
     * 조직 트리 목록 조회
     *
     * @param surveyGroupId
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> selectDeptTreeList(@Param("surveyGroupId") String surveyGroupId,
            @Param("deptSe") DeptSeEnums deptSe);

    /**
     * 사람(대상자) 목록 생성 - 부서 ID로부터
     *
     * @param surveyGroupId
     * @param deptId
     * @return
     */
    public int insertPersonListFromDeptId(@Param("surveyGroupId") String surveyGroupId, @Param("deptId") String deptId);

}
