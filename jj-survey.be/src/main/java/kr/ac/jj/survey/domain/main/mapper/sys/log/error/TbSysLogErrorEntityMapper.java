package kr.ac.jj.survey.domain.main.mapper.sys.log.error;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.log.error.TbSysLogError;

/**
 * 시스템 - 에러 로그 Entity Mapper
 */
abstract interface TbSysLogErrorEntityMapper {
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
}
