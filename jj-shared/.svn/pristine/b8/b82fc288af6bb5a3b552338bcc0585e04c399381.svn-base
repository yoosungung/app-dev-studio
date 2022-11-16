package kr.ac.jj.shared.domain.main.mapper.bbs.notice;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.bbs.notice.TbBbsNotice;

/**
 * 게시판 - 공지사항 Entity Mapper
 */
abstract interface TbBbsNoticeEntityMapper {

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    public TbBbsNotice select(@Param("bbscttId") String bbscttId);

    /**
     * 생성
     *
     * @param tbBbsNotice
     * @return
     */
    public int insert(@Param("tbBbsNotice") TbBbsNotice tbBbsNotice);

    /**
     * 수정
     *
     * @param tbBbsNotice
     * @return
     */
    public int update(@Param("tbBbsNotice") TbBbsNotice tbBbsNotice);

    /**
     * 삭제
     *
     * @param bbscttId
     * @return
     */
    public int delete(@Param("bbscttId") String bbscttId);

}
