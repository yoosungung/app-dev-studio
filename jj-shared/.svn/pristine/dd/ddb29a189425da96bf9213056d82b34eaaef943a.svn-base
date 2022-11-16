package kr.ac.jj.shared.domain.main.mapper.sys.user.loginfailr;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.user.loginfailr.TbSysUserLoginFailr;

/**
 * 시스템 - 사용자 로그인 실패 Entity Mapper
 */
abstract interface TbSysUserLoginFailrEntityMapper {

    /**
     * 조회
     *
     * @param loginNm
     * @return
     */
    public TbSysUserLoginFailr select(@Param("loginNm") String loginNm);

    /**
     * 생성
     *
     * @param tbSysUserLoginFailr
     * @return
     */
    public int insert(@Param("tbSysUserLoginFailr") TbSysUserLoginFailr tbSysUserLoginFailr);

    /**
     * 수정
     *
     * @param tbSysUserLoginFailr
     * @return
     */
    public int update(@Param("tbSysUserLoginFailr") TbSysUserLoginFailr tbSysUserLoginFailr);

    /**
     * 삭제
     *
     * @param loginNm
     * @return
     */
    public int delete(@Param("loginNm") String loginNm);

}
