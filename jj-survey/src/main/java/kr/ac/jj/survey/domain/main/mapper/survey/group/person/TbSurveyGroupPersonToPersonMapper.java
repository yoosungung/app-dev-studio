package kr.ac.jj.survey.domain.main.mapper.survey.group.person;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문_그룹_사람 TO 공통 - 사람 Mapper
 */
@MainSqlMapper
public interface TbSurveyGroupPersonToPersonMapper {

    /**
     * 그리드 목록 조회 - SURVEY_GROUP_ID(으)로
     *
     * @param resultHandler
     * @param paging
     * @param search
     */
    public void selectGridListBySurveyGroupId(GridDataResultHandler resultHandler, @Param("paging") PagingInfo paging,
            @Param("search") BaseMap search);

}
