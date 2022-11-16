package kr.ac.jj.survey.domain.main.mapper.sys.code.value;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.code.value.TbSysCodeValue;

/**
 * 시스템 - 코드 값 Entity Mapper
 */
abstract interface TbSysCodeValueEntityMapper {
    /**
     * 목록 조회 - CODE_GROUP_ID(으)로
     *
     * @param codeGroupId
     * @return
     */
    public List<TbSysCodeValue> selectListByCodeGroupId(@Param("codeGroupId") String codeGroupId);

    /**
     * 조회
     *
     * @param codeValueId
     * @return
     */
    public TbSysCodeValue select(@Param("codeValueId") String codeValueId);

    /**
     * 생성
     *
     * @param tbSysCodeValue
     * @return
     */
    public int insert(@Param("tbSysCodeValue") TbSysCodeValue tbSysCodeValue);

    /**
     * 수정
     *
     * @param tbSysCodeValue
     * @return
     */
    public int update(@Param("tbSysCodeValue") TbSysCodeValue tbSysCodeValue);

    /**
     * 삭제
     *
     * @param codeValueId
     * @return
     */
    public int delete(@Param("codeValueId") String codeValueId);

    /**
     * 목록 삭제 - CODE_GROUP_ID(으)로
     *
     * @param codeGroupId
     * @return
     */
    public int deleteListByCodeGroupId(@Param("codeGroupId") String codeGroupId);
}
