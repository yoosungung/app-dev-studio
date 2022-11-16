package kr.ac.jj.shared.domain.main.mapper.sys.schdul;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.schdul.TbSysSchdul;

/**
 * 시스템 - 스케줄 Entity Mapper
 */
abstract interface TbSysSchdulEntityMapper {

    /**
     * 조회
     *
     * @param schdulId
     * @return
     */
    public TbSysSchdul select(@Param("schdulId") String schdulId);

    /**
     * 생성
     *
     * @param tbSysSchdul
     * @return
     */
    public int insert(@Param("tbSysSchdul") TbSysSchdul tbSysSchdul);

    /**
     * 수정
     *
     * @param tbSysSchdul
     * @return
     */
    public int update(@Param("tbSysSchdul") TbSysSchdul tbSysSchdul);

    /**
     * 삭제
     *
     * @param schdulId
     * @return
     */
    public int delete(@Param("schdulId") String schdulId);

}
