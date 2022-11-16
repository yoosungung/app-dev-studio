package kr.ac.jj.shared.domain.main.mapper.sys.log.login;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.log.login.TbSysLogLogin;

/**
 * 시스템 - 로그인 로그 Entity Mapper
 */
abstract interface TbSysLogLoginEntityMapper {

    /**
     * 목록 조회 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public List<TbSysLogLogin> selectListByLogId(@Param("logId") String logId);

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

    /**
     * 목록 삭제 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public int deleteListByLogId(@Param("logId") String logId);

}
