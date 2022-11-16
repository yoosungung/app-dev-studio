package kr.ac.jj.survey.domain.main.mapper.com.bbsctt.popup;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.com.bbsctt.popup.TbComBbscttPopup;

/**
 * 공통 - 게시글 팝업 Entity Mapper
 */
abstract interface TbComBbscttPopupEntityMapper {
    /**
     * 조회
     *
     * @param bbscttPopupId
     * @return
     */
    public TbComBbscttPopup select(@Param("bbscttPopupId") String bbscttPopupId);

    /**
     * 생성
     *
     * @param tbComBbscttPopup
     * @return
     */
    public int insert(@Param("tbComBbscttPopup") TbComBbscttPopup tbComBbscttPopup);

    /**
     * 수정
     *
     * @param tbComBbscttPopup
     * @return
     */
    public int update(@Param("tbComBbscttPopup") TbComBbscttPopup tbComBbscttPopup);

    /**
     * 삭제
     *
     * @param bbscttPopupId
     * @return
     */
    public int delete(@Param("bbscttPopupId") String bbscttPopupId);
}
