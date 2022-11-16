package kr.ac.jj.shared.domain.main.mapper.sys.author.relatecode;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.author.relatecode.TbSysAuthorRelateCode;

/**
 * 시스템 - 권한별 관련 코드 Entity Mapper
 */
abstract interface TbSysAuthorRelateCodeEntityMapper {

    /**
     * 목록 조회 - AUTHOR_ID, RELATE_CODE_SE(으)로
     *
     * @param authorId
     * @param relateCodeSe
     * @return
     */
    public List<TbSysAuthorRelateCode> selectListByAuthorIdAndRelateCodeSe(@Param("authorId") String authorId,
            @Param("relateCodeSe") String relateCodeSe);

    /**
     * 조회
     *
     * @param authorId
     * @param relateCodeSe
     * @param relateCodeValue
     * @return
     */
    public TbSysAuthorRelateCode select(@Param("authorId") String authorId, @Param("relateCodeSe") String relateCodeSe,
            @Param("relateCodeValue") String relateCodeValue);

    /**
     * 생성
     *
     * @param tbSysAuthorRelateCode
     * @return
     */
    public int insert(@Param("tbSysAuthorRelateCode") TbSysAuthorRelateCode tbSysAuthorRelateCode);

    /**
     * 수정
     *
     * @param tbSysAuthorRelateCode
     * @return
     */
    public int update(@Param("tbSysAuthorRelateCode") TbSysAuthorRelateCode tbSysAuthorRelateCode);

    /**
     * 삭제
     *
     * @param authorId
     * @param relateCodeSe
     * @param relateCodeValue
     * @return
     */
    public int delete(@Param("authorId") String authorId, @Param("relateCodeSe") String relateCodeSe,
            @Param("relateCodeValue") String relateCodeValue);

    /**
     * 목록 삭제 - AUTHOR_ID, RELATE_CODE_SE(으)로
     *
     * @param authorId
     * @param relateCodeSe
     * @return
     */
    public int deleteListByAuthorIdAndRelateCodeSe(@Param("authorId") String authorId,
            @Param("relateCodeSe") String relateCodeSe);

}
