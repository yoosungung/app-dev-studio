package kr.ac.jj.shared.domain.main.mapper.com.dty;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.dty.TbComDty;

/**
 * 공통 - 직무 Entity Mapper
 */
abstract interface TbComDtyEntityMapper {

    /**
     * 조회
     *
     * @param dtyId
     * @return
     */
    public TbComDty select(@Param("dtyId") String dtyId);

    /**
     * 생성
     *
     * @param tbComDty
     * @return
     */
    public int insert(@Param("tbComDty") TbComDty tbComDty);

    /**
     * 수정
     *
     * @param tbComDty
     * @return
     */
    public int update(@Param("tbComDty") TbComDty tbComDty);

    /**
     * 삭제
     *
     * @param dtyId
     * @return
     */
    public int delete(@Param("dtyId") String dtyId);

}
