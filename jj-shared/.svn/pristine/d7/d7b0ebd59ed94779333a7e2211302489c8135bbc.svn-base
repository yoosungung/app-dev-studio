package kr.ac.jj.shared.domain.main.mapper.sys.title;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.title.TbSysTitle;

/**
 * 시스템 - 타이틀 Entity Mapper
 */
abstract interface TbSysTitleEntityMapper {

    /**
     * 목록 조회 - TITLE_CODE(으)로
     *
     * @param titleCode
     * @return
     */
    public List<TbSysTitle> selectListByTitleCode(@Param("titleCode") String titleCode);

    /**
     * 조회
     *
     * @param titleCode
     * @param titleLocale
     * @return
     */
    public TbSysTitle select(@Param("titleCode") String titleCode, @Param("titleLocale") String titleLocale);

    /**
     * 생성
     *
     * @param tbSysTitle
     * @return
     */
    public int insert(@Param("tbSysTitle") TbSysTitle tbSysTitle);

    /**
     * 수정
     *
     * @param tbSysTitle
     * @return
     */
    public int update(@Param("tbSysTitle") TbSysTitle tbSysTitle);

    /**
     * 삭제
     *
     * @param titleCode
     * @param titleLocale
     * @return
     */
    public int delete(@Param("titleCode") String titleCode, @Param("titleLocale") String titleLocale);

    /**
     * 목록 삭제 - TITLE_CODE(으)로
     *
     * @param titleCode
     * @return
     */
    public int deleteListByTitleCode(@Param("titleCode") String titleCode);

}
