package kr.ac.jj.shared.domain.main.mapper.intrfc.code;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.intrfc.code.TbIntrfcCode;

/**
 * 인터페이스 - 코드 Entity Mapper
 */
abstract interface TbIntrfcCodeEntityMapper {

    /**
     * 목록 조회 - CODE_TY(으)로
     *
     * @param codeTy
     * @return
     */
    public List<TbIntrfcCode> selectListByCodeTy(@Param("codeTy") String codeTy);

    /**
     * 조회
     *
     * @param codeTy
     * @param codeValue
     * @return
     */
    public TbIntrfcCode select(@Param("codeTy") String codeTy, @Param("codeValue") String codeValue);

    /**
     * 생성
     *
     * @param tbIntrfcCode
     * @return
     */
    public int insert(@Param("tbIntrfcCode") TbIntrfcCode tbIntrfcCode);

    /**
     * 수정
     *
     * @param tbIntrfcCode
     * @return
     */
    public int update(@Param("tbIntrfcCode") TbIntrfcCode tbIntrfcCode);

    /**
     * 삭제
     *
     * @param codeTy
     * @param codeValue
     * @return
     */
    public int delete(@Param("codeTy") String codeTy, @Param("codeValue") String codeValue);

    /**
     * 목록 삭제 - CODE_TY(으)로
     *
     * @param codeTy
     * @return
     */
    public int deleteListByCodeTy(@Param("codeTy") String codeTy);

}
