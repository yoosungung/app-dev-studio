package kr.ac.jj.shared.domain.main.mapper.com.person.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;

/**
 * 공통 - 사람 사용자 Mapper
 */
@SharedSqlMapper
public interface TbComPersonUserMapper extends TbComPersonUserEntityMapper {

    /**
     * personId 조회
     *
     * @param userId
     * @return
     */
    public String selectPersonId(@Param("userId") String userId);

    /**
     * userId 조회
     *
     * @param personId
     * @return
     */
    public String selectUserId(@Param("personId") String personId);

    /**
     * personId 삭제
     *
     * @param personId
     * @return
     */
    public int deletePersonId(@Param("personId") String personId);

    /**
     * userId 삭제
     *
     * @param userId
     * @return
     */
    public int deleteUserId(@Param("userId") String userId);

}
