package kr.ac.jj.survey.domain.main.mapper.survey.realm;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.application.realmmanage.model.RealmUsedCountModel;
import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문_분야 Mapper
 */
@MainSqlMapper
public interface TbSurveyRealmMapper extends TbSurveyRealmEntityMapper {

    /**
     * 사용된 갯수 조회
     *
     * @param surveyRealmId
     * @return
     */
    public RealmUsedCountModel selectUsedCo(@Param("surveyRealmId") String surveyRealmId);

}
