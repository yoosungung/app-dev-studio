package kr.ac.jj.survey.domain.main.mapper.sys.log.login;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.log.login.TbSysLogLogin;

/**
 * 시스템 - 로그인 로그 Entity Mapper
 */
abstract interface TbSysLogLoginEntityMapper {
    /**
     * 조회
     *
     * @param loginLogId
     * @return
     */
    public TbSysLogLogin select(@Param("loginLogId") String loginLogId);

    /**
     * 생성
     *
     * @param tbSysLogLogin
     * @return
     */
    public int insert(@Param("tbSysLogLogin") TbSysLogLogin tbSysLogLogin);

    /**
     * 수정
     *
     * @param tbSysLogLogin
     * @return
     */
    public int update(@Param("tbSysLogLogin") TbSysLogLogin tbSysLogLogin);

    /**
     * 삭제
     *
     * @param loginLogId
     * @return
     */
    public int delete(@Param("loginLogId") String loginLogId);
}
