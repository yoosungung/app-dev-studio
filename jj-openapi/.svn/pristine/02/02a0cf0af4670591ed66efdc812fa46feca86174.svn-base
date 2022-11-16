package kr.ac.jj.openapi.domain.main.mapper.serviceguide;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.serviceguide.TbServiceGuide;

/**
 * 서비스 가이드 Entity Mapper
 */
abstract interface TbServiceGuideEntityMapper {

    /**
     * 조회
     *
     * @return
     */
    public TbServiceGuide select();

    /**
     * 생성
     *
     * @param tbServiceGuide
     * @return
     */
    public int insert(@Param("tbServiceGuide") TbServiceGuide tbServiceGuide);

    /**
     * 수정
     *
     * @param tbServiceGuide
     * @return
     */
    public int update(@Param("tbServiceGuide") TbServiceGuide tbServiceGuide);

    /**
     * 삭제
     *
     * @return
     */
    public int delete();

}
