package kr.ac.jj.shared.domain.main.mapper.com.person.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.person.user.TbComPersonUser;

/**
 * 공통 - 사람 사용자 Entity Mapper
 */
abstract interface TbComPersonUserEntityMapper {

    /**
     * 조회
     *
     * @param personUserId
     * @return
     */
    public TbComPersonUser select(@Param("personUserId") String personUserId);

    /**
     * 생성
     *
     * @param tbComPersonUser
     * @return
     */
    public int insert(@Param("tbComPersonUser") TbComPersonUser tbComPersonUser);

    /**
     * 수정
     *
     * @param tbComPersonUser
     * @return
     */
    public int update(@Param("tbComPersonUser") TbComPersonUser tbComPersonUser);

    /**
     * 삭제
     *
     * @param personUserId
     * @return
     */
    public int delete(@Param("personUserId") String personUserId);

}
