package kr.ac.jj.survey.domain.main.mapper.jd.survey.definition;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveyDefinitionMapper extends JdSurveyDefinitionEntityMapper {

    /**
     * 수정 - STATUS
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 삭제 - 작성중인 경우
     *
     * @param id
     * @return
     */
    public int deleteIfStatusI(@Param("id") Long id);

}
