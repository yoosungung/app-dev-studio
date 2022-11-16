package kr.ac.jj.shared.domain.main.mapper.sys.schdul.execut;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.schdul.execut.TbSysSchdulExecut;

/**
 * 시스템 - 스케줄 실행 Entity Mapper
 */
abstract interface TbSysSchdulExecutEntityMapper {

    /**
     * 목록 조회 - SCHDUL_ID(으)로
     *
     * @param schdulId
     * @return
     */
    public List<TbSysSchdulExecut> selectListBySchdulId(@Param("schdulId") String schdulId);

    /**
     * 조회
     *
     * @param schdulExecutId
     * @return
     */
    public TbSysSchdulExecut select(@Param("schdulExecutId") String schdulExecutId);

    /**
     * 생성
     *
     * @param tbSysSchdulExecut
     * @return
     */
    public int insert(@Param("tbSysSchdulExecut") TbSysSchdulExecut tbSysSchdulExecut);

    /**
     * 수정
     *
     * @param tbSysSchdulExecut
     * @return
     */
    public int update(@Param("tbSysSchdulExecut") TbSysSchdulExecut tbSysSchdulExecut);

    /**
     * 삭제
     *
     * @param schdulExecutId
     * @return
     */
    public int delete(@Param("schdulExecutId") String schdulExecutId);

    /**
     * 목록 삭제 - SCHDUL_ID(으)로
     *
     * @param schdulId
     * @return
     */
    public int deleteListBySchdulId(@Param("schdulId") String schdulId);

}
