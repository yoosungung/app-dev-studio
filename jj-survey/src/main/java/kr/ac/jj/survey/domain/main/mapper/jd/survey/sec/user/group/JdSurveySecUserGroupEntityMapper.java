package kr.ac.jj.survey.domain.main.mapper.jd.survey.sec.user.group;

import org.apache.ibatis.annotations.Param;

/**
 * Entity Mapper
 */
abstract interface JdSurveySecUserGroupEntityMapper {

    /**
     * 생성
     *
     * @param userId
     * @param groupId
     * @return
     */
    public int insert(@Param("userId") Long userId, @Param("groupId") Long groupId);

    /**
     * 삭제
     *
     * @param userId
     * @param groupId
     * @return
     */
    public int delete(@Param("userId") Long userId, @Param("groupId") Long groupId);

}
