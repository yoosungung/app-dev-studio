package kr.ac.jj.shared.domain.main.mapper.bbs.faq;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.bbs.faq.TbBbsFaq;

/**
 * 게시판 - FAQ Entity Mapper
 */
abstract interface TbBbsFaqEntityMapper {

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    public TbBbsFaq select(@Param("bbscttId") String bbscttId);

    /**
     * 생성
     *
     * @param tbBbsFaq
     * @return
     */
    public int insert(@Param("tbBbsFaq") TbBbsFaq tbBbsFaq);

    /**
     * 수정
     *
     * @param tbBbsFaq
     * @return
     */
    public int update(@Param("tbBbsFaq") TbBbsFaq tbBbsFaq);

    /**
     * 삭제
     *
     * @param bbscttId
     * @return
     */
    public int delete(@Param("bbscttId") String bbscttId);

}
