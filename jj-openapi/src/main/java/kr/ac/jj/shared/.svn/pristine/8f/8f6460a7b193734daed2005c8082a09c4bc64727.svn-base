package kr.ac.jj.shared.domain.main.mapper.sys.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.user.TbSysUser;

/**
 * 시스템 - 사용자 Entity Mapper
 */
abstract interface TbSysUserEntityMapper {

    /**
     * 조회
     *
     * @param userId
     * @return
     */
    public TbSysUser select(@Param("userId") String userId);

    /**
     * 생성
     *
     * @param tbSysUser
     * @return
     */
    public int insert(@Param("tbSysUser") TbSysUser tbSysUser);

    /**
     * 수정
     *
     * @param tbSysUser
     * @return
     */
    public int update(@Param("tbSysUser") TbSysUser tbSysUser);

    /**
     * 삭제
     *
     * @param userId
     * @return
     */
    public int delete(@Param("userId") String userId);

}
