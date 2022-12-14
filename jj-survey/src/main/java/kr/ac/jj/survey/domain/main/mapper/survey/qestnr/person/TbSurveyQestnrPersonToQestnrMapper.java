package kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPersonToQestnr;

/**
 * 설문_설문지 - 사람(대상자) TO 설문_설문지 Mapper
 */
@MainSqlMapper
public interface TbSurveyQestnrPersonToQestnrMapper {

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbSurveyQestnrPersonToQestnr> selectListByPersonId(@Param("personId") String personId);

}
