package kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveySecUserMapper extends JdSurveySecUserEntityMapper {

    /**
     * ID 조회 - LOGIN으로
     *
     * @param login
     * @return
     */
    public Long selectIdByLogin(@Param("login") String login);

}
