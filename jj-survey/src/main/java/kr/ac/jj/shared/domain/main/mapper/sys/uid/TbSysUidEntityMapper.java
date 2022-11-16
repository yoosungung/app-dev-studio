package kr.ac.jj.shared.domain.main.mapper.sys.uid;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.sys.uid.TbSysUid;

/**
 * 시스템 - UID Entity Mapper
 */
abstract interface TbSysUidEntityMapper {

    /**
     * 조회
     *
     * @param uidValue
     * @return
     */
    public TbSysUid select(@Param("uidValue") String uidValue);

    /**
     * 생성
     *
     * @param tbSysUid
     * @return
     */
    public int insert(@Param("tbSysUid") TbSysUid tbSysUid);

    /**
     * 수정
     *
     * @param tbSysUid
     * @return
     */
    public int update(@Param("tbSysUid") TbSysUid tbSysUid);

    /**
     * 삭제
     *
     * @param uidValue
     * @return
     */
    public int delete(@Param("uidValue") String uidValue);

}
