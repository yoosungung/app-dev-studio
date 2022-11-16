package kr.ac.jj.survey.domain.main.mapper.sys.code.group;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.sys.code.group.TbSysCodeGroup;

/**
 * 시스템 - 코드 그룹 Entity Mapper
 */
abstract interface TbSysCodeGroupEntityMapper {
    /**
     * 조회
     *
     * @param codeGroupId
     * @return
     */
    public TbSysCodeGroup select(@Param("codeGroupId") String codeGroupId);

    /**
     * 생성
     *
     * @param tbSysCodeGroup
     * @return
     */
    public int insert(@Param("tbSysCodeGroup") TbSysCodeGroup tbSysCodeGroup);

    /**
     * 수정
     *
     * @param tbSysCodeGroup
     * @return
     */
    public int update(@Param("tbSysCodeGroup") TbSysCodeGroup tbSysCodeGroup);

    /**
     * 삭제
     *
     * @param codeGroupId
     * @return
     */
    public int delete(@Param("codeGroupId") String codeGroupId);
}
