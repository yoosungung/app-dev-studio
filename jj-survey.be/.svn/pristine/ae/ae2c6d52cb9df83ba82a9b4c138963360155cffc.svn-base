package kr.ac.jj.survey.domain.main.mapper.com.bbsctt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.com.bbsctt.TbComBbsctt;

/**
 * 공통 - 게시글 Entity Mapper
 */
abstract interface TbComBbscttEntityMapper {
    /**
     * 목록 조회 - BBS_ID(으)로
     *
     * @param bbsId
     * @return
     */
    public List<TbComBbsctt> selectListByBbsId(@Param("bbsId") String bbsId);

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    public TbComBbsctt select(@Param("bbscttId") String bbscttId);

    /**
     * 생성
     *
     * @param tbComBbsctt
     * @return
     */
    public int insert(@Param("tbComBbsctt") TbComBbsctt tbComBbsctt);

    /**
     * 수정
     *
     * @param tbComBbsctt
     * @return
     */
    public int update(@Param("tbComBbsctt") TbComBbsctt tbComBbsctt);

    /**
     * 삭제
     *
     * @param bbscttId
     * @return
     */
    public int delete(@Param("bbscttId") String bbscttId);

    /**
     * 목록 삭제 - BBS_ID(으)로
     *
     * @param bbsId
     * @return
     */
    public int deleteListByBbsId(@Param("bbsId") String bbsId);
}
