package kr.ac.jj.shared.domain.main.mapper.bbs.gnrl;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.bbs.gnrl.TbBbsGnrl;

/**
 * 게시판 - 일반(답변형) Entity Mapper
 */
abstract interface TbBbsGnrlEntityMapper {

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    public TbBbsGnrl select(@Param("bbscttId") String bbscttId);

    /**
     * 생성
     *
     * @param tbBbsGnrl
     * @return
     */
    public int insert(@Param("tbBbsGnrl") TbBbsGnrl tbBbsGnrl);

    /**
     * 수정
     *
     * @param tbBbsGnrl
     * @return
     */
    public int update(@Param("tbBbsGnrl") TbBbsGnrl tbBbsGnrl);

    /**
     * 삭제
     *
     * @param bbscttId
     * @return
     */
    public int delete(@Param("bbscttId") String bbscttId);

}
