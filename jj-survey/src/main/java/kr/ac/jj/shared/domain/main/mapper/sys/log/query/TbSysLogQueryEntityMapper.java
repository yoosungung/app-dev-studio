package kr.ac.jj.shared.domain.main.mapper.sys.log.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.log.query.TbSysLogQuery;

/**
 * 시스템 - 쿼리 로그 Entity Mapper
 */
abstract interface TbSysLogQueryEntityMapper {

    /**
     * 목록 조회 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public List<TbSysLogQuery> selectListByLogId(@Param("logId") String logId);

    /**
     * 조회
     *
     * @param queryLogId
     * @return
     */
    public TbSysLogQuery select(@Param("queryLogId") String queryLogId);

    /**
     * 생성
     *
     * @param tbSysLogQuery
     * @return
     */
    public int insert(@Param("tbSysLogQuery") TbSysLogQuery tbSysLogQuery);

    /**
     * 수정
     *
     * @param tbSysLogQuery
     * @return
     */
    public int update(@Param("tbSysLogQuery") TbSysLogQuery tbSysLogQuery);

    /**
     * 삭제
     *
     * @param queryLogId
     * @return
     */
    public int delete(@Param("queryLogId") String queryLogId);

    /**
     * 목록 삭제 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public int deleteListByLogId(@Param("logId") String logId);

}
