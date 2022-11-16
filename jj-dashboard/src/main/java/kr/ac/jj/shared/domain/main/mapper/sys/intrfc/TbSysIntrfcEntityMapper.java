package kr.ac.jj.shared.domain.main.mapper.sys.intrfc;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.intrfc.TbSysIntrfc;

/**
 * 시스템 - 인터페이스 Entity Mapper
 */
abstract interface TbSysIntrfcEntityMapper {

    /**
     * 조회
     *
     * @param tableNm
     * @param idValue
     * @return
     */
    public TbSysIntrfc select(@Param("tableNm") String tableNm, @Param("idValue") String idValue);

    /**
     * 생성
     *
     * @param tbSysIntrfc
     * @return
     */
    public int insert(@Param("tbSysIntrfc") TbSysIntrfc tbSysIntrfc);

    /**
     * 수정
     *
     * @param tbSysIntrfc
     * @return
     */
    public int update(@Param("tbSysIntrfc") TbSysIntrfc tbSysIntrfc);

    /**
     * 삭제
     *
     * @param tableNm
     * @param idValue
     * @return
     */
    public int delete(@Param("tableNm") String tableNm, @Param("idValue") String idValue);

}
