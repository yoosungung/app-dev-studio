package kr.ac.jj.survey.domain.main.mapper.survey.group.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.group.person.TbSurveyGroupPersonToGroup;

/**
 * 설문_그룹_사람 TO 설문_그룹 Mapper
 */
@MainSqlMapper
public interface TbSurveyGroupPersonToGroupMapper {

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbSurveyGroupPersonToGroup> selectListByPersonId(@Param("personId") String personId);

}
