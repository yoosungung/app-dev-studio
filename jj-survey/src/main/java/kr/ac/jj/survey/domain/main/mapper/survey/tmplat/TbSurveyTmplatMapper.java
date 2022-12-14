package kr.ac.jj.survey.domain.main.mapper.survey.tmplat;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * 설문_템플릿 Mapper
 */
@MainSqlMapper
public interface TbSurveyTmplatMapper extends TbSurveyTmplatEntityMapper {

    /**
     * 수정 - 템플릿 상태
     *
     * @param surveyTmplatId
     * @param tmplatSttus
     * @return
     */
    public int updateTmplatSttus(@Param("surveyTmplatId") String surveyTmplatId,
            @Param("tmplatSttus") String tmplatSttus);

}
