package kr.ac.jj.shared.domain.main.mapper.sys.schdul.trigr;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.schdul.trigr.TbSysSchdulTrigr;

/**
 * 시스템 - 스케줄 트리거 Entity Mapper
 */
abstract interface TbSysSchdulTrigrEntityMapper {

    /**
     * 목록 조회 - SCHDUL_ID(으)로
     *
     * @param schdulId
     * @return
     */
    public List<TbSysSchdulTrigr> selectListBySchdulId(@Param("schdulId") String schdulId);

    /**
     * 조회
     *
     * @param schdulTrigrId
     * @return
     */
    public TbSysSchdulTrigr select(@Param("schdulTrigrId") String schdulTrigrId);

    /**
     * 생성
     *
     * @param tbSysSchdulTrigr
     * @return
     */
    public int insert(@Param("tbSysSchdulTrigr") TbSysSchdulTrigr tbSysSchdulTrigr);

    /**
     * 수정
     *
     * @param tbSysSchdulTrigr
     * @return
     */
    public int update(@Param("tbSysSchdulTrigr") TbSysSchdulTrigr tbSysSchdulTrigr);

    /**
     * 삭제
     *
     * @param schdulTrigrId
     * @return
     */
    public int delete(@Param("schdulTrigrId") String schdulTrigrId);

    /**
     * 목록 삭제 - SCHDUL_ID(으)로
     *
     * @param schdulId
     * @return
     */
    public int deleteListBySchdulId(@Param("schdulId") String schdulId);

}
