package kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user.group;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Mapper
 */
@MainSqlMapper
public interface JdSurveySecUserGroupMapper extends JdSurveySecUserGroupEntityMapper {

    /**
     * 생성 - 존재하지 않는 경우
     *
     * @param userId
     * @param groupId
     * @return
     */
    public int insertNotExists(@Param("userId") Long userId, @Param("groupId") Long groupId);

}
