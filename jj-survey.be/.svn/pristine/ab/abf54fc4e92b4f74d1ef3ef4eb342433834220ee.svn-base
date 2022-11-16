package kr.ac.jj.survey.domain.main.mapper.sys.log;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.log.TbSysLog;

/**
 * 시스템 - 로그 Entity Mapper
 */
abstract interface TbSysLogEntityMapper {
    /**
     * 조회
     *
     * @param logId
     * @return
     */
    public TbSysLog select(@Param("logId") String logId);

    /**
     * 생성
     *
     * @param tbSysLog
     * @return
     */
    public int insert(@Param("tbSysLog") TbSysLog tbSysLog);

    /**
     * 수정
     *
     * @param tbSysLog
     * @return
     */
    public int update(@Param("tbSysLog") TbSysLog tbSysLog);

    /**
     * 삭제
     *
     * @param logId
     * @return
     */
    public int delete(@Param("logId") String logId);
}
