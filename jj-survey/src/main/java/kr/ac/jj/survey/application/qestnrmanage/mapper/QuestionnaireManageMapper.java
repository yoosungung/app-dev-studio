package kr.ac.jj.survey.application.qestnrmanage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

/**
 * 설문지 관리 Mapper
 */
@MainSqlMapper
public interface QuestionnaireManageMapper {

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
     * 템플릿 검색 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectTemplateSearchList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 이전 설문지 검색 목록 조회
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectPrevSurveySearchList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 사람(대상자) 그룹 검색
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectPersonGroupList(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

    /**
     * 사람(대상자) 목록 생성 - 그룹 ID로부터
     *
     * @param surveyQestnrId
     * @param surveyGroupId
     * @return
     */
    public int insertPersonListFromGroupId(@Param("surveyQestnrId") String surveyQestnrId,
            @Param("surveyGroupId") String surveyGroupId);

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
     * @param tbSurveyQestnrPerson
     * @return
     */
    public int insertPersonNotExists(@Param("tbSurveyQestnrPerson") TbSurveyQestnrPerson tbSurveyQestnrPerson);

    /**
     * 조직 트리 목록 조회
     *
     * @param surveyQestnrId
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> selectDeptTreeList(@Param("surveyQestnrId") String surveyQestnrId,
            @Param("deptSe") DeptSeEnums deptSe);

    /**
     * 사람(대상자) 목록 생성 - 부서 ID로부터
     *
     * @param surveyQestnrId
     * @param deptId
     * @return
     */
    public int insertPersonListFromDeptId(@Param("surveyQestnrId") String surveyQestnrId,
            @Param("deptId") String deptId);

    /**
     * 설문 데이터 전체 삭제
     *
     * @param surveyDefinitionId
     */
    @Delete(value = "DELETE FROM SURVEY_DATA_${surveyDefinitionId}")
    @ResultType(value = Integer.class)
    public int deleteSurveyDataAll(Long surveyDefinitionId);

}
