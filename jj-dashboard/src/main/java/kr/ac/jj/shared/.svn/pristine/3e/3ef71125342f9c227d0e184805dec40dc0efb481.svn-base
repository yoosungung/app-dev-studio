package kr.ac.jj.shared.domain.main.mapper.com.person.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 공통 - 사람 사용자 Mapper
 */
@SharedMainSqlMapper
public interface TbComPersonUserMapper extends TbComPersonUserEntityMapper {

    /**
     * 목록 생성
     *
     * @return
     */
    public int insertList();

    /**
     * PERSON_ID 조회 - USER_ID로
     *
     * @param userId
     * @return
     */
    public String selectPersonIdByUserId(@Param("userId") String userId);

    /**
     * USER_ID 조회 - PERSON_ID로
     *
     * @param personId
     * @return
     */
    public String selectUserIdByPersonId(@Param("personId") String personId);

    /**
     * 삭제 - PERSON_ID로
     *
     * @param personId
     * @return
     */
    public int deleteByPersonId(@Param("personId") String personId);

    /**
     * 삭제 - USER_ID로
     *
     * @param userId
     * @return
     */
    public int deleteByUserId(@Param("userId") String userId);

}
