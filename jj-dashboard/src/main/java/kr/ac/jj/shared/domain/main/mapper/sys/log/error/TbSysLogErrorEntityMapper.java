package kr.ac.jj.shared.domain.main.mapper.sys.log.error;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.log.error.TbSysLogError;

/**
 * 시스템 - 에러 로그 Entity Mapper
 */
abstract interface TbSysLogErrorEntityMapper {

    /**
     * 목록 조회 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public List<TbSysLogError> selectListByLogId(@Param("logId") String logId);

    /**
     * 조회
     *
     * @param errorLogId
     * @return
     */
    public TbSysLogError select(@Param("errorLogId") String errorLogId);

    /**
     * 생성
     *
     * @param tbSysLogError
     * @return
     */
    public int insert(@Param("tbSysLogError") TbSysLogError tbSysLogError);

    /**
     * 수정
     *
     * @param tbSysLogError
     * @return
     */
    public int update(@Param("tbSysLogError") TbSysLogError tbSysLogError);

    /**
     * 삭제
     *
     * @param errorLogId
     * @return
     */
    public int delete(@Param("errorLogId") String errorLogId);

    /**
     * 목록 삭제 - LOG_ID(으)로
     *
     * @param logId
     * @return
     */
    public int deleteListByLogId(@Param("logId") String logId);

}
