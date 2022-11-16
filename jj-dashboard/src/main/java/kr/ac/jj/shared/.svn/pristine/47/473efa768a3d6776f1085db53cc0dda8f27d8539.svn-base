package kr.ac.jj.shared.domain.main.mapper.sys.user.setup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.user.setup.TbSysUserSetup;

/**
 * 시스템 - 사용자별 환경설정 Entity Mapper
 */
abstract interface TbSysUserSetupEntityMapper {

    /**
     * 목록 조회 - USER_ID(으)로
     *
     * @param userId
     * @return
     */
    public List<TbSysUserSetup> selectListByUserId(@Param("userId") String userId);

    /**
     * 조회
     *
     * @param userId
     * @param setupCode
     * @return
     */
    public TbSysUserSetup select(@Param("userId") String userId, @Param("setupCode") String setupCode);

    /**
     * 생성
     *
     * @param tbSysUserSetup
     * @return
     */
    public int insert(@Param("tbSysUserSetup") TbSysUserSetup tbSysUserSetup);

    /**
     * 수정
     *
     * @param tbSysUserSetup
     * @return
     */
    public int update(@Param("tbSysUserSetup") TbSysUserSetup tbSysUserSetup);

    /**
     * 삭제
     *
     * @param userId
     * @param setupCode
     * @return
     */
    public int delete(@Param("userId") String userId, @Param("setupCode") String setupCode);

    /**
     * 목록 삭제 - USER_ID(으)로
     *
     * @param userId
     * @return
     */
    public int deleteListByUserId(@Param("userId") String userId);

}
