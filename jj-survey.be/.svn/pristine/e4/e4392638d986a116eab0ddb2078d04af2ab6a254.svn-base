package kr.ac.jj.survey.domain.main.mapper.sys.resrce;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.resrce.TbSysResrce;

/**
 * 시스템 - 리소스 Entity Mapper
 */
abstract interface TbSysResrceEntityMapper {
    /**
     * 조회
     *
     * @param resrceId
     * @return
     */
    public TbSysResrce select(@Param("resrceId") String resrceId);

    /**
     * 생성
     *
     * @param tbSysResrce
     * @return
     */
    public int insert(@Param("tbSysResrce") TbSysResrce tbSysResrce);

    /**
     * 수정
     *
     * @param tbSysResrce
     * @return
     */
    public int update(@Param("tbSysResrce") TbSysResrce tbSysResrce);

    /**
     * 삭제
     *
     * @param resrceId
     * @return
     */
    public int delete(@Param("resrceId") String resrceId);
}
