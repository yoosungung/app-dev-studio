package kr.ac.jj.openapi.domain.main.mapper.api.banner;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.banner.TbApiBanner;

/**
 * 오픈API - 배너 Entity Mapper
 */
abstract interface TbApiBannerEntityMapper {

    /**
     * 조회
     *
     * @param bannerId
     * @return
     */
    public TbApiBanner select(@Param("bannerId") String bannerId);

    /**
     * 생성
     *
     * @param tbApiBanner
     * @return
     */
    public int insert(@Param("tbApiBanner") TbApiBanner tbApiBanner);

    /**
     * 수정
     *
     * @param tbApiBanner
     * @return
     */
    public int update(@Param("tbApiBanner") TbApiBanner tbApiBanner);

    /**
     * 삭제
     *
     * @param bannerId
     * @return
     */
    public int delete(@Param("bannerId") String bannerId);

}
